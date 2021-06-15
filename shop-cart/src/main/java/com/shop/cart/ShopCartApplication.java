package com.shop.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 购物车服务
 *
 * Author: wang Y
 * Date: 2021-06-14
 */
@SpringBootApplication(
        exclude = { //1.排除SpringBoot自带的数据源配置
                DataSourceAutoConfiguration.class
        },
        scanBasePackages = { //2.spring boot默认的扫描路径是启动类当前的包和子包,由于商品中心有些配置bean需要从common中加载到Spring容器中,故添加common包相关配置bean路径
                "com.shop.cart"
        },
        scanBasePackageClasses = {
                com.shop.common.config.mybatis.MybatisPlusConfig.class  //common模块Mybatis-Plus配置类
        }
)
@MapperScan(basePackages = {  //mapper文件扫描，按需添加
        "com.shop.cart.cart.mapper"
})
@EnableDiscoveryClient
@EnableFeignClients
public class ShopCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopCartApplication.class, args);
    }

}
