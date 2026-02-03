package org.example.serviceprovider.service;

import org.example.serviceprovider.entity.UserEntity;

public interface HelloService {

    UserEntity findUserById(String id);

}
