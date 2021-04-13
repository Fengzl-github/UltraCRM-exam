package com.cn.exam.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author fengzhilong
 * @Date 2020/9/25 14:35
 * @Desc // 定时更新token的secret秘钥
 **/
@Slf4j
@Component
public class updateJwtTokenTimer {

    @Scheduled(cron = "0 10 0 * * ?")
    public void timerCorpid() {

        /*每天凌晨10分时触发token改变, 此刻所有的登录在发起请求都需要登录验证*/
        try {
            pmJwtToken.setNewTokenSecret();
            log.info("秘钥发生改变, 所有人都需要重新登录");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
