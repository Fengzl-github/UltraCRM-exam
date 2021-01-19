package com.cn.exam.dto.exam;

import com.alibaba.fastjson.JSONObject;
import com.cn.exam.entity.exam.ExamTopic;
import lombok.Data;

import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/14 13:55
 *@Desc 合成试卷前端展示需要数据
 **/
@Data
public class TestPaperDTO {

    private Integer id;
    private String topicId;
    private Integer topicType;
    private String topicDes;
    private String correctAnswer;
    private double topicScore;
    private Integer difficultyLevel;
    private String topicContent;
    private List<ExamOptionDTO> topicQueContent;
    private String ghid;
    private String operator;
    private Integer isDel;

    public TestPaperDTO(ExamTopic examTopic) {

        this.id = examTopic.getId();
        this.topicId = examTopic.getTopicId();
        this.topicType = examTopic.getTopicType();
        this.topicDes = examTopic.getTopicDes();
        this.correctAnswer = examTopic.getCorrectAnswer();
        this.topicScore = examTopic.getTopicScore();
        this.difficultyLevel = examTopic.getDifficultyLevel();
        this.topicContent = examTopic.getTopicContent();
        this.topicQueContent = JSONObject.parseArray(examTopic.getTopicQueContent(), ExamOptionDTO.class);
        this.ghid = examTopic.getGhid();
        this.operator = examTopic.getOperator();
        this.isDel = examTopic.getIsDel();
    }


    public TestPaperDTO() {
    }
}
