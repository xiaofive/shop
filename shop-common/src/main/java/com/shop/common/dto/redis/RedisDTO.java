package com.shop.common.dto.redis;

import lombok.Data;

/**
 * 请求Redis操作的参数bean
 *
 * Author: wang Y
 * Date: 2021-05-29
 */
@Data
public class RedisDTO {

    private String key;

    private String field;

    private String value;

}
