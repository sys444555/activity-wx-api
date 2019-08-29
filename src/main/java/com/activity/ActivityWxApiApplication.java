package com.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.activity.modules.*.mapper")
@SpringBootConfiguration
@EnableTransactionManagement
@EnableScheduling
public class ActivityWxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityWxApiApplication.class, args);
    }

}
