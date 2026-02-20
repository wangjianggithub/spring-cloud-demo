package org.example.serviceprovider.service;

import org.example.serviceprovider.entity.po.UserEntity;

public interface HelloService {

    UserEntity findUserById(String id);

}
