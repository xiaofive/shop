package com.shop.redis;

import com.shop.redis.dao.RedisDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用lettuce操作redis 测试
 * Author: wang Y
 * Date: 2021-05-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LettuceDAOTest {

    @Autowired
    @Qualifier("lettuceServiceImpl")
    private RedisDTO redisDTO;

    @Test
    public void testSet() {
        redisDTO.set(null, "k1", "test123456");
    }

    @Test
    public void testGet() {
        String val = redisDTO.get(null, "k1");
        log.info("key：k1_value:" + val);
    }

}
