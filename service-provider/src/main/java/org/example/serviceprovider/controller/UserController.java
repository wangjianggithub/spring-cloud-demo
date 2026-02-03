package org.example.serviceprovider.controller;

import org.example.serviceprovider.entity.UserEntity;
import org.example.serviceprovider.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/api/redis")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }
//
//    @PutMapping("/{id}")
//    public UserEntity updateUser(@PathVariable String id, @RequestBody UserEntity user) {
//        return userService.updateUser(id, user);
//    }
}
