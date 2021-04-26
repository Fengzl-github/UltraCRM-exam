package com.cn.exam.service.exam.impl;

import com.cn.common.exception.FzlException;
import com.cn.common.jpa.util.JpaUtil;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamTestPersonDao;
import com.cn.exam.dao.exam.ExamTestResultDao;
import com.cn.exam.dto.exam.SubmitScoringDTO;
import com.cn.exam.dto.exam.TestResultDTO;
import com.cn.exam.dto.exam.TestScoreDTO;
import com.cn.exam.entity.exam.ExamTestPerson;
import com.cn.exam.service.exam.ExamResultService;
import com.cn.exam.vo.exam.TestResultVO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 *@Author fengzhilong
 *@Date 2021/4/13 16:56
 *@Desc
 **/
@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Resource
    private ExamTestPersonDao examTestPersonDao;
    @Resource
    private ExamTestResultDao examTestResultDao;
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
        if (testResultVO.getIsScoring() != null) {
            hql += "and ps.isScoring = :isScoring ";
            params.put("isScoring", testResultVO.getIsScoring());
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

        List<TestResultDTO> list = new ArrayList<>();
        synchronized (list) {
            Page<TestResultDTO> page = jpaUtil.page(hql, params, pageable.getPageableUnsorted(), TestResultDTO.class);
            System.out.println("试题数:" + page.getTotalElements());
            list = page.getContent();
            // 阅卷：修改抽到试卷的是否阅卷中状态
            if (testResultVO.getScoringStatus() == 1) {
                for (TestResultDTO temp : list) {

                    examTestPersonDao.updateIsScoring(temp.getId(), 1);
                }
            }
        }

        return list;
    }


    /**
     * @Author fengzhilong
     * @Desc 提交阅卷结果
     * @Date 2021/4/14 10:47
     * @param submitScoringDTO 参数
     * @return void
     **/
    @Override
    @Transactional
    public void submitScoring(SubmitScoringDTO submitScoringDTO) {

        // 1. 获取提交试题
        List<TestScoreDTO> epList = submitScoringDTO.getEpList();
        // 2. 存储所有考生id - 不重复
        Set<Integer> idSet = new HashSet<>();
        // 3. 循环所有试题
        for (TestScoreDTO testScoreDto : epList) {
            // 修改试题分数
            Integer isSucceed = examTestResultDao.updateEpScore(testScoreDto.getResId(), testScoreDto.getEpScore());
            if (isSucceed > 0) {
                System.out.println("分数更新成功，id-> " + testScoreDto.getResId());
                idSet.add(testScoreDto.getId());
            }
        }
        // 4. 循环更新每个人的状态 阅卷人等信息
        for (Integer id : idSet) {
            Optional<ExamTestPerson> personOptional = examTestPersonDao.findById(id);
            if (!personOptional.isPresent()) {
                throw new FzlException("考生不存在");
            }
            ExamTestPerson examTestPerson = personOptional.get();
            examTestPerson.setScoringStatus(2);
            examTestPerson.setMarkerGhid(submitScoringDTO.getMarkerGhid());
            examTestPerson.setMarkerName(submitScoringDTO.getMarkerName());

            //重新计算每个人总分
            double totalScore = examTestResultDao.countTotalScore(examTestPerson.getPaperId(), examTestPerson.getGhid());
            examTestPerson.setTotalScore(totalScore);

            // 保存
            examTestPersonDao.saveAndFlush(examTestPerson);
        }


    }
}
