package com.cn.exam.restful.test;

import com.cn.exam.entity.exam.ExamTopic;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 17:33
 *@Desc
 **/
@Data
public class TestRestful {

    private int score;
    private int age;

    public TestRestful(int score, int age) {
        this.score = score;
        this.age = age;
    }

    public static void main(String[] args) {

        List<ExamTopic> list = new ArrayList<>();
        ExamTopic examTopic = new ExamTopic();
        examTopic.setTopicType(2);
        examTopic.setDifficultyLevel(4);
        list.add(examTopic);

        examTopic = new ExamTopic();
        examTopic.setTopicType(1);
        examTopic.setDifficultyLevel(4);
        list.add(examTopic);

        examTopic = new ExamTopic();
        examTopic.setTopicType(3);
        examTopic.setDifficultyLevel(1);
        list.add(examTopic);

        examTopic = new ExamTopic();
        examTopic.setTopicType(2);
        examTopic.setDifficultyLevel(1);
        list.add(examTopic);

        Collections.sort(list, new Comparator<ExamTopic>() {
            @Override
            public int compare(ExamTopic o1, ExamTopic o2) {
                int i = o1.getTopicType() - o2.getTopicType();
                if (i == 0) {
                    return o1.getDifficultyLevel() - o2.getDifficultyLevel();
                }
                return i;
            }
        });
        System.out.println(list);
        for (ExamTopic n : list
             ) {
            System.out.println(n.getTopicType()+"        "+ n.getDifficultyLevel());
        }
    }
}
