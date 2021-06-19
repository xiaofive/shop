package com.shop.redis.rest;

import com.shop.common.dto.redis.RedisDTO;
import com.shop.redis.dao.RedisDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * 操作redis的控制层，使用lettuce操作
 * <p>
 * Author: wang Y
 * Date: 2021-06-17
 */
@RequestMapping("/rest/lettuce")
@RestController
@Slf4j
@Api(tags = "redis-cluster集群lettuce操作的接口")
public class LettuceRest {

    @Autowired
    @Qualifier("lettuceServiceImpl")
    private RedisDAO redisDAO;

    /**
     * redis的set命令
     *
     * @param redisDTO
     * @return: void
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的set命令")
    @PostMapping("/redis/lettuce/set")
    public void set(com.shop.common.dto.redis.RedisDTO redisDTO) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive POST request [/redis/lettuce/set], req: {}", logId, redisDTO);
        this.redisDAO.set(logId, redisDTO.getKey(), redisDTO.getValue());
    }

    /**
     * redis的get命令
     *
     * @param key
     * @return: java.lang.String
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的get命令")
    @GetMapping("/redis/lettuce/get/{key}")
    public String get(@PathVariable("key") String key) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive GET request [/redis/lettuce/get/{}]", logId, key);
        String get = redisDAO.get(logId, key);
        log.info("[logId:{}] resp: {}", logId, get);
        return get;
    }

    /**
     * redis的hset命令
     *
     * @param redisDTO
     * @return: void
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的hset命令")
    @PostMapping("/redis/lettuce/hset")
    public void hset(@RequestBody RedisDTO redisDTO) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive POST request [/redis/lettuce/hset], requestBody: {}", logId, redisDTO);
        redisDAO.hset(logId, redisDTO.getKey(), redisDTO.getField(), redisDTO.getValue());
    }

    /**
     * redis的hget命令
     *
     * @param key
     * @param field
     * @return: java.lang.Object
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的hget命令")
    @GetMapping("/redis/lettuce/hget/{key}/{field}")
    public String hget(@PathVariable("key") String key, @PathVariable("field") String field) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive GET request [/redis/lettuce/hget/{}/{}]", logId, key, field);
        String hget = redisDAO.hget(logId, key, field);
        log.info("[logId:{}] hget resp: {}", logId, hget);
        return hget;
    }

    /**
     * redis的hgetAll命令
     *
     * @param key
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的hgetAll命令")
    @GetMapping("/redis/lettuce/hgetAll/{key}")
    public Map<String, String> hgetAll(@PathVariable("key") String key) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive GET request [/redis/lettuce/hgetAll/{}]", logId, key);
        Map<String, String> hgetAll = redisDAO.hgetAll(logId, key);
        log.info("[logId:{}] resp: {}", logId, hgetAll);
        return hgetAll;
    }

    /**
     * redis的hdel命令
     *
     * @param key
     * @param field
     * @return: java.lang.Long
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "redis的hdel命令")
    @DeleteMapping("/redis/lettuce/hdel/{key}/{field}")
    public Long hdel(@PathVariable("key") String key, @PathVariable("field") String field) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive DELETE request [/redis/lettuce/hdel/{}/{}]", logId, key, field);
        Long hdel = redisDAO.hdel(logId, key, field);
        log.info("[logId:{}] resp: {}", logId, hdel);
        return hdel;
    }

    /**
     * redis的watch命令
     *
     * @param key
     * @Param:
     * @return: java.lang.Long
     * @Date: 2021-06-18
     */
    @ApiOperation(value = "redis的watch命令")
    @DeleteMapping("/redis/lettuce/watch/{field}")
    public void watch(@PathVariable("key") String key) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive watch request [/redis/lettuce/watch/{}]", logId, key);
        redisDAO.watch(logId, key);
        log.info("[logId:{}]", logId);
    }

    /**
     * redis的unwatch命令
     *
     * @return: void
     * @Date: 2021-06-18
     */
    @ApiOperation(value = "redis的unwatch命令")
    @DeleteMapping("/redis/lettuce/unwatch")
    public void unwatch() {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive DELETE unwatch [/redis/lettuce/unwatch]", logId);
        redisDAO.unWatch(logId);
        log.info("[logId:{}]", logId);
    }

    /**
     * redis的multi命令
     *
     * @return: void
     * @Date: 2021-06-18
     */
    @ApiOperation(value = "redis的multi命令")
    @DeleteMapping("/redis/lettuce/multi")
    public void multi() {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive DELETE unwatch [/redis/lettuce/multi]", logId);
        redisDAO.multi(logId);
        log.info("[logId:{}]", logId);
    }

    /**
     * test redis feign
     *
     * @return: java.lang.String
     * @Date: 2021-06-17
     */
    @ApiOperation(value = "testFeignFallBack")
    @GetMapping("/redis/lettuce/testFeignFallBack")
    public String testFeignFallBack() throws Exception {
        Thread.sleep(2000);
        return "testFeignFallBack";
    }


}
