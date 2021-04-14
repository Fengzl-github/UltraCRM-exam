package com.cn.exam.dto.exam;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/4/14 10:34
 *@Desc 阅卷提交的试题信息
 **/
@Data
public class TestScoreDTO {

    //person表id
    @NotNull(message = "id不能为空")
    private Integer id;
    //试题id
    @NotNull(message = "resId不能为空")
    private Integer resId;
    //得分
    @NotNull(message = "得分不能为空")
    private double epScore;

}
