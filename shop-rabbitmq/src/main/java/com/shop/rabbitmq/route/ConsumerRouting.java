package com.shop.rabbitmq.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 4：路由模式 - 消费者
 * 根据routingKey有选择性的接收消息
 * <p>
 * 特点
 * 每个队列根据不同routingKey绑定交换机
 * 消息发送到交换机后通过routingKey发送给特定的队列，然后传到消费者消费。
 * 交换由扇形交换机(fanout)改成直连交换机(direct)
 * <p>
 * Author: wang Y
 * Date: 2022-09-22
 */
@Component
public class ConsumerRouting {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.routing.queue1"),
            exchange = @Exchange("shop.routing.exchange")
    ))
    public void consumer(String msg) {
        System.out.println("4：路由模式（T - 1） - 消费者1" + " 接收到的消息内容：" + msg + "_shop.routing.queue1");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.routing.queue2"),
            exchange = @Exchange("shop.routing.exchange")
    ))
    public void consumerTwo(String msg) {
        System.out.println("4：路由模式（T - 2） - 消费者2" + " 接收到的消息内容：" + msg + "_shop.routing.queue2");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("shop.routing.queue3"),
            exchange = @Exchange("shop.routing.exchange")
    ))
    public void consumerThree(String msg) {
        System.out.println("4：路由模式（T - 3） - 消费者3" + " 接收到的消息内容：" + msg + "_shop.routing.queue3");
    }

}
