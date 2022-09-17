package com.shop.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 消息队列模块
 * Author: wang Y
 * Date: 2022-08-27
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = { //1.排除SpringBoot自带的数据源配置
        DataSourceAutoConfiguration.class
})
public class ShopRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopRabbitmqApplication.class, args);
    }

}
