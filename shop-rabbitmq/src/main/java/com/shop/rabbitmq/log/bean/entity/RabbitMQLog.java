package com.shop.rabbitmq.log.bean.entity;

import com.shop.common.bean.base.BaseEntity;
import lombok.Data;

/**
 * RabbitMQ消息日志
 */
@Data
public class RabbitMQLog extends BaseEntity {

    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 队列
     */
    private String queue;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 重试次数（消息发送 or 消费失败 -> 重试次数） TODO 生产者 消费者代码可能在不同的服务（公司之间） -> 日志服务是否单独抽出，通过http调用，
     * TODO 否则要么生产者，要么消费者，只能兼顾一方
     */
    private Integer reTryTimes;
    /**
     * 状态
     */
    private Integer status;

}
