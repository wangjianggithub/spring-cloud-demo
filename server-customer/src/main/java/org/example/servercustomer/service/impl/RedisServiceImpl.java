package org.example.servercustomer.service.impl;

import org.example.servercustomer.entity.UserEntity;
import org.example.servercustomer.mapper.UserInfoMapper;
import org.example.servercustomer.service.RedisService;
import org.springframework.stereotype.Service;
import org.example.servercustomer.redis.RedisCacheConfig;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private RedisCacheConfig cacheConfig;

    @Override
    public UserEntity getUserById(String userId) {
        String cacheKey = "user:" + userId;
        long cacheTime = 3600; // 缓存1小时

        return cacheConfig.getCachedData(cacheKey, UserEntity.class, () -> {
            // 从数据库查询用户数据
            return getUserFromDatabase(userId);
        }, cacheTime);
    }

    private UserEntity getUserFromDatabase(String userId) {
        // 模拟数据库查询
        return userInfoMapper.findUserinfoById(userId);
    }

}
