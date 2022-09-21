package com.shop.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 1：简单模式 - 消费者
 * 一对一
 * 生产者是消费者是一一对应，也叫做点对点模式
 * 生产者和消费者在发送和接收消息时，只需要指定队列名称，而不需要指定Exchange交换机
 * Author: wang Y
 * Date: 2022-08-28
 */
@Component
public class ConsumerSimple {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * T - 1
     * 自动创建：队列，交换机
     * 且交换机自动与 队列绑定
     *
     * @param msg
     * @Param:
     * @return: void
     * @Date: 2022-09-17
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.simple.queue1"),
            exchange = @Exchange("shop.simple.exchange")
    ))
    public void consumer(String msg) {
        System.out.println("1：简单模式（T - 1） - 消费者 接收到的消息内容：" + msg + "_shop.simple.queue1");
    }

    /**
     * T - 2
     * 自动创建队列
     * 使用默认交换机
     *
     * @param msg
     * @Param:
     * @return: void
     * @Date: 2022-09-17
     */
    @RabbitListener(queuesToDeclare = @Queue("shop.simple.queue2"))
    public void consumer2(String msg) {
        System.out.println("1：简单模式（T - 2） - 消费者 接收到的消息内容：" + msg + "shop.simple.queue2");
    }

    /**
     * T - 3
     * 手动创建，需在RabbitMQ中手动创建myQueue1 队列，否则报错
     * 使用默认交换机
     *
     * @param msg
     * @Param:
     * @return: void
     * @Date: 2022-09-17
     */
    @RabbitListener(queues = "shop.simple.queue3")
    public void consumer3(String msg) {
        System.out.println("1：简单模式（T - 3） - 消费者 接收到的消息内容：" + msg + "shop.simple.queue3");
    }

}
