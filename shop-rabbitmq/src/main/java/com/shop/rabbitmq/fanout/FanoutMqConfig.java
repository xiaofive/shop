package com.shop.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 3：发布订阅模式 - Config (有配置可以无需手动创建队列与交换机，或者声明消费者时，声明并绑定队列与交换机即可)
 * 发布订阅模式 定义多个队列绑定同一个扇形交换机配置
 * Author: wang Y
 * Date: 2022-09-21
 */
@Configuration
public class FanoutMqConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("shop.fanout.exchange");
    }

    @Bean
    public Queue fanoutOneQueue() {
        return new Queue("shop.fanout.queue1");
    }

    @Bean
    public Queue fanoutTwoQueue() {
        return new Queue("shop.fanout.queue2");
    }

    @Bean
    public Queue fanoutThreeQueue() {
        return new Queue("shop.fanout.queue3");
    }

    @Bean
    public Binding routingFirstBinding() {
        return BindingBuilder.bind(fanoutOneQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingSecondBinding() {
        return BindingBuilder.bind(fanoutTwoQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding routingThirdBinding() {
        return BindingBuilder.bind(fanoutThreeQueue()).to(fanoutExchange());
    }

}
