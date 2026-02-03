package org.example.serviceprovider.controller;

import org.example.serviceprovider.entity.UserEntity;
import org.example.serviceprovider.service.UserService;
import org.example.serviceprovider.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;

@RestController
public class UserInfoController {

    @Resource
    private UserService userService;

    @GetMapping("/findUser")
    public ApiResponse<UserEntity> findUser(){
        UserEntity users = userService.queryList();
        return ApiResponse.success(users);
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://172.31.38.196:3306/seata?useUnicode=true";
        String user = "root";
        String password = "123456";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("连接成功！");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
