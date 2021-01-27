package com.cn.exam.dto.exam;

import lombok.Data;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 15:10
 *@Desc 生成试卷DTO
 **/
@Data
public class ExamProdPaperDTO {

    /*考试计划id*/
    private String planId;
    /*考试计划名称*/
    private String planName;
    /*生成几套试卷*/
    private Integer paperCnt;

    /*所有试题内容*/
    private List<ExamTempDTO> examDatas;

}
