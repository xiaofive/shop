package com.shop.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 5：主题模式 - config
 * 路由模式发送的消息，是需要指定固定的routingKey，如果想要针对一类路由。
 * 比如：
 * <p>
 * 只接收以.com结尾的消息。
 * <p>
 * www.开头的消息。
 * <p>
 * 主题模式就派上场了，路由模式和主题模式类似，路由模式是设置特定的routingKey绑定唯一的队列，而主题模式的是使用通配符匹配一个或者多个队列
 * Author: wang Y
 * Date: 2022-09-24
 */
@Configuration
public class TopicMqConfig {

    //创建交换机和队列：

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("shop.topic.exchange");
    }

    @Bean
    public Queue topicOneQueue() {
        return new Queue("shop.topic.queue1");
    }

    @Bean
    public Queue topicTwoQueue() {
        return new Queue("shop.topic.queue2");
    }

    @Bean
    public Queue topicThreeQueue() {
        return new Queue("shop.topic.queue3");
    }

    // 使用通配符绑定交换机和交换机：
//    通配符有两种，*和#,
//
//            * 表示可以匹配一个。
//
//            # 表示可以匹配多个

    @Bean
    public Binding topicOneBind() {
        //.com 为结尾
        return BindingBuilder.bind(topicOneQueue()).to(topicExchange()).with("*.com");
    }

    @Bean
    public Binding topicTwoBind() {
        //www.为开头
        return BindingBuilder.bind(topicTwoQueue()).to(topicExchange()).with("www.#");
    }

}
