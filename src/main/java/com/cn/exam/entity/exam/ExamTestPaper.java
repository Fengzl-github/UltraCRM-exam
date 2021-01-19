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
 *@Date 2021/1/15 11:19
 *@Desc 试卷模板信息表
 **/
@Entity
@Data
@Table(name = "t_exam_test_paper")
@DynamicInsert
@DynamicUpdate
public class ExamTestPaper extends BaseIntIdEntity {

    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '试卷编号'", unique = true)
    private String paperId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '试卷名称'")
    private String paperName;
    @Column(columnDefinition = "varchar(20) NOT NULL COMMENT '考试计划编号'")
    private String planId;
    @Column(columnDefinition = "varchar(64) NOT NULL COMMENT '计划名称'")
    private String planName;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否使用：0:失效,1:使用'")
    private Integer isUsed;
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除：0:正常,1:删除'")
    private Integer isDel;


}
