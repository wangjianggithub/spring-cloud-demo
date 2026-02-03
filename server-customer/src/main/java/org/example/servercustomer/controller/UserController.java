package org.example.servercustomer.controller;

import com.rabbitmq.client.*;
import org.example.servercustomer.util.ApiResponse;
import org.example.servercustomer.entity.UserEntity;
import org.example.servercustomer.mq.RabbitmqUtils;
import org.example.servercustomer.service.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/user/{id}")
    public ApiResponse<UserEntity> hello(@PathVariable String id) {
        return ApiResponse.success(userInfoService.findUserinfoById(id));
    }

    @GetMapping("/customerMessage")
    public void customerMessage() throws TimeoutException, IOException {
        Channel channel = RabbitmqUtils.getChannel();
        try {
            Consumer consummer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){
                    System.out.println("接收到的消息是:"+new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            channel.basicConsume("demo.queue", consummer);
            //等待回调函数执行完毕之后，关闭资源
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            channel.close();
        }
    }

    @GetMapping("/info")
    public ApiResponse<UserEntity> getUserInfo(@RequestHeader("X-User-Id") String userId,
                              @RequestHeader("X-Username") String username) {

        UserEntity userInfo = new UserEntity();
        userInfo.setId(userId);
        userInfo.setName(username);
        userInfo.setEmail(username + "@example.com");

        return ApiResponse.success(userInfo);
    }

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "欢迎访问用户仪表盘";
    }

    // 生成跳转到其他服务的链接
    @GetMapping("/navigation")
    public ApiResponse<Map<String, String>> getNavigationLinks(@RequestHeader("X-User-Id") String userId) {
        Map<String, String> links = new HashMap<>();
        links.put("订单服务", "http://localhost:8080/order/dashboard");
        links.put("商品服务", "http://localhost:8080/product/catalog");
        links.put("个人中心", "http://localhost:8080/user/info");

        return ApiResponse.success(links);
    }

}
