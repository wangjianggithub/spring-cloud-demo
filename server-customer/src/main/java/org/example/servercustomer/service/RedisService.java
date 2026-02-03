package org.example.servercustomer.service;

import org.example.servercustomer.entity.UserEntity;

public interface RedisService {

    UserEntity getUserById(String id);
}
