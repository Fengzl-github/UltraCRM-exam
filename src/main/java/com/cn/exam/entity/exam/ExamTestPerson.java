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
 *@Date 2021/1/15 13:48
 *@Desc 参考人员表
 **/
@Entity
@Data
@Table(name = "t_exam_test_person")
@DynamicInsert
@DynamicUpdate
public class ExamTestPerson extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试计划编号'")
    private String planId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '计划名称'")
    private String planName;
    @Column(columnDefinition = "varchar(30) NOT NULL COMMENT '参考人工号'")
    private String ghid;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '参考人姓名'")
    private String name;
    @Column(columnDefinition = "varchar(20) NULL DEFAULT NULL COMMENT '试卷编号'")
    private String paperId;
    @Column(columnDefinition = "varchar(64) NULL DEFAULT NULL COMMENT '试卷名称'")
    private String paperName;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '答卷状态：1:未作答,2:已作答'")
    private Integer testStatus;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 1 COMMENT '阅卷状态：1:未阅卷,2:已阅卷'")
    private Integer scoringStatus;
    @Column(columnDefinition = "varchar(64) NULL DEFAULT NULL COMMENT '提交试卷时间'")
    private String submitTime;
    @Column(columnDefinition = "double(5,1) COMMENT '考试总分'")
    private Double totalScore;
    @Column(columnDefinition = "varchar(64) NULL DEFAULT NULL COMMENT '考试用时'")
    private String usedTime;
}
