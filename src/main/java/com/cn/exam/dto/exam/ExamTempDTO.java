package com.cn.exam.dto.exam;

import com.cn.exam.entity.exam.ExamTestQues;
import com.cn.exam.entity.exam.ExamTopic;
import lombok.Data;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/27 15:51
 *@Desc 考试模板DTO
 **/
@Data
public class ExamTempDTO {

    /*每题分数*/
    private Integer score;
    /*每套试卷题量*/
    private Integer cnt;
    /*实际题量*/
    private Integer actualCnt;
    /*题目类型*/
    private Integer topicType;

    /*题目内容*/
    private List<ExamTestQues> ExamItem;

}
