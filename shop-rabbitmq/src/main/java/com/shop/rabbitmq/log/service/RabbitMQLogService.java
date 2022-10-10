package com.shop.rabbitmq.log.service;

import com.shop.rabbitmq.log.bean.entity.RabbitMQLog;

public interface RabbitMQLogService {

    void save(RabbitMQLog rabbitMQLog);

    RabbitMQLog getRabbitMqLog(String messageId);

    void updateByMsgId(String messageId);

}
