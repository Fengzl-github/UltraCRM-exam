package com.cn.exam.dto.exam;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 10:05
 *@Desc
 **/
@Data
public class SelfTrainingDTO {

    /*抽题数*/
    @NotNull(message = "题量不能为空")
    private Integer tNum;

    /*题目类型list*/
    private String tTypeList;
    /*难度等级list*/
    private String tDlList;
}
