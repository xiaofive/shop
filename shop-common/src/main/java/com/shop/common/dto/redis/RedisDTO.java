package com.shop.common.dto.redis;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 请求Redis操作的参数bean
 *
 * Author: wang Y
 * Date: 2021-05-29
 */
@Accessors(chain = true)
@Data
public class RedisDTO {

    private String key;

    private String field;

    private String value;

}
