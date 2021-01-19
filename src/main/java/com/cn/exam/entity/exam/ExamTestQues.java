package com.cn.exam.entity.exam;

import com.cn.common.jpa.BaseIntIdEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *@Author fengzhilong
 *@Date 2021/1/15 13:43
 *@Desc 试卷的试题内容表
 **/
@Entity
@Data
@Table(name = "t_exam_test_ques")
@DynamicInsert
@DynamicUpdate
public class ExamTestQues extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '试题编号'", unique = true)
    private String topicId;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题'")
    private Integer topicType;
    @Column(columnDefinition = "text NOT NULL COMMENT '题干'")
    private String topicDes;
    @Column(columnDefinition = "text NULL COMMENT '正确答案'")
    private String correctAnswer;
    @Column(columnDefinition = "double(5,1) NOT NULL DEFAULT 5 COMMENT '题目分值'")
    private double topicScore;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 3 COMMENT '难度等级：1:困难,2:较难,3:一般,4:较易,5:容易'")
    private Integer difficultyLevel;
    @Column(columnDefinition = "text NULL COMMENT '选项内容'")
    private String topicContent;
    @Column(columnDefinition = "text NULL COMMENT '选项内容,用于考试页面'")
    private String topicQueContent;

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '试卷编号'")
    private String paperId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '试卷名称'")
    private String paperName;
    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试计划编号'")
    private String planId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '计划名称'")
    private String planName;

}
