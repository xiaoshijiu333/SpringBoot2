package com.fei.springboothigh.services.rabbit;

import com.fei.springboothigh.entity.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author: xiaoshijiu
 * @Date: 2019/6/2
 * @Description: 监听RabbitMQ的队列，方便及时收到消息
 */
@Service
public class RabbitListen {

    /**
     * 指定监听哪个消息队列，Queue（数组形式。可以指定多个） 一有消息就会及时收到
     */
    @RabbitListener(queues = "springboot.queue.test")
    public void rabbitListen(Employee employee) {
        System.out.println(employee.getEmployeeName());
    }
}
