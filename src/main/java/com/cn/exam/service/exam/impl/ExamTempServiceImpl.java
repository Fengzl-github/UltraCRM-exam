package com.cn.exam.service.exam.impl;

import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.exam.dao.exam.ExamPlanDao;
import com.cn.exam.dao.exam.ExamTestPaperDao;
import com.cn.exam.dao.exam.ExamTestQuesDao;
import com.cn.exam.dto.exam.ExamProdPaperDTO;
import com.cn.exam.dto.exam.ExamTempDTO;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPaper;
import com.cn.exam.entity.exam.ExamTestQues;
import com.cn.exam.service.exam.ExamTempService;
import com.cn.exam.util.ExamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 15:15
 *@Desc
 **/
@Service
@Slf4j
public class ExamTempServiceImpl implements ExamTempService {

    @Resource
    private ExamPlanDao examPlanDao;
    @Resource
    private ExamTestPaperDao examTestPaperDao;
    @Resource
    private ExamTestQuesDao examTestQuesDao;


    /**
     * @Desc 生成试卷模板
     * @param examProdPaperDTO
     **/
    @Override
    public void prodThePaperTemp(ExamProdPaperDTO examProdPaperDTO) {
        //为每套试卷生成名字用
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
        // 1. 检测本次考试计划是否存在
        ExamPlan examPlan = examPlanDao.findByPlanId(examProdPaperDTO.getPlanId());
        if (examPlan == null) {
            log.info("考试计划不存在或已删除");
            return;
        }
        // 2. 根据生成几套试卷遍历生成
        for (int i = 0; i < examProdPaperDTO.getPaperCnt(); i++) {
            // 2.1 保存模板
            ExamTestPaper examTestPaper = new ExamTestPaper();
            examTestPaper.setPaperId("PP" + DateTime.Now().ToString("yyyyMMddHHmmss") + MyString.getRandom(4));
            examTestPaper.setPaperName(map.get(i) + "卷");
            examTestPaper.setPlanId(examProdPaperDTO.getPlanId());
            examTestPaper.setPlanName(examProdPaperDTO.getPlanName());
            examTestPaperDao.saveAndFlush(examTestPaper);

            // 3. 遍历所有试题内容生成试卷模板
            for (ExamTempDTO examTemp : examProdPaperDTO.getExamDatas()) {
                // 3.1 根据题量、实际题量获取随机数
                Set<Integer> set = ExamUtil.getRandom(examTemp.getActualCnt(), examTemp.getCnt());
                // 3.2 生成试卷试题信息并保存
                for (Integer n : set) {
                    ExamTestQues examTestQues = examTemp.getExamItem().get(n);
                    examTestQues.setTopicId("TP" + DateTime.Now().ToString("yyyyMMddHHmmss") + MyString.getRandom(4));
                    examTestQues.setTopicScore(examTemp.getScore());
                    examTestQues.setPaperId(examTestPaper.getPaperId());
                    examTestQues.setPaperName(examTestPaper.getPaperName());
                    examTestQues.setPlanId(examTestPaper.getPlanId());
                    examTestQues.setPlanName(examTestPaper.getPlanName());
                    //去除id 保证新增
                    examTestQues.setId(null);

                    examTestQuesDao.saveAndFlush(examTestQues);
                }
            }
        }
    }


    /**
     * @Desc 计划下试卷列表
     * @param planId
     **/
    @Override
    public List<ExamTestPaper> testPaperList(String planId) {

        List<ExamTestPaper> list = examTestPaperDao.findByPlanId(planId);
        return list;
    }

    /**
     * @Desc 设置试卷是否生效
     * @param paperId
     * @param isUsed
     **/
    @Override
    public void updateIsUsed(String paperId, Integer isUsed) {

        examTestPaperDao.updateIsUsed(paperId, isUsed);
    }
}
