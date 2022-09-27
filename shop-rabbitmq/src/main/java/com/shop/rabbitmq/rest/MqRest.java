package com.shop.rabbitmq.rest;

import com.shop.rabbitmq.fanout.ProducerFanout;
import com.shop.rabbitmq.route.ProducerRoute;
import com.shop.rabbitmq.simple.ProducerSimple;
import com.shop.rabbitmq.topic.ProducerTopic;
import com.shop.rabbitmq.work.ProducerWork;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 消息队列 - 工作模式
 * 1：简单模式
 * 2：work工作模式
 * 3：pub/sub发布订阅模式
 * 4：Routing路由模式
 * 5：Topics主题模式
 * <p>
 * 1.简单模式
 * <p>
 * 无需创建交换机，匹配生产端和消费的routingKey即可。
 * <p>
 * 2.工作模式
 * <p>
 * 多个消费端公平竞争同一个消息。
 * <p>
 * 3.发布订阅模式
 * <p>
 * 一次向多个消费者发送消息。
 * <p>
 * 4.路由模式
 * <p>
 * 根据特定的路由键转发消息。
 * <p>
 * 5.主题模式
 * <p>
 * 根据通配符，匹配路由键转发消息。
 * <p>
 *     TODO SHOW DOC文档
 * Author: wang Y
 * Date: 2022-08-28
 */
@Slf4j
@RequestMapping("/rest/mq")
@RestController
public class MqRest {

    @Resource
    private ProducerSimple producerSimple;
    @Resource
    private ProducerWork producerWork;
    @Resource
    private ProducerFanout producerFanout;
    @Resource
    private ProducerRoute producerRoute;
    @Resource
    private ProducerTopic producerTopic;

    /**
     * 1：简单模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-08-28
     */
    @GetMapping("simpleTest")
    public void simpleTest(@RequestParam(name = "num") Integer num) {
        producerSimple.producerOne(num);
    }

    /**
     * 2：工作模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-09-22
     */
    @GetMapping("workTest")
    public void workTest(@RequestParam(name = "num") Integer num) {
        producerWork.producerOne(num);
    }

    /**
     * 3：发布订阅模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-09-23
     */
    @GetMapping("fanoutTest")
    public void fanoutTest(@RequestParam(name = "num") Integer num) {
        producerFanout.producerOne(num);
    }

    /**
     * 4：路由模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-09-24
     */
    @GetMapping("routeTest")
    public void routeTest(@RequestParam(name = "num") Integer num) {
        producerRoute.producerOne(num);
    }

    /**
     * 5：主题模式 - 发送消息测试
     *
     * @Param:
     * @return: void
     * @Date: 2022-09-24
     */
    @GetMapping("topicTest")
    public void topicTest(@RequestParam(name = "num") Integer num) {
        producerTopic.producerOne(num);
    }


}
