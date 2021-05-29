package com.shop.redis.dao;

public interface RedisDTO {

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

}
