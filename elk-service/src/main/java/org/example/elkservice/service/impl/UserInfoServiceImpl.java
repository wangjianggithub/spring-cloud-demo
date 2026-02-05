package org.example.elkservice.service.impl;

import org.example.elkservice.entity.UserEntity;
import org.example.elkservice.mapper.UserInfoMapper;
import org.example.elkservice.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private LogOperationService logOperationService;

    @Override
    public UserEntity getUserById(String id) {
        UserEntity entity = userInfoMapper.getUserById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("userEntity", String.valueOf(entity));
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        logOperationService.saveOperationLog("查询用户", request.getMethod(), map);
        return entity;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }
}
