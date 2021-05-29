package com.shop.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Redis-Cluster-Client公共服务
 * Author: wang Y
 * Date: 2021-05-29
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = { //1.排除SpringBoot自带的数据源配置
        DataSourceAutoConfiguration.class
})
public class RedisClusterClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterClientApplication.class, args);
    }

}
