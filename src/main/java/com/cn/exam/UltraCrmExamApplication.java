package com.cn.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.cn")
@ServletComponentScan(basePackages = "com.cn")
public class UltraCrmExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(UltraCrmExamApplication.class, args);
    }

}
