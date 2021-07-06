package com.cn.exam.service.multi.impl;

import com.cn.common.exception.FzlException;
import com.cn.exam.dao.exam.ExamTopicDao;
import com.cn.exam.entity.exam.ExamTopic;
import com.cn.exam.service.multi.MultiThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 *@Author fengzhilong
 *@Date 2021/7/6 14:34
 *@Desc
 **/
@Slf4j
@Service
public class MultiThreadServiceImpl implements MultiThreadService {

    @Resource
    private ExamTopicDao examTopicDao;

    @Override
    public void saveProdData() {
        ExamTopic examTopic;
        String maxTopicId = "";
        log.info("[锁前id] -> {}", maxTopicId);
        synchronized (maxTopicId){
            maxTopicId = examTopicDao.findMaxTopicId();
            String first = maxTopicId.substring(0, 15);
            Integer next = Integer.parseInt(maxTopicId.substring(15)) + 1;
            System.out.println(next);
            maxTopicId = first + "" + next;
            log.info("[锁中id] -> {}", maxTopicId);
            Optional<ExamTopic> byId = examTopicDao.findById(12);
            if (!byId.isPresent()) {
                throw new FzlException("信息不存在");
            }

            examTopic = byId.get();
            examTopic.setId(null);
            examTopic.setTopicId(maxTopicId);
            examTopicDao.saveAndFlush(examTopic);
        }
        log.info("[锁后id] -> {}", maxTopicId);

        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("[睡后] ->{}",examTopic.getTopicId());

    }
}
