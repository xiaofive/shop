package com.shop.redis.dao.impl;

import com.shop.redis.dao.RedisDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 使用lettuce操作redis的service层实现类，TODO 持续集成
 * Author: wang Y
 * Date: 2021-05-29
 */
@Service("lettuceServiceImpl")
@Slf4j
public class LettuceDAOImpl implements RedisDAO {

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


    @Override
    public void hset(String logId, String key, String field, String value) {
        try {
            rt.opsForHash().put(key, field, value);
            log.debug("[logId:{}] HSET redis key: {}, field: {}, value: {}", logId, key, field, value);
        } catch (Exception e) {
            log.error("[logId:{}] HSET redis key: {}, field: {}, value: {}", logId, key, field, value, e);
        }
    }

    @Override
    public String hget(String logId, String key, String field) {
        String hget = null;
        try {
            HashOperations<String, String, String> hash = rt.opsForHash();
            hget = hash.get(key, field);
            log.debug("[logId:{}] HGET redis key: {}, field: {}, result: {}", logId, key, field, hget);
        } catch (Exception e) {
            log.error("[logId:{}] HGET redis key: {}, field: {}", logId, key, field, e);
        }
        return hget;
    }

    @Override
    public Map<String, String> hgetAll(String logId, String key) {
        Map<String, String> hgetAll = null;
        try {
            HashOperations<String, String, String> hash = rt.opsForHash();
            hgetAll = hash.entries(key);
            log.debug("[logId:{}] HGETALL redis key: {}, result: {}", logId, key, hgetAll);
        } catch (Exception e) {
            log.error("[logId:{}] HGETALL redis key: {}", logId, key, e);
        }
        return hgetAll;
    }

    @Override
    public Long hdel(String logId, String key, String field) {
        Long hdel = null;
        try {
            hdel = rt.opsForHash().delete(key, field);
            log.debug("[logId:{}] HDEL redis key: {}, field: {}, result: {}", logId, key, field, hdel);
        } catch (Exception e) {
            log.error("[logId:{}] HDEL redis key: {}, field: {}", logId, key, field, e);
        }
        return hdel;
    }

    @Override
    public void watch(String logId, String key) {
        try {
            rt.watch(key);
            log.debug("[logId:{}] watch redis key: {}", logId, key);
        } catch (Exception e) {
            log.error("[logId:{}] watch redis key: {}", logId, key);
        }
    }

    @Override
    public void unWatch(String logId) {
        try {
            rt.unwatch();
            log.debug("[logId:{}] unWatch redis", logId);
        } catch (Exception e) {
            log.error("[logId:{}] unWatch redis", logId);
        }
    }

    @Override
    public void multi(String logId) {
        try {
            rt.multi();
            log.debug("[logId:{}] multi redis", logId);
        } catch (Exception e) {
            log.error("[logId:{}] multi redis", logId);
        }
    }

}
