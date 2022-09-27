package com.shop.rabbitmq.topic;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessageDeliveryMode;
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

        //TODO 发送消息前首先将发送的数据插入数据库，状态变为发送中 save
        for (int i = 0; i < num; i++) {

            String dataOne = "这是：5：主题模式 - 生产者1，发送的第" + (i + 1) + "条消息！ routingKey = " + "www.taobao.com";
            try {
                rabbitTemplate.convertAndSend("shop.topic.exchange", "www.taobao.com",
                        dataOne,
                        message -> {
                            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 消息持久化
                            return message;
                        }
                        , correlationData);
            } catch (AmqpException e) {
                //rabbitLogService.removeById 从数据库中删除 ？？
            }

            try {
                String dataTwo = "这是：5：主题模式 - 生产者2，发送的第" + (i + 1) + "条消息！ routingKey = " + "taobao.com";
                rabbitTemplate.convertAndSend("shop.topic.exchange", "taobao.com1",
                        dataTwo,
                        message -> {
                            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 消息持久化
                            return message;
                        }
                        , correlationData);
            } catch (AmqpException e) {
                //rabbitLogService.removeById 从数据库中删除 ？？
            }

            try {
                String dataThree = "这是：5：主题模式 - 生产者3，发送的第" + (i + 1) + "条消息！ routingKey = " + "www.jd";
                rabbitTemplate.convertAndSend("shop.topic.exchange", "www.jd",
                        dataThree,
                        message -> {
                            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT); // 消息持久化
                            return message;
                        }
                        , correlationData);
            } catch (AmqpException e) {
                //rabbitLogService.removeById 从数据库中删除 ？？
            }
        }
    }

}
