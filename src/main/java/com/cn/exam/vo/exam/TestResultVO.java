package com.cn.exam.vo.exam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/4/13 16:50
 *@Desc
 **/
@Data
public class TestResultVO {

    @NotBlank(message = "缺少计划id")
    private String planId;
    @NotNull(message = "缺少testStatus；答卷状态：1:未作答,2:已作答")
    private Integer testStatus;
    @NotNull(message = "缺少scoringStatus；阅卷状态：1:未阅卷,2:已阅卷")
    private Integer scoringStatus;
    // 是否在阅卷中(1-待阅卷 2-阅卷中；防止多个阅卷人抽到相同试卷)
    private Integer isScoring;

    private String ghid;
    @NotNull(message = "缺少onlyShortAnswer")
    private Integer onlyShortAnswer;
}
