package com.shop.rabbitmq.topic;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 5：主题模式 - 生产者
 * <p>
 * Author: wang Y
 * Date: 2022-09-24
 */
@Component
public class ProducerTopic {

    @Resource
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private MessagePostProcessor correlationIdProcessor;

    public void producerOne(Integer num) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("设置ID: " + correlationData.getId());
        System.out.println("发送消息");

        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend("shop.topic.exchange", "www.taobao.com", "这是：5：主题模式 - 生产者1，发送的第" + (i + 1) + "条消息！ routingKey = " + "www.taobao.com"

                    , correlationData);

            rabbitTemplate.convertAndSend("shop.topic.exchange", "taobao.com1", "这是：5：主题模式 - 生产者2，发送的第" + (i + 1) + "条消息！ routingKey = " + "taobao.com"
                    , correlationData);

            rabbitTemplate.convertAndSend("shop.topic.exchange", "www.jd", "这是：5：主题模式 - 生产者3，发送的第" + (i + 1) + "条消息！ routingKey = " + "www.jd"
                    , correlationData);
        }
    }

}
