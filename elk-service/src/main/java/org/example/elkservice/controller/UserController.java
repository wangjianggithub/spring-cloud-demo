package org.example.elkservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.elkservice.entity.UserEntity;
import org.example.elkservice.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private UserInfoService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据ID获取用户信息")
    public UserEntity getUser(
            @Parameter(description = "用户ID", required = true, example = "1")
            @PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ApiOperation("创建用户")
    public UserEntity createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "用户信息", required = true)
            @RequestBody UserEntity user) {
        return userService.createUser(user);
    }
}
