package com.cn.exam.dto.exam;

import com.cn.exam.entity.exam.ExamTestPerson;
import com.cn.exam.entity.exam.ExamTestResult;
import lombok.Data;

/**
 *@Author fengzhilong
 *@Date 2021/4/13 17:02
 *@Desc 考试结果
 **/
@Data
public class TestResultDTO {
    private Integer id;
    //考试计划编号
    private String planId;
    //参考人工号
    private String ghid;
    //参考人姓名
    private String name;
    //试卷编号
    private String paperId;
    //试卷名称
    private String paperName;
    //答卷状态：1:未作答,2:已作答
    private Integer testStatus;
    //阅卷状态：1:未阅卷,2:已阅卷
    private Integer scoringStatus;
    //提交试卷时间
    private String submitTime;
    //考试总分
    private Double totalScore;
    //考试用时
    private String usedTime;

    //试题编号
    private String topicId;
    //题目类型：1:单项选择题,2:多项选择题,3:填空题,4:判断题,5:简答题
    private Integer topicType;
    //题干
    private String topicDes;
    //正确答案
    private String correctAnswer;
    //考生答案
    private String epReplay;
    //题目分值
    private double topicScore;
    //考生得分
    private double epScore;
    //难度等级：1:困难,2:较难,3:一般,4:较易,5:容易
    private Integer difficultyLevel;
    //选项内容
    private String topicContent;
    //选项内容,用于考试页面
    private String topicQueContent;


    public TestResultDTO(ExamTestPerson ps, ExamTestResult rs) {
        if (ps != null) {
            this.id = ps.getId();
            this.planId = ps.getPlanId();
            this.ghid = ps.getGhid();
            this.name = ps.getName();
            this.paperId = ps.getPaperId();
            this.paperName = ps.getPaperName();
            this.testStatus = ps.getTestStatus();
            this.scoringStatus = ps.getScoringStatus();
            this.submitTime = ps.getSubmitTime();
            this.totalScore = ps.getTotalScore();
            this.usedTime = ps.getUsedTime();
        }
        if (rs != null) {
            this.topicId = rs.getTopicId();
            this.topicType = rs.getTopicType();
            this.topicDes = rs.getTopicDes();
            this.correctAnswer = rs.getCorrectAnswer();
            this.epReplay = rs.getEpReplay();
            this.topicScore = rs.getTopicScore();
            this.epScore = rs.getEpScore();
            this.difficultyLevel = rs.getDifficultyLevel();
            this.topicContent = rs.getTopicContent();
            this.topicQueContent = rs.getTopicQueContent();
        }
    }


    public TestResultDTO() {
    }
}
