package com.cn.exam.service.exam;

import com.cn.common.jpa.vo.JsonPage;
import com.cn.exam.dto.exam.ExamPlanDTO;
import com.cn.exam.entity.exam.ExamPlan;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

/**
 *@Author fengzhilong
 *@Date 2021/1/14 15:37
 *@Desc
 **/
@Validated
public interface ExamPlanService {

    /*考试计划列表*/
    Page<ExamPlan> listExamPlanPage(ExamPlanDTO examPlanDTO, JsonPage pageable);

    /*添加考试计划*/
    void saveExamPlan(ExamPlan examPlan);

    /*发布计划/公布成绩*/
    void updatePlanStatus(String planId, Integer status);

    /*删除考试计划*/
    void removeExamPlan(String planId);
}
