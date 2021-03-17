package com.cn.exam.vo.exam;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 *@Author fengzhilong
 *@Date 2021/3/12 10:32
 *@Desc 获取考生信息
 **/
@Data
public class TestPersonVO {

    @NotBlank(message = "缺少计划id")
    private String planId;
    @NotBlank(message = "缺少工号")
    private String ghid;

}
