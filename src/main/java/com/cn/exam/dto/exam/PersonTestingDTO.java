package com.cn.exam.dto.exam;

import com.cn.exam.entity.exam.ExamTestResult;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/4/6 16:22
 *@Desc 提交试卷
 **/
@Data
public class PersonTestingDTO {

    @NotBlank(message = "缺少计划id")
    private String planId;
    @NotBlank(message = "缺少试卷id")
    private String paperId;
    @NotBlank(message = "缺少工号")
    private String ghid;
    @NotNull
    private Integer useTime;
    @NotNull
    private List<ExamTestResult> personEp;
}
