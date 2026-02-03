package org.example.servercustomer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    // 监听指定队列
//    @RabbitListener(queues = "demo.queue", autoStartup = "true")
    public void handleMessage(String message) {
        System.out.println("收到消息: " + message);
        // 业务处理逻辑
    }

}
