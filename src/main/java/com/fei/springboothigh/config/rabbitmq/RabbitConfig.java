package com.fei.springboothigh.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/2
 * @Description: RabbitMQ配置类，顺带开启Rabbit注解支持
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    /**
     * 配置消息转换器，使用JackJosn提供的json序列化转换器
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
