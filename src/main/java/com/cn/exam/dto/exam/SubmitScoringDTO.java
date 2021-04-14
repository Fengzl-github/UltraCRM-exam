package com.cn.exam.dto.exam;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/4/14 10:28
 *@Desc 提交阅卷结果
 **/
@Data
public class SubmitScoringDTO {

    //阅卷人ghid
    @NotNull(message = "缺少阅卷人信息")
    private String markerGhid;
    //阅卷人
    @NotNull(message = "缺少阅卷人信息")
    private String markerName;

    //是否只提交填空和简答
    private Integer onlyShortAnswer;

    //试题阅卷结果
    private List<TestScoreDTO> epList;
}
