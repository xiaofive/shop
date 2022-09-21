package com.shop.rabbitmq.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 2：工作模式 - 生产者
 * Author: wang Y
 * Date: 2022-09-21
 */
@Component
public class ProducerWork {

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
    public void producerOne(Integer num) {
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend("shop.work.queue","这是：2：工作模式 - 生产者，发送的第" + (i+1) + "条消息！");
        }
    }

}
