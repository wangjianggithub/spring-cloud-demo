package org.example.serviceprovider.util;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMq 工具类
 *
 * @author wj
 * @date 2026/1/19
 */
public class RabbitmqUtils {
    private static Channel channel = null;
    private static Connection connection = null;

    /**
     * 获取连接
     *
     * @author Klay
     * @date 2023/6/25 10:37
     */
    public static Channel getChannel() {
        //定义连接池
        ConnectionFactory factory = new ConnectionFactory();
        //设置主机地址
        factory.setHost("172.31.38.196");
        //设置端口
        factory.setPort(5672);
        //设置用户名
        factory.setUsername("guest");
        //密码
        factory.setPassword("guest");
        //虚拟机路径
        factory.setVirtualHost("/");
        try {
            connection = factory.newConnection();
            //创建信道
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * 关闭连接
     *
     * @author Klay
     * @date 2023/6/25 10:37
     */
    public static void closeConnection() {
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
