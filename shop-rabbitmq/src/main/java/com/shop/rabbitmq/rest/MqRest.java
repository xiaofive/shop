package com.shop.rabbitmq.rest;

import com.shop.rabbitmq.simple.ProducerSimple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息队列 - 工作模式
 * 1：简单模式
 * 2：work工作模式
 * 3：pub/sub发布订阅模式
 * 4：Routing路由模式
 * 5：Topics主题模式
 * <p>
 * Author: wang Y
 * Date: 2022-08-28
 */
@Slf4j
@RequestMapping("/rest/mq")
@RestController
public class MqRest {

    @Resource
    private ProducerSimple producerSimple;

    /**
     * 1：简单模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-08-28
     */
    @GetMapping("simpleTest")
    public void simpleTest(@RequestParam(name = "num") Integer num) {
        producerSimple.producer1(num);
    }


}
