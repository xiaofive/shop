package com.shop.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * 营销中心
 *
 * Author: wang Y
 * Date: 2021-06-24
 */
@SpringBootApplication(
        exclude = { //1.排除SpringBoot自带的数据源配置
                DataSourceAutoConfiguration.class
        },
        scanBasePackages = { //2.spring boot默认的扫描路径是启动类当前的包和子包,由于商品中心有些配置bean需要从common中加载到Spring容器中,故添加common包相关配置bean路径
                "com.shop.activity"
        },
        scanBasePackageClasses = {
                com.shop.common.config.mybatis.MybatisPlusConfig.class  //common模块Mybatis-Plus配置类
        }
)
@MapperScan(basePackages = {  //mapper文件扫描，按需添加
        "com.shop.activity.activity.mapper"
})
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class ShopActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopActivityApplication.class, args);
    }

}
