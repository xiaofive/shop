package com.shop.rabbitmq.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 1：简单模式 - 生产者
 * Author: wang Y
 * Date: 2022-08-28
 */
@Component
public class ProducerSimple {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 当我们将消息发送到RabbitMQ并且队列不存在时，消息会丢失而不会丢失任何错误。
     *
     * @Param:
     * @param num
     * @return: void
     * @Date: 2022-09-17
     */
    public void producer1(Integer num) {
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend("shop.simple.queue1","这是：1：简单模式 - 生产者，发送的第" + (i+1) + "条消息！");
        }
    }

}
