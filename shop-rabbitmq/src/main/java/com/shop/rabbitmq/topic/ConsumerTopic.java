package com.shop.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.shop.rabbitmq.log.bean.SendStatusEnum;
import com.shop.rabbitmq.log.bean.entity.RabbitMQLog;
import com.shop.rabbitmq.log.service.RabbitMQLogService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 5：主题模式 - 消费者
 * <p>
 * TODO 消息确认机制：消费者在绑定队列时 可以指定 【AutoAck】 参数
 * TODO 发送消息日志，确认消息日志 - 开一个Job定时检索
 * <p>
 * Author: wang Y
 * Date: 2022-09-24
 */
@Slf4j
@Component
public class ConsumerTopic {

    @Resource
    private RabbitMQLogService mqLogService;

    @RabbitListener(queues = "shop.topic.queue1")
    @SneakyThrows
    public void consumerOne(String msg, Message message, Channel channel) {
        System.out.println("5：主题模式（T - 1） - 消费者1 接收到的消息内容：" + msg + "_shop.topic.queue1");
    }

    /**
     * //        channel.basicNack();
     * //        channel.basicAck();
     * //        channel.basicReject();
     * //        void basicAck(long var1, boolean var3) throws IOException;
     * //        void basicNack(long var1, boolean var3, boolean var4) throws IOException;
     * //        void basicReject(long var1, boolean var3) throws IOException;
     *
     * @param msg
     * @param message
     * @param channel
     */
    @SneakyThrows
    @RabbitListener(queues = "shop.topic.queue2")
    public void consumerTwo(String msg, Message message, Channel channel) {
        //消息id
        String msgId = (String) message.getMessageProperties().getHeaders().get("spring_returned_message_correlation");
        //根据消息id查询RabbitMQLog日志表
        RabbitMQLog rabbitMqLog = mqLogService.getRabbitMqLog(msgId);
        if (Objects.isNull(rabbitMqLog)) {
            log.error("消息ID：{}查询null，未经确认的消息，拒绝处理 biddingRabbitLogs", msgId);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        if (rabbitMqLog.getStatus().equals(SendStatusEnum.CONSUMER_SUCCESS.getStatus()) //消费成功
                || rabbitMqLog.getStatus().equals(SendStatusEnum.SEND_FAIL.getStatus())) { //发送失败
            log.error("消息ID：{},重复消费，丢弃处理", msgId);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            return;
        }
        try {
            System.out.println("5：主题模式（T - 2） - 消费者2 接收到的消息内容：" + msg + "_shop.topic.queue2");
            rabbitMqLog.setStatus(SendStatusEnum.CONSUMER_SUCCESS.getStatus());
        } catch (Exception e) {
            log.error("", e);
            if (rabbitMqLog.getReTryTimes() >= SendStatusEnum.maxtrys()) {
                //多次消费不成功 自动接收 避免卡在这里
                log.error("多次消费失败，消息ID：{}", msgId);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                //消费失败，拒收，重回队列 实现重试
                log.error("消费失败，消息ID：{}", msgId);
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            rabbitMqLog.setStatus(SendStatusEnum.CONSUMER_FAIL.getStatus());
            rabbitMqLog.setReTryTimes(rabbitMqLog.getReTryTimes() + 1);
        }
        mqLogService.updateByMsgId(msgId);
    }

}
