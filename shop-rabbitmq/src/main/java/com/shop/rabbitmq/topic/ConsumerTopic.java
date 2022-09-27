package com.shop.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 5：主题模式 - 消费者
 *
 * TODO 消息确认机制：消费者在绑定队列时 可以指定 【AutoAck】 参数
 * <p>
 * Author: wang Y
 * Date: 2022-09-24
 */
@Component
public class ConsumerTopic {

    @RabbitListener(queues = "shop.topic.queue1")
    public void consumerOne(String msg) {
        System.out.println("5：主题模式（T - 1） - 消费者1 接收到的消息内容：" + msg + "_shop.topic.queue1");
    }

    @RabbitListener(queues = "shop.topic.queue2")
    public void consumerTwo(String msg) {
        System.out.println("5：主题模式（T - 2） - 消费者2 接收到的消息内容：" + msg + "_shop.topic.queue2");
    }

}
