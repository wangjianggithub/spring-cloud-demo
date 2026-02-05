package org.example.serviceprovider.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.example.serviceprovider.redis.RedisCacheConfig;
import org.example.serviceprovider.entity.UserEntity;
import org.example.serviceprovider.mapper.UserInfoMapper;
import org.example.serviceprovider.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private RedisCacheConfig cacheConfig;

    @Override
    @GlobalTransactional
    public UserEntity saveUserInfo() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID().toString());
        user.setName("李四");
        user.setAge("21");
        user.setEmail("http://123.com");
        user.setPassword("234");
        userInfoMapper.insertUser(user);
        return userInfoMapper.queryList(user.getId());
    }

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
        // 这里应该是实际的数据库查询
        // return userRepository.findById(userId).orElse(null);

        UserEntity u = userInfoMapper.queryList(userId);
        // 模拟数据库查询
        return userInfoMapper.queryList(userId);
    }

}
