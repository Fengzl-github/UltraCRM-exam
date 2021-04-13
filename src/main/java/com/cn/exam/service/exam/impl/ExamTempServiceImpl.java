package com.cn.exam.service.exam.impl;

import com.alibaba.fastjson.JSONObject;
import com.cn.common.exception.FzlException;
import com.cn.common.utils.DateTime;
import com.cn.common.utils.MyString;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.dao.exam.ExamPlanDao;
import com.cn.exam.dao.exam.ExamTestPaperDao;
import com.cn.exam.dao.exam.ExamTestPersonDao;
import com.cn.exam.dao.exam.ExamTestQuesDao;
import com.cn.exam.dto.exam.ExamProdPaperDTO;
import com.cn.exam.dto.exam.ExamTempDTO;
import com.cn.exam.dto.exam.TestPaperDTO;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPaper;
import com.cn.exam.entity.exam.ExamTestPerson;
import com.cn.exam.entity.exam.ExamTestQues;
import com.cn.exam.service.exam.ExamTempService;
import com.cn.exam.util.ExamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private ExamTestPersonDao examTestPersonDao;


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
        // 修改计划为已添加试卷
        examPlan.setIsPaper(1);
        examPlanDao.saveAndFlush(examPlan);
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

    /**
     * @Desc 试卷预览
     * @param paperId
     **/
    @Override
    public ResResult previewPaperData(String planId, String paperId) {

        List<ExamTestQues> quesList = examTestQuesDao.findByPaperId(paperId);
        ExamPlan examPlan = examPlanDao.findByPlanId(planId);

        //转换为前端可解析的数据结构
        List<TestPaperDTO> list = new ArrayList<>();
        for (ExamTestQues examTestQues : quesList) {
            list.add(new TestPaperDTO(examTestQues));
        }
        return ResCode.OK.msg("操作成功")
                .putData("content", list)
                .putData("Timer", examPlan.getTestTime());
    }


    /**
     * @Desc 分配试卷
     * @param planId
     **/
    @Override
    public void allotPaper(String planId) {
        log.info("【分配试卷】---【计划id】->{}", planId);
        // 1. 获取考试计划下所有需要分配试卷的人员
        List<ExamTestPerson> personList = examTestPersonDao.findByPlanIdAndPaperId(planId);
        if (personList.size() == 0) {
            log.info("【分配试卷】---【没有需要分配的人员】");
            throw new FzlException("没有需要分配的人员");
        }

        // 2. 获取发卷方式
        ExamPlan examPlan = examPlanDao.findByPlanId(planId);

        Integer type = examPlan.getTestType() == null ? 0 : examPlan.getTestType();/*0:随机,1:轮询*/
        log.info("【分配试卷】---【发卷方式】->{}", type == 0 ? "随机" : "轮询");
        // 3. 获取考试计划下所有有效试卷
        List<ExamTestPaper> paperList = examTestPaperDao.findByPlanIdAndIsUsed(planId, 1);
        Integer paperCnt = paperList.size();
        if (paperCnt == 0) {
            log.info("【分配试卷】---【没有有效试卷】");
            throw new FzlException("没有有效试卷");
        }
        log.info("【分配试卷】---【人员信息】->{}，【试卷信息】->{}", JSONObject.toJSONString(personList), JSONObject.toJSON(paperList));
        // 4. 分配
        for (int i = 0; i < personList.size(); i++) {
            ExamTestPerson examTestPerson = personList.get(i);
            // 随机分配
            ExamTestPaper examTestPaper = null;
            Integer random;
            if (type == 0) {
                random = ExamUtil.getOneRandom(paperCnt);
                examTestPaper = paperList.get(random);
            } else { // 轮询
                random = i % (paperCnt - 1);
                examTestPaper = paperList.get(random);
            }

            // 添加试卷信息
            examTestPerson.setPaperId(examTestPaper.getPaperId());
            examTestPerson.setPaperName(examTestPaper.getPaperName());

            log.info("【分配试卷】---【分配结果】---【人员】>{}，【试卷】->{}", examTestPerson.getName(), examTestPerson.getPaperName());
            examTestPersonDao.saveAndFlush(examTestPerson);

        }
    }

    /**
     * @Desc 获取参考人列表
     * @param planId
     **/
    @Override
    public List<ExamTestPerson> getTestPerson(String planId) {

        List<ExamTestPerson> list = examTestPersonDao.findbyPlanId(planId);

        return list;
    }
}
