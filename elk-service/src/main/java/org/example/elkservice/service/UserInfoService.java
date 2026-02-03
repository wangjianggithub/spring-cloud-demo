package org.example.elkservice.service;

import org.example.elkservice.entity.UserEntity;

public interface UserInfoService {

    UserEntity getUserById(String id);

    UserEntity createUser(UserEntity user);
}
