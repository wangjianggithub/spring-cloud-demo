package org.example.serviceprovider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.rabbitmq.client.Channel;
import org.example.serviceprovider.util.ApiResponse;
import org.example.serviceprovider.entity.UserEntity;
import org.example.serviceprovider.service.HelloService;
import org.example.serviceprovider.util.RabbitmqUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RefreshScope
public class HelloController {

    @Resource
    private HelloService helloService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 测试接口：根据传入的姓名返回问候语
    @GetMapping("/hello/{id}")
    @SentinelResource(value = "hello", blockHandler = "handleBlock")
    public String hello(@PathVariable String id) {
        UserEntity entity = helloService.findUserById(id);
        return "Hello " + entity.getName() + "! This is service-provider response.";
    }

    @GetMapping("/testSimpleQuene")
    public void sendMessage() {
        String queueName = "test.queue";
        String message = "hello! test";
        rabbitTemplate.convertAndSend(queueName, message);
        //利用写好工具类获取信道连接
        Channel channel = RabbitmqUtils.getChannel();
        try {
            channel.basicPublish("demo.exchange", "demo.routingKey", null, message.getBytes());
        } catch (IOException e) {
            System.out.print("发送消息IOException:{}" + e);
        } finally {
            //关闭连接
            RabbitmqUtils.closeConnection();
        }
        System.out.println("消息已发送: " + message);
    }

    @Value("${spring.application.name}")
    private String name;
    @Value("${spring.cloud.nacos.server-addr}")
    private String addr;
    @GetMapping("/test")
    public ApiResponse<String> test(){
        return ApiResponse.success(name + addr);
    }
}
