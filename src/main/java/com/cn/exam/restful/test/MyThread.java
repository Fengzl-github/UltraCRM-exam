package com.cn.exam.restful.test;

/**
 *@Author fengzhilong
 *@Date 2021/7/6 14:53
 *@Desc
 **/
public class MyThread implements Runnable {

    private String url;
    private Integer count;
    public MyThread(String url, Integer count) {
        this.url = url;
        this.count = count;
    }

    @Override
    public void run() {
        PostRequestTest.getRequestTest(url, count);
    }
}
