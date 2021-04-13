package com.cn.exam.service.exam.impl;

import com.cn.common.jpa.util.JpaUtil;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.MyString;
import com.cn.exam.dto.exam.TestResultDTO;
import com.cn.exam.service.exam.ExamResultService;
import com.cn.exam.vo.exam.TestResultVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Author fengzhilong
 *@Date 2021/4/13 16:56
 *@Desc
 **/
@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Resource
    private JpaUtil jpaUtil;


    @Override
    public List<TestResultDTO> testResultPage(TestResultVO testResultVO, JsonPage pageable) {
        String hql = "";
        Map<String, Object> params = new HashMap<>();
        hql += "select new com.cn.exam.dto.exam.TestResultDTO(ps,rs) from ExamTestPerson ps " +
                "left join ExamTestResult rs on ps.paperId = rs.paperId and ps.ghid = rs.ghid " +
                "where 1=1 ";
        if (testResultVO.getTestStatus() != null) {
            hql += "and ps.testStatus = :testStatus ";
            params.put("testStatus", testResultVO.getTestStatus());
        }
        if (testResultVO.getScoringStatus() != null) {
            hql += "and ps.scoringStatus = :scoringStatus ";
            params.put("scoringStatus", testResultVO.getScoringStatus());
        }
        if (MyString.isNotEmpty(testResultVO.getPlanId())) {
            hql += "and ps.planId = :planId ";
            params.put("planId", testResultVO.getPlanId());
        }
        if (MyString.isNotEmpty(testResultVO.getGhid())) {
            hql += "and ps.ghid = :ghid ";
            params.put("ghid", testResultVO.getGhid());
        }
        if (testResultVO.getOnlyShortAnswer() != null) {
            if (testResultVO.getOnlyShortAnswer() == 1) { // 只展示填空题和简答题
                hql += "and (rs.topicType = 3 or rs.topicType = 5) ";
            }
        }

        hql += "order by ps.submitTime asc, ps.ghid asc";

        Page<TestResultDTO> page = jpaUtil.page(hql, params, pageable.getPageableUnsorted(), TestResultDTO.class);
        System.out.println("试题数:"+page.getTotalElements());
        return page.getContent();
    }
}
