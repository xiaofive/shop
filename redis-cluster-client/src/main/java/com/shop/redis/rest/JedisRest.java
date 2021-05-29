package com.shop.redis.rest;

import com.shop.redis.dao.RedisDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作redis的控制层，使用lettuce操作
 *
 * @author dijia478
 * @date 2020-12-16 11:26
 */
@RequestMapping("/rest/jedis")
@RestController
@Slf4j
@Api(tags = "redis-cluster集群jedis操作的接口")
public class JedisRest {

    @Autowired
    @Qualifier("jedisServiceImpl")
    private RedisDTO redisDTO;

}
