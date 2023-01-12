package com.atguigu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@EnableFeignClients
@EnableScheduling
@MapperScan("com.atguigu.staservice.mapper")
public class StaAppliction {
    public static void main(String[] args) {
        SpringApplication.run(StaAppliction.class, args);
    }
}
