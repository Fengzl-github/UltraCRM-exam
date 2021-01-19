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
 *@Date 2021/1/14 15:19
 *@Desc 考试计划表
 **/
@Entity
@Data
@Table(name = "t_exam_plan")
@DynamicInsert
@DynamicUpdate
public class ExamPlan extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试计划编号'", unique = true)
    private String planId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '计划名称'")
    private String planName;
    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试开始时间'")
    private String testStartTime;
    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试结束时间'")
    private String testEndTime;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '考试形式：0:闭卷,1:开卷'")
    private Integer testForm;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '发卷类型：0:随机,1:轮询'")
    private Integer testType;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：0:未发布,1:已发布,2:待公布成绩,3:已公布成绩'")
    private Integer status;
    @Column(columnDefinition = "varchar(30) NOT NULL COMMENT '创建人工号'")
    private String ghid;
    @Column(columnDefinition = "varchar(50) NOT NULL COMMENT '创建人姓名'")
    private String operator;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0:正常,1:删除'")
    private Integer isDel;


}
