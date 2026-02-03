package org.example.servercustomer.service;

import org.example.servercustomer.entity.UserEntity;

public interface UserInfoService {

    UserEntity findUserinfoById(String id);
}
