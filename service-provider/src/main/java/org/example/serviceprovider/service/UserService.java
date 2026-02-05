package org.example.serviceprovider.service;

import org.example.serviceprovider.entity.UserEntity;

public interface UserService {

    UserEntity saveUserInfo();

    UserEntity getUserById(String id);
}
