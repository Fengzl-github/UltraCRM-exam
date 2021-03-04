package com.cn.exam.vo.exam;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *@Author fengzhilong
 *@Date 2021/3/4 10:15
 *@Desc
 **/
@Data
public class ExamTopicVO {

    // 题目编号
    private String topicId;
    // 题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题
    @NotNull(message = "题目类型不能为空")
    private Integer topicType;
    // 题干
    @NotBlank(message = "题干不能为空")
    private String topicDes;
    // 正确答案
    private String correctAnswer;
    // 题目分值
    private double topicScore;
    // 难度等级：1:困难,2:较难,3:一般,4:较易,5:容易
    @NotNull(message = "难度等级不能为空")
    private Integer difficultyLevel;
    // 选项内容
    private String topicContent;
    // 选项内容,用于考试页面
    private String topicQueContent;
    // 创建人工号
    @NotBlank(message = "创建人id不能为空")
    private String ghid;
    // 创建人姓名
    @NotBlank(message = "创建人不能为空")
    private String operator;

    private Integer isDel;


}
