package com.cn.exam.restful.exam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 10:41
 *@Desc 关于考试的返回页面的控制器
 **/
@Controller
@RequestMapping("/exam")
public class ExamPageRestful {

    @RequestMapping("/test1")
    public String examTest(HttpServletRequest request, Model model) {

        model.addAttribute("name", "张三");

        return "exam/test";
    }
}
