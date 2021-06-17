package com.shop.redis.dao.impl;

import com.shop.redis.dao.RedisDAO;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 使用redisson操作redis的service层实现类，TODO 持续集成
 * Author: wang Y
 * Date: 2021-05-29
 */
@Service("redissonServiceImpl")
public class RedissonDAOImpl implements RedisDAO {

    @Override
    public void set(String logId, String key, String value) {

    }

    @Override
    public String get(String logId, String key) {
        return null;
    }

    @Override
    public void hset(String logId, String key, String field, String value) {

    }

    @Override
    public String hget(String logId, String key, String field) {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String logId, String key) {
        return null;
    }

    @Override
    public Long hdel(String logId, String key, String field) {
        return null;
    }


}
