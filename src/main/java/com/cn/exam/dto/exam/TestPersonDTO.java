package com.cn.exam.dto.exam;

import com.cn.exam.entity.exam.ExamPlan;
import com.cn.exam.entity.exam.ExamTestPerson;
import lombok.Data;

import javax.persistence.Column;

/**
 *@Author fengzhilong
 *@Date 2021/3/12 10:52
 *@Desc
 **/
@Data
public class TestPersonDTO {

    //考试计划编号'")
    private String planId;
    //计划名称'")
    private String planName;
    //参考人工号'")
    private String ghid;
    //参考人姓名'")
    private String name;
    //试卷编号'")
    private String paperId;
    //试卷名称'")
    private String paperName;
    //答卷状态：1:未作答,2:已作答'")
    private Integer testStatus;
    //阅卷状态：1:未阅卷,2:已阅卷'")
    private Integer scoringStatus;
    //提交试卷时间'")
    private String submitTime;
    //考试总分'")
    private Double totalScore;
    //考试用时'")
    private String usedTime;


    //考试时长(分)'")
    private Integer testTime;
    //考试形式：0:闭卷,1:开卷'")
    private Integer testForm;
    //发卷类型：0:随机,1:轮询'")
    private Integer testType;
    //状态：0:未发布,1:已发布,2:待公布成绩,3:已公布成绩'")
    private Integer status;


    public TestPersonDTO(ExamTestPerson etp, ExamPlan ep) {
        if (etp != null) {
            this.planId = etp.getPlanId();

            this.planName = etp.getPlanName();

            this.ghid = etp.getGhid();

            this.name = etp.getName();

            this.paperId = etp.getPaperId();

            this.paperName = etp.getPaperName();

            this.testStatus = etp.getTestStatus();

            this.scoringStatus = etp.getScoringStatus();

            this.submitTime = etp.getSubmitTime();

            this.totalScore = etp.getTotalScore();

            this.usedTime = etp.getUsedTime();
        }

        if (ep != null){

            this.testTime = ep.getTestTime();

            this.testForm = ep.getTestForm();

            this.testType = ep.getTestType();

            this.status = ep.getStatus();
        }
    }
}
