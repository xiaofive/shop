package com.shop.redis.rest;

import com.shop.redis.dao.RedisDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * 操作redis的控制层，使用lettuce操作
 *
 * @author dijia478
 * @date 2020-12-16 11:26
 */
@RequestMapping("/rest/lettuce")
@RestController
@Slf4j
@Api(tags = "redis-cluster集群lettuce操作的接口")
public class LettuceRest {

    @Autowired
    @Qualifier("lettuceServiceImpl")
    private RedisDTO redisDTO;

    @ApiOperation(value = "redis的set命令")
    @PostMapping("/redis/lettuce/set")
    public void set(com.shop.common.dto.redis.RedisDTO redisDTO) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive POST request [/redis/lettuce/set], req: {}", logId, redisDTO);
        this.redisDTO.set(logId, redisDTO.getKey(), redisDTO.getValue());
    }

    @ApiOperation(value = "redis的get命令")
    @GetMapping("/redis/lettuce/get/{key}")
    public String get(@PathVariable("key") String key) {
        String logId = UUID.randomUUID().toString();
        log.info("[logId:{}] receive GET request [/redis/lettuce/get/{}]", logId, key);
        String get = redisDTO.get(logId, key);
        log.info("[logId:{}] resp: {}", logId, get);
        return get;
    }

    @ApiOperation(value = "testFeignFallBack")
    @GetMapping("/redis/lettuce/testFeignFallBack")
    public String testFeignFallBack() throws Exception{
        Thread.sleep(2000);
        return "testFeignFallBack";
    }


}
