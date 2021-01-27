package com.wms.common.util;

import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


/**
 * 签名工具类
 *
 * @author ZhangHao
 * @date 2019-09-29
 */
public class SignUtils {

    /**
     * sign生成算法（map中键值参数不可为空）
     *
     * @author ZhangHao
     * @date 2019-09-29
     */
    public static String generateSign(Map<String, String> parameters, String appSecret) {
        // 将参数键按ASCII值排序
        Set<String> keys = parameters.keySet();
        Object[] keyArray = keys.toArray();
        Arrays.sort(keyArray);

        // 将排序好的数据键值对拼接成一个字符串，最后拼接appSecret
        StringBuilder sb = new StringBuilder();
        for (Object key : keyArray) {
            if (key instanceof String) {
                String keyStr = (String) key;
                String value = parameters.get(keyStr);
                sb.append(keyStr).append(value);
            }
        }
        sb.append(appSecret);

        // 使用MD5加密，并将字母全转为大写
        String md5 = DigestUtils.md5DigestAsHex(sb.toString().getBytes()).toUpperCase();

        // 返回数据
        return md5;
    }

}