package com.cn.exam.restful.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.text.Format;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *@Author fengzhilong
 *@Date 2021/2/20 16:58
 *@Desc
 **/
@Validated
@Slf4j
@RestController
public class TestController {

    public static void main(String[] args) {
        String strUrl = "http://127.0.0.1:9060/multi/multiThreadTest";
        ExecutorService pool = Executors.newCachedThreadPool();

        final Integer count = 50;
        //创建 count 个线程
        for (int i = 0; i < count; i++) {

            MyThread target = new MyThread(strUrl, count);
            pool.execute(target);
        }

        pool.shutdown();
    }


}
