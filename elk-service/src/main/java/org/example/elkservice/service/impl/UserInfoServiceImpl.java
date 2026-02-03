package org.example.elkservice.service.impl;

import org.example.elkservice.entity.UserEntity;
import org.example.elkservice.mapper.UserInfoMapper;
import org.example.elkservice.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserEntity getUserById(String id) {
        return userInfoMapper.getUserById(id);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }
}
