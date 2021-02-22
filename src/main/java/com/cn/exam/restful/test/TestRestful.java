package com.cn.exam.restful.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *@Author fengzhilong
 *@Date 2021/1/13 17:33
 *@Desc
 **/
@Data
public class TestRestful {

    public static void main(String[] args) {
        getTest();
    }

    public static void getTest() {
       /* ArrayList arrayList = new ArrayList(0);
        arrayList.add("li");
        arrayList.add("hi");
        arrayList.add("fi");
        System.out.println(arrayList);

        // 迭代器
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Iterator<String> iterator1 = arrayList.listIterator();
        while (iterator1.hasNext()) {
            System.out.println("1-" + iterator1.next());
        }*/

        // 笔记本外接显示器的原理
        // 是笔记本显示屏投影到外接显示器还是可以直接作用到外接显示器
        // 外接显示器和笔记本显示屏实现分屏

        /**
         * @Author fengzhilong
         * @Desc Hutool 工具包
         * @Date 2021/2/20 15:15
         * @param
         * @return void
         **/
        // Convert - 转换类，各种类型之间的转换
        Convert.toStr(1.2);
        // 时间工具类

        // 图形验证码
        // 1. 定义图形验证码的长度、宽度
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50, 4, 50);
        // 2. 图形文件写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("D:/line.png");

        System.out.println(lineCaptcha.getCode());
        boolean verify = lineCaptcha.verify("1234");
        if (verify) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }


    }


}
