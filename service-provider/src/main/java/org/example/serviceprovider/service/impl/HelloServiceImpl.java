package org.example.serviceprovider.service.impl;

import org.example.serviceprovider.util.ApiResponse;
import org.example.serviceprovider.entity.UserEntity;
import org.example.serviceprovider.service.HelloService;
import org.example.serviceprovider.feign.UserServiceClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    public UserServiceClient userServiceClient;


    @Override
    public UserEntity findUserById(String id) {
        ApiResponse<UserEntity> response = userServiceClient.findUserinfoById(id);
        if (response.getCode() == 200 && response.getData() != null) {
            return response.getData();
        } else {
            throw new RuntimeException("获取用户失败: " + response.getMessage());
        }
    }
}
