package com.shop.redis.dao.impl;

import com.shop.redis.dao.RedisDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 使用lettuce操作redis的service层实现类，TODO 持续集成
 * Author: wang Y
 * Date: 2021-05-29
 */
@Service("lettuceServiceImpl")
@Slf4j
public class LettuceDTOImpl implements RedisDTO {

    @Autowired
    private RedisTemplate<String, String> rt;

    @Override
    public void set(String logId, String key, String value) {
        try {
            rt.opsForValue().set(key, value);
            log.debug("[logId:{}] SET redis key: {}, value: {}", logId, key, value);
        } catch (Exception e) {
            log.error("[logId:{}] SET redis key: {}, value: {}", logId, key, value, e);
        }
    }

    @Override
    public String get(String logId, String key) {
        String get = null;
        try {
            get = rt.opsForValue().get(key);
            log.debug("[logId:{}] GET redis key: {}, result: {}", logId, key, get);
        } catch (Exception e) {
            log.error("[logId:{}] GET redis key: {}", logId, key, e);
        }
        return get;
    }
}
