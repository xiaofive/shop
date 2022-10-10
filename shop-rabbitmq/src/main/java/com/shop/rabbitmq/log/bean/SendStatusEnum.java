package com.shop.rabbitmq.log.bean;

import io.swagger.models.auth.In;
import lombok.Getter;

/**
 * RabbitMQ - 消息状态 枚举类
 */
@Getter
public enum SendStatusEnum {

    SEND_ING(1, "消息发送中"),
    SEND_SUCCESS(2, "消息发送成功"),
    SEND_FAIL(3, "消息发送失败"),
    CONSUMER_SUCCESS(4, "消费成功"),
    CONSUMER_FAIL(5, "消费失败");

    private final Integer status;
    private final String desc;

    SendStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static String getDesc(Integer status) {
        for (SendStatusEnum sendStatusEnum : SendStatusEnum.values()) {
            if (sendStatusEnum.getStatus().equals(status)) {
                return sendStatusEnum.getDesc();
            }
        }
        return "";
    }

    public static Integer maxtrys(){
        return 5;
    }

}
