package com.fei.springboothigh.services.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/8
 * @Description: $value$
 */
@Service
public class PublishMessage {

    // rabbit操作模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 给RabbitMQ发送发送消息
     * @param to 收件人邮箱地址
     * @return void
     */
    public void publisMessage(String to) {
        /**
         * 发送消息，第一个参数：交给哪个交换机处理，第二个参数：消息的路由键，第三个参数：消息对象（Object类型），这里发送字符串
         */
        rabbitTemplate.convertAndSend("rabbitmq.email", "email", to);
    }
}
