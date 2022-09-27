package com.shop.rabbitmq.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者 - 保证消息可靠投递配置
 * (保证消息的一致性)
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class RabbitMqConfig {
    private final ConnectionFactory connectionFactory;
    //private final RabbitLogsMapper rabbitLogsMapper;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //confirm确认 保证消息发送到exchange(交换机)
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            assert correlationData != null;
            String msgId = correlationData.getId();
            if (ack) {
                //发送成功
                log.info("消息成功发送 , msgId: {},", msgId);
                //状态更新  消息发送成功A
//                BiddingRabbitLogs biddingRabbitLogs = new BiddingRabbitLogs();
//                biddingRabbitLogs.setStatus(SendStatus.SEND_SUCCESS.getValue());
//                rabbitLogsMapper.update(biddingRabbitLogs, Wrappers.lambdaUpdate(BiddingRabbitLogs.class).eq(BiddingRabbitLogs::getId,msgId).notIn(BiddingRabbitLogs::getStatus,"4"));
            } else {
                //发送失败
                log.error("消息发送失败, {}, cause: {}, msgId: {}", correlationData, cause, msgId);
                //状态更新  消息发送失败
//                BiddingRabbitLogs biddingRabbitLogs = new BiddingRabbitLogs();
//                biddingRabbitLogs.setStatus(SendStatus.SEND_FAILD.getValue());
//                rabbitLogsMapper.update(biddingRabbitLogs, Wrappers.lambdaUpdate(BiddingRabbitLogs.class).eq(BiddingRabbitLogs::getId,msgId).notIn(BiddingRabbitLogs::getStatus,"4"));
            }
        });
        rabbitTemplate.setMandatory(true);
        //触发回调  只有交换机找不到队列时才会触发 确保消息从交换机路由到队列 Queue
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
            //状态更新 消息发送失败
            String msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
//            BiddingRabbitLogs biddingRabbitLogs = new BiddingRabbitLogs();
//            biddingRabbitLogs.setStatus(SendStatus.SEND_FAILD.getValue());
//            rabbitLogsMapper.update(biddingRabbitLogs, Wrappers.lambdaUpdate(BiddingRabbitLogs.class).eq(BiddingRabbitLogs::getId, msgId).notIn(BiddingRabbitLogs::getStatus, "4"));
        });
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        /*
         *  autoStartup 必须要设为 true ，否则Spring容器不会加载RabbitAdmin类
         */
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

//    @Bean
//    public MessagePostProcessor correlationIdProcessor() {
//        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message, Correlation correlation) {
//                MessageProperties messageProperties = message.getMessageProperties();
//
//                if (correlation instanceof CorrelationData) {
//                    String correlationId = ((CorrelationData) correlation).getId();
//                    messageProperties.setCorrelationId(correlationId);
//                }
//                // 可以设置持久化，但与本文无关，因此没有附上
//                return message;
//            }
//
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                return message;
//            }
//        };
//        return messagePostProcessor;
//    }


}
