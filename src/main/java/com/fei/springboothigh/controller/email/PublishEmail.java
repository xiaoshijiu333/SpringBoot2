package com.fei.springboothigh.controller.email;

import com.fei.springboothigh.services.rabbit.PublishMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/8
 * @Description: 通过RabbitMQ发送邮件
 */
@RestController
public class PublishEmail {

    @Autowired
    private PublishMessage publishMessage;

    @RequestMapping("email")
    public String publishEamil(String to) {
        publishMessage.publisMessage(to);
        return "验证码已经通过邮箱发送给您，谢谢";
    }
}
