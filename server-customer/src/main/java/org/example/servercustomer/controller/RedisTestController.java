package org.example.servercustomer.controller;

import org.example.servercustomer.entity.UserEntity;
import org.example.servercustomer.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/redis")
public class RedisTestController {

    @Resource
    private RedisService redisService;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable String id) {
        return redisService.getUserById(id);
    }

}
