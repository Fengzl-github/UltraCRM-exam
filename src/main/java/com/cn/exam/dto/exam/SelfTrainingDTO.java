package com.cn.exam.dto.exam;

import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 10:05
 *@Desc
 **/
@Data
public class SelfTrainingDTO {

    /*抽题数*/
    private Integer tNum;

    /*题目类型list*/
    private String tTypeList;
    /*难度等级list*/
    private String tDlList;
}
