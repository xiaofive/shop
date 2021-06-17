package com.shop.cart.feign;

import com.shop.common.dto.redis.RedisDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;


/**
 * shop-redis模块服务
 * <p>
 * Author: wang Y
 * Date: 2021-06-15
 */
@FeignClient(
        name = "redis-cluster-client",
        fallback = ResdisClusterFeignClient.DefaultFallback.class,
        decode404 = true //避免对正常的业务异常进行熔断处理，可以重写一下Feign的errorDecoder
)
public interface ResdisClusterFeignClient {

    @PostMapping("/rest/lettuce/redis/lettuce/set")
    void set(com.shop.common.dto.redis.RedisDTO redisDTO);

    @GetMapping("/rest/lettuce/redis/lettuce/get/{key}")
    String get(@PathVariable("key") String key);

    @GetMapping("/rest/lettuce/redis/lettuce/testFeignFallBack")
    String testFeignFallBack();

    @PostMapping("/redis/lettuce/hset")
    void hset(RedisDTO redisDTO);

    @GetMapping("/rest/lettuce/redis/lettuce/hget/{key}/{field}")
    Object hget(@PathVariable("key") String key, @PathVariable("field") String field);

    @GetMapping("/rest/lettuce/redis/lettuce/hgetAll/{key}")
    Map<String, String> hgetAll(@PathVariable("key") String key);

    @DeleteMapping("/rest/lettuce/redis/lettuce/hdel/{key}/{field}")
    Long hdel(@PathVariable("key") String key, @PathVariable("field") String field);

    /**
     * shop-redis 降级服务
     * 当后端一个接口响应非常慢的时候,那么请求该接口的时候会被强制等待,直到返回或者超时结束.若在高负载的情况下,如果不做处理的话,这些问题会导致系统崩溃.
     * Feign已经为我们集成了Hystrix,默认就会使用断路器包裹所有方法
     * TODO：熔断原因打印记录 Feign使用fallbackFactory属性打印fallback异常;
     * TODO：什么异常才进行熔断
     * TODO：熔断阈值、参数配置
     * <p>
     * Author: wang Y
     * Date: 2021-06-15
     */
    @Component
    class DefaultFallback implements ResdisClusterFeignClient {

        @Override
        public void set(RedisDTO redisDTO) {
            System.out.println("熔断降级返回：set fail");
        }

        @Override
        public String get(String key) {
            return "熔断降级返回：get fail";
        }

        @Override
        public String testFeignFallBack() {
            return "熔断降级返回：testFeignFallBack";
        }

        @Override
        public void hset(RedisDTO redisDTO) {

        }

        @Override
        public Object hget(String key, String field) {
            return "熔断降级返回：hget fail";
        }

        @Override
        public Map<String, String> hgetAll(String key) {
            return Collections.emptyMap();
        }

        @Override
        public Long hdel(String key, String field) {
            return null;
        }

    }

}