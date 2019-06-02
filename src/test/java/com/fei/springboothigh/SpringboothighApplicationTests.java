package com.fei.springboothigh;

import com.fei.springboothigh.config.database.MybatisScan;
import com.fei.springboothigh.entity.Employee;
import com.fei.springboothigh.enums.SexEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringboothighApplicationTests {

    @Autowired
    private MybatisScan mybatisScan;

    // rabbit操作模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // rabbit组件管理
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {
    }

    @Test
    @Ignore
    public void test() {
        SexEnum sexEnum = null;
        Integer integer = Optional.ofNullable(sexEnum).map(SexEnum::getSexCode).orElse(null);
        System.out.println(integer);
    }

    @Test
    @Ignore
    public void test2() {
        List<Integer> integers = new ArrayList<>();
        List<Integer> integers2 = null;
        // 这样会报错，因为集合并不是空，而是长度为零
        Integer integer = Optional.ofNullable(integers).map(list -> list.get(0)).orElse(null);
        // 不会报错，为null的时候可以避免
        Integer integer2 = Optional.ofNullable(integers2).map(list -> list.get(0)).orElse(null);
        System.out.println(integer);
    }

    /**
     * 测试@Configuration配置类本身会不会被自动加进IOC容器 结果：会根据构造方法将其加入IOC容器中
     */
    @Test
    @Ignore
    public void test3() {
        System.out.println(mybatisScan.getClass());
    }

    /**
     * rabbitMQ发送消息测试
     */
    @Test
    @Ignore
    public void rabbitTest() {
        /**
         * 发送消息，第一个参数：交给哪个交换机处理，第二个参数：消息的路由键，第三个参数：消息对象（Object类型），这里发送字符串
         */
        rabbitTemplate.convertAndSend("rabbitmq.direct", "queue.test3", "SpringBoot发送消息，哈喽沃德");
    }

    /**
     * rabbitMQ发送消息测试
     */
    @Test
    @Ignore
    public void rabbitTest2() {
        /**
         * 发送消息，第一个参数：交给哪个交换机处理，第二个参数：消息的路由键，第三个参数：消息对象（Object类型），这里发送具体对象
         */
        Employee employee = new Employee();
        employee.setEmployeeId(2);
        employee.setEmployeeName("鱼仔");
        employee.setEmployeeSex(1);
        rabbitTemplate.convertAndSend("rabbitmq.direct", "queue.test4", employee);
    }

    /**
     * rabbitMQ接受消息测试
     */
    @Test
    @Ignore
    public void rabbitTest3() {
        // 接受消息，参数：队列名
        Object o = rabbitTemplate.receiveAndConvert("queue.test4");
        if (o != null) {
            System.out.println(o.getClass());
            Employee employee = (Employee) o;
            System.out.println(employee.getEmployeeName());
        }
    }

    /**
     * rabbitMQ使用AMQPAdmin管理组件
     */
    @Test
    @Ignore
    public void rabbitTest4() {
        // new一个Direct类型的交换器，指定名称，其他持久化默认true，自动删除默认false
        DirectExchange exchange = new DirectExchange("springboot.rabbitmq.direct");
        // 新建一个交换器，参数类型Exchange
        amqpAdmin.declareExchange(exchange);
    }

    /**
     * rabbitMQ使用AMQPAdmin管理组件
     */
    @Test
    @Ignore
    public void rabbitTest5() {
        // new一个消息队列Queue，指定名称，其他持久化默认true，自动删除默认false
        Queue queue = new Queue("springboot.queue.test");
        // 新建一个消息队列Queue，参数类型Queue
        amqpAdmin.declareQueue(queue);
    }

    /**
     * rabbitMQ使用AMQPAdmin管理组件
     */
    @Test
    @Ignore
    public void rabbitTest6() {
        // 第一个参数：目的地（Queue名）；第二个参数：目的地类型（Queue或者其他）；第三个参数：交换器名；第四个参数：绑定键（Routing Key）；最后一个参数：指定一些参数，这里不设置
        Binding binding = new Binding("springboot.queue.test", Binding.DestinationType.QUEUE,
                "springboot.rabbitmq.direct", "springboot.news", null);
        // 新建一个Binding绑定关系，参数类型Binding
        amqpAdmin.declareBinding(binding);
    }

    /**
     * 新建的组件测试，rabbitMQ发送消息
     */
    @Test
    @Ignore
    public void rabbitTest7() {
        /**
         * 发送消息，第一个参数：交给哪个交换机处理，第二个参数：消息的路由键，第三个参数：消息对象（Object类型），这里发送具体对象
         */
        Employee employee = new Employee();
        employee.setEmployeeId(2);
        employee.setEmployeeName("鱼仔");
        employee.setEmployeeSex(1);
        rabbitTemplate.convertAndSend("springboot.rabbitmq.direct", "springboot.news", employee);
    }
}
