package com.shop.rabbitmq.route;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 4：路由模式 - Config (有配置可以无需手动创建队列与交换机，或者声明消费者时，声明并绑定队列与交换机即可)
 * 路由模式 创建一个交换机，根据不同的路由规则匹配不同的队列routingExchange,根据不同的routingKey绑定不同的队列
 * 直连交换机
 * Author: wang Y
 * Date: 2022-09-21
 */
@Configuration
public class RoutingMqConfig {

    @Bean
    public DirectExchange routingExchange() {
        return new DirectExchange("shop.routing.exchange");
    }

    @Bean
    public Queue routingOneQueue() {
        return new Queue("shop.routing.queue1");
    }

    @Bean
    public Queue routingTwoQueue() {
        return new Queue("shop.routing.queue2");
    }

    @Bean
    public Queue routingThreeQueue() {
        return new Queue("shop.routing.queue3");
    }

    @Bean
    public Binding routingOneBind() {
        return BindingBuilder.bind(routingOneQueue()).to(routingExchange()).with("oneRouting");
    }

    @Bean
    public Binding routingTwoBind() {
        return BindingBuilder.bind(routingTwoQueue()).to(routingExchange()).with("twoRouting");
    }

    @Bean
    public Binding routingThreeBind() {
        return BindingBuilder.bind(routingThreeQueue()).to(routingExchange()).with("threeRouting");
    }

}
