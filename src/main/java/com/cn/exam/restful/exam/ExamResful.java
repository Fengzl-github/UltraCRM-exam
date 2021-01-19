package com.cn.exam.restful.exam;

import com.cn.common.exception.FzlException;
import com.cn.common.jpa.vo.JsonPage;
import com.cn.common.utils.MyString;
import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.dto.exam.ExamPlanDTO;
import com.cn.exam.dto.exam.ExamTopicDTO;
import com.cn.exam.dto.exam.SelfTrainingDTO;
import com.cn.exam.dto.exam.TestPaperDTO;
import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.service.exam.ExamPlanService;
import com.cn.exam.service.exam.ExamTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/8 13:44
 *@Desc 关于考试的接口控制器
 **/
@RestController
@RequestMapping("/exam")
public class ExamResful {

    @Autowired
    private ExamTopicService examTopicService;
    @Autowired
    private ExamPlanService examPlanService;


    /**
     * @Author fengzhilong
     * @Desc 题库列表
     * @Date 2021/1/8 17:48
     * @param examTopicDTO
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamTopicPage")
    ResResult listExamTopicPage(@RequestBody ExamTopicDTO examTopicDTO, JsonPage pageable) throws FzlException {

        Page<ExamTopic> page = examTopicService.listExamTopicPage(examTopicDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改题目
     * @Date 2021/1/8 17:57
     * @param examTopic
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamTopic")
    ResResult saveExamTopic(@RequestBody ExamTopic examTopic) throws FzlException {

        examTopicService.saveExamTopic(examTopic);

        return ResCode.OK.msg("操作成功");
    }

    /**
     * @Author fengzhilong
     * @Desc 禁用/启用题目
     * @Date 2021/1/8 17:57
     * @param topicId 重启
     * @return
     **/
    @PostMapping("/removeOrRebootTopic")
    ResResult removeOrRebootTopic(String topicId, Integer isDel) throws FzlException {

        if (MyString.isNotEmpty(topicId)) {

            examTopicService.removeOrRebootTopic(topicId, isDel);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 自主练习获取题目内容
     * @Date 2021/1/13 15:03
     * @param selfTrainingDTO
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/getSelfExamInfo")
    ResResult getSelfExamInfo(@RequestBody SelfTrainingDTO selfTrainingDTO) {
        List<ExamTopic> examList = examTopicService.getSelfExamData(selfTrainingDTO);
//        System.out.println(examList);
        //转换为前端可解析的数据结构
        List<TestPaperDTO> list = new ArrayList<>();
        for (ExamTopic examTopic : examList) {
            list.add(new TestPaperDTO(examTopic));
        }
//        System.out.println(list);

        return ResCode.OK.putData("content", list);
    }


    /**
     * @Author fengzhilong
     * @Desc 考试计划列表
     * @Date 2021/1/14 17:12
     * @param pageable
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/listExamPlanPage")
    ResResult listExamPlanPage(@RequestBody ExamPlanDTO examPlanDTO, JsonPage pageable) throws FzlException {

        Page<ExamPlan> page = examPlanService.listExamPlanPage(examPlanDTO, pageable);

        return ResCode.OK.msg("查询成功")
                .putData("content", page.getContent())
                .putData("total", page.getTotalElements());
    }


    /**
     * @Author fengzhilong
     * @Desc 保存和修改考试计划
     * @Date 2021/1/15 10:18
     * @param examPlan
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/saveExamPlan")
    ResResult saveExamPlan(@RequestBody ExamPlan examPlan) throws FzlException {

        examPlanService.saveExamPlan(examPlan);

        return ResCode.OK.msg("操作成功");
    }


    /**
     * @Author fengzhilong
     * @Desc 发布考试计划/公布成绩
     * @Date 2021/1/15 10:39
     * @param planId
	 * @param status
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/updatePlanStatus")
    ResResult updatePlanStatus(String planId, Integer status) throws FzlException {

        if (MyString.isNotEmpty(planId)) {

            examPlanService.updatePlanStatus(planId, status);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /**
     * @Author fengzhilong
     * @Desc 删除考试计划
     * @Date 2021/1/15 10:21
     * @param planId
     * @return com.cn.common.vo.ResResult
     **/
    @PostMapping("/removeExamPlan")
    ResResult removeExamPlan(String planId) throws FzlException {

        if (MyString.isNotEmpty(planId)) {

            examPlanService.removeExamPlan(planId);

            return ResCode.OK.msg("操作成功");
        } else {
            return ResCode.ERROR.msg("缺少参数");
        }
    }


    /*生成几套试卷*/
}
