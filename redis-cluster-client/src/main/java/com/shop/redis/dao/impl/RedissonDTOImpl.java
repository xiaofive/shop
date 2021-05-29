package com.shop.redis.dao.impl;

import com.shop.redis.dao.RedisDTO;
import org.springframework.stereotype.Service;

/**
 * 使用redisson操作redis的service层实现类，TODO 持续集成
 * Author: wang Y
 * Date: 2021-05-29
 */
@Service("redissonServiceImpl")
public class RedissonDTOImpl implements RedisDTO {
    @Override
    public void set(String logId, String key, String value) {

    }

    @Override
    public String get(String logId, String key) {
        return null;
    }
}
