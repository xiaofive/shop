//package com.shop.rabbitmq.fanout;
//
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
///**
// * 3：发布订阅模式 - 消费者
// * 一次向多个消费者发送消息
// * 扇形交换机
// * 同一消息能被多个消费者消费
// * Author: wang Y
// * Date: 2022-09-21
// */
//@Component
//public class ConsumerFanout {
//
//    /**
//     * Q - 1
//     * 自动创建：队列，交换机
//     * 且交换机自动与 队列绑定
//     *
//     * @param msg
//     * @Param:
//     * @return: void
//     * @Date: 2022-09-21
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("shop.fanout.queue1"),
//            exchange = @Exchange("shop.fanout.exchange")
//    ))
//    public void consumerOne(String msg) {
//        System.out.println("3：发布订阅模式（T - 1） - 消费者1" + " 接收到的消息内容：" + msg + "_shop.fanout.queue1");
//    }
//
//    /**
//     * Q - 2
//     * 自动创建：队列，交换机
//     * 且交换机自动与 队列绑定
//     *
//     * @param msg
//     * @Param:
//     * @return: void
//     * @Date: 2022-09-21
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("shop.fanout.queue2"),
//            exchange = @Exchange("shop.fanout.exchange")
//    ))
//    public void consumerTwo(String msg) {
//        System.out.println("3：发布订阅模式（T - 2） - 消费者2" + " 接收到的消息内容：" + msg + "_shop.fanout.queue2");
//    }
//
//    /**
//     * Q - 3
//     * 自动创建：队列，交换机
//     * 且交换机自动与 队列绑定
//     *
//     * @param msg
//     * @Param:
//     * @return: void
//     * @Date: 2022-09-21
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("shop.fanout.queue3"),
//            exchange = @Exchange("shop.fanout.exchange")
//    ))
//    public void consumerThree(String msg) {
//        System.out.println("3：发布订阅模式（T - 3） - 消费者3" + " 接收到的消息内容：" + msg + "_shop.fanout.queue3");
//    }
//
//}
