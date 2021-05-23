package com.shop.common.util;

import com.baomidou.mybatisplus.core.toolkit.AES;

/**
 * 加密工具
 * <p>
 * Author: wang Y
 * Date: 2021-05-23
 */
public class EncryptUtil {


    /**
     * YML 配置,密钥加密：
     *
     * <p>
     * // 加密配置 mpw: 开头紧接加密内容（ 非数据库配置专用 YML 中其它配置也是可以使用的 ）
     * spring:
     * datasource:
     * url: mpw:qRhvCwF4GOqjessEB3G+a5okP+uXXr96wcucn2Pev6Bf1oEMZ1gVpPPhdDmjQqoM
     * password: mpw:Hzy5iliJbwDHhjLs1L0j6w==
     * username: mpw:Xb+EgsyuYRXw7U7sBJjBpA==
     *
     * // Jar 启动参数（ idea 设置 Program arguments , 服务器可以设置为启动环境变量 ）
     * --mpw.key=d1104d7c3b616f0b
     *
     * @Date: 2021-05-23
     */
    private void config(String serverAddr) {

        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println(randomKey);
        //由于使用Nacos管理配置，所以仅对Nacos相关配置加密即可
        String secretAddr = AES.encrypt(serverAddr, randomKey);
        System.out.println("secretAddr:".concat(secretAddr));

    }

}
