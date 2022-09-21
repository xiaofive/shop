package com.shop.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 2：工作模式 - 消费者
 * 一对多
 * 资源的竞争,多个消费者消费同一个队列的消息
 * 队列里的消息只能被一个消费者消费
 * Author: wang Y
 * Date: 2022-09-21
 */
@Component
public class ConsumerWork {

    /**
     * Q - 1
     * 自动创建：队列，交换机
     * 且交换机自动与 队列绑定
     *
     * @param msg
     * @Param:
     * @return: void
     * @Date: 2022-09-21
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.work.queue"),
            exchange = @Exchange("shop.work.exchange")
    ))
    public void consumerOne(String msg) {
        System.out.println("2：工作模式（T - 1） - 消费者1" + " 接收到的消息内容：" + msg + "_shop.work.queue");
    }

    /**
     * Q - 2
     * 自动创建：队列，交换机
     * 且交换机自动与 队列绑定
     *
     * @param msg
     * @Param:
     * @return: void
     * @Date: 2022-09-21
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.work.queue"),
            exchange = @Exchange("shop.work.exchange")
    ))
    public void consumerTwo(String msg) {
        System.out.println("2：工作模式（T - 2） - 消费者2" + " 接收到的消息内容：" + msg + "_shop.work.queue");
    }

}
