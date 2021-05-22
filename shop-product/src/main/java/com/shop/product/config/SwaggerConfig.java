package com.shop.product.config;

import com.shop.common.config.BaseSwaggerConfig;
import com.shop.common.config.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * shop-商品中心 Swagger-ui配置
 *
 * Author: wang Y
 * Date: 2021-05-22
 */
@Configuration
@EnableSwagger2
//@Profile(value = {"dev","test"})   //表示该配置只在 dev ，test 环境下生效
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.shop.product.rest")    //扫描生成API文档的包路径
                .title("商品中心")
                .description("商品中心后台相关接口文档")
                .contactName("shop-product")
                .version("1.0")
                .enableSecurity(false) //TODO 未实现会员中心，暂无需登陆
                .build();
    }

}
