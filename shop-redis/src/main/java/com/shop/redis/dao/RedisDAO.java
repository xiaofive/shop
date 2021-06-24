package com.shop.redis.dao;

import java.util.Map;

public interface RedisDAO {

    /**
     * redis中的set方法
     *
     * @param logId 日志id
     * @param key   key
     * @param value value
     * @return: void
     * @Date: 2021-05-29
     */
    void set(String logId, String key, String value);

    /**
     * redis中的get方法
     *
     * @param logId
     * @param key
     * @Param:
     * @return: java.lang.String
     * @Date: 2021-05-29
     */
    String get(String logId, String key);

    /**
     * redis中的hset方法
     *
     * @param logId 日志id
     * @param key   key
     * @param field field
     * @param value value
     */
    void hset(String logId, String key, String field, String value);

    /**
     * redis中的hget方法
     *
     * @param logId 日志id
     * @param key   key
     * @param field field
     * @return 操作结果
     */
    String hget(String logId, String key, String field);

    /**
     * redis中的hgetAll方法
     *
     * @param logId 日志id
     * @param key   key
     * @return 操作结果
     */
    Map<String, String> hgetAll(String logId, String key);

    /**
     * redis中的hdel方法
     *
     * @param logId 日志id
     * @param key   key
     * @param field field
     * @return 操作结果
     */
    Long hdel(String logId, String key, String field);

    /**
     * redis中的watch方法
     *
     * @param logId
     * @param key
     * @return: java.lang.Long
     * @Date: 2021-06-18
     */
    void watch(String logId, String key);

    /**
     * redis中的unWatch方法
     *
     * @param logId
     * @return: void
     * @Date: 2021-06-18
     */
    void unWatch(String logId);

    /**
     * redis中的multi方法
     *
     * @param logId
     * @return: void
     * @Date: 2021-06-18
     */
    void multi(String logId);

}
