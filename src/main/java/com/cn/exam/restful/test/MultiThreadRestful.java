package com.cn.exam.restful.test;

import com.cn.common.vo.ResCode;
import com.cn.common.vo.ResResult;
import com.cn.exam.service.multi.MultiThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author fengzhilong
 *@Date 2021/7/6 14:31
 *@Desc
 **/
@Slf4j
@RestController
@RequestMapping("/multi")
public class MultiThreadRestful {

    @Autowired
    private MultiThreadService multiThreadService;

    //测试
    @PostMapping("/multiThreadTest")
    public ResResult multiThreadTest(){

        multiThreadService.saveProdData();

        return ResCode.OK;
    }
}
