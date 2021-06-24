package com.shop.redis.rest;

import com.shop.redis.dao.RedisDAO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作redis的控制层，使用lettuce操作
 *
 * Author: wang Y
 * Date: 2021-06-17
 */
@RequestMapping("/rest/redisson")
@RestController
@Slf4j
@Api(tags = "redis-cluster集群redisson操作的接口")
public class RedissonRest {

    @Autowired
    @Qualifier("redissonServiceImpl")
    private RedisDAO redisDAO;

}
