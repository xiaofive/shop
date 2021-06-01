package com.shop.wms.config;

import com.shop.common.swagger.BaseSwaggerConfig;
import com.shop.common.swagger.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * shop-库存中心 Swagger-ui配置
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
                .apiBasePackage("com.shop.wms")    //扫描生成API文档的包路径
                .title("库存中心")
                .description("库存中心后台管理相关接口文档")
                .contactName("shop-product")
                .version("1.0")
                .enableSecurity(false) //TODO 未实现会员中心，暂无需登陆
                .build();
    }

}
