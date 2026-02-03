package org.example.servercustomer.service.impl;

import org.example.servercustomer.entity.UserEntity;
import org.example.servercustomer.mapper.UserInfoMapper;
import org.example.servercustomer.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserEntity findUserinfoById(String id) {
        return userInfoMapper.findUserinfoById(id);
    }
}
