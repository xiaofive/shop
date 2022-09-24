package com.shop.rabbitmq.route;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 4：路由模式 - 生产者
 * Author: wang Y
 * Date: 2022-09-24
 */
@Component
public class ProducerRoute {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 当我们将消息发送到RabbitMQ并且队列不存在时，消息会丢失而不会丢失任何错误。
     * 使用不同的routingKey 转发到不同的队列
     *
     * @param num
     * @Param:
     * @return: void
     * @Date: 2022-09-17
     */
    public void producerOne(Integer num) {
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend("shop.routing.exchange", "oneRouting", "这是：4：路由模式 - 生产者，发送的第" + (i + 1) + "条消息！ routingKey = "+"oneRouting");
            rabbitTemplate.convertAndSend("shop.routing.exchange", "twoRouting", "这是：4：路由模式 - 生产者，发送的第" + (i + 1) + "条消息！ routingKey = "+"twoRouting");
            rabbitTemplate.convertAndSend("shop.routing.exchange", "threeRouting", "这是：4：路由模式 - 生产者，发送的第" + (i + 1) + "条消息！routingKey = "+"threeRouting");

        }
    }

}
