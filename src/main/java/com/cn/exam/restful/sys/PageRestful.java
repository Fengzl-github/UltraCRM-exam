package com.cn.exam.restful.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *@Author fengzhilong
 *@Date 2021/1/8 14:37
 *@Desc 菜单跳转页面Ctrl
 **/
@Controller
@RequestMapping("/page")
public class PageRestful {
    /*题库*/
    @GetMapping("/exam/topiclist")
    public String examTopicList() {
        return "exam/topiclist";
    }

    /*自主练习*/
    @GetMapping("/exam/selftraning")
    public String examSelftraning() {
        return "exam/selftraning";
    }

    /*模拟考试*/
    @GetMapping("/exam/selfTrainingtest")
    public String examSelfTrainingtest() {
        return "exam/selftrainingtest";
    }

    /*考试计划*/
    @GetMapping("/exam/plan")
    public String examTopicPlan() {
        return "exam/examplan";
    }

    /*为考试计划添加试卷*/
    @GetMapping("/exam/testPaperTempQue")
    public String testPaperTempQue() {
        return "exam/testpapertempque";
    }

    /*考试计划下试卷列表*/
    @GetMapping("/exam/paperlist")
    public String examPaperList() {
        return "exam/paperlist";
    }

    /*预览试卷*/
    @GetMapping("/exam/previewPaper")
    public String previewPaper() {
        return "exam/previewPaper";
    }

    /*参加考试的人员管理*/
    @GetMapping("/exam/testPerson")
    public String testPerson() {
        return "exam/testPerson";
    }

    /*参考人列表*/
    @GetMapping("/exam/personlist")
    public String personlist() {
        return "exam/personlist";
    }

    /*参加考试*/
    @GetMapping("/exam/testgo")
    public String testgo() {
        return "exam/testgo";
    }

    /*考试*/
    @GetMapping("/exam/persontesting")
    public String persontesting() {
        return "exam/persontesting";
    }
}
