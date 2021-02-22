package com.cn.exam.dto.exam;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/1/15 14:31
 *@Desc
 **/
@Data
public class ExamPlanDTO {
    /*计划名称*/
    private String planName;
    /*计划状态*/
    private Integer status;
    /*是否已添加试卷*/
    private Integer isPaper;
}
