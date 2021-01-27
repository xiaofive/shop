package com.jyzt.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisLockUtils {

    /**
     * tryLock 默认过期时间秒
     *
     * @param redisTemplate
     * @param key
     * @param expireTime
     * @return
     */
    public static boolean tryLock(RedisTemplate redisTemplate, String key, Long expireTime) {
        return tryLock(redisTemplate, key, expireTime, TimeUnit.SECONDS);
    }

    public static boolean tryLock(RedisTemplate redisTemplate, String key, Long expireTime, TimeUnit timeUnit) {
        String identifier = UUID.randomUUID().toString();
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, identifier,
                expireTime, timeUnit);
        log.info("tryLock key = {}, value = {}, expireTime = {}", key, identifier, expireTime);
        return success != null && success;
    }


    /**
     * unLock
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public static boolean unLock(RedisTemplate redisTemplate, String key) {
        Boolean success = redisTemplate.delete(key);
        log.info("unLock key = {}", key);
        return success != null && success;
    }

    public static Long unLock(RedisTemplate redisTemplate, Collection<String> keys) {
        Long count =  redisTemplate.delete(keys);
        log.info("unLock keys = {}", keys.toString());
        return count;
    }
}
