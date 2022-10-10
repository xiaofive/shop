package com.shop.rabbitmq.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shop.rabbitmq.log.bean.entity.RabbitMQLog;
import com.shop.rabbitmq.log.mapper.RabbitMQLogEntityMapper;
import com.shop.rabbitmq.log.service.RabbitMQLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 消息操作
 */
@Service
public class RabbitMQLogServiceImpl implements RabbitMQLogService {

    @Resource
    private RabbitMQLogEntityMapper rabbitMQLogEntityMapper;

    @Override
    public void save(RabbitMQLog rabbitMQLog) {
        rabbitMQLogEntityMapper.insert(rabbitMQLog);
    }

    @Override
    public RabbitMQLog getRabbitMqLog(String messageId) {
        QueryWrapper<RabbitMQLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(messageId), "message_id", messageId);
        return rabbitMQLogEntityMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateByMsgId(String messageId) {
       RabbitMQLog rabbitMQLog = getRabbitMqLog(messageId);
       if (Objects.isNull(rabbitMQLog))
           return;
       rabbitMQLogEntityMapper.updateById(rabbitMQLog);
    }

}
