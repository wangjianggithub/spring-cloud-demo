package org.example.gatewayserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 开启配置热更新
public class HelloController {

    // 注入 Nacos 中的配置项
    @Value("${custom.greeting:Hello}") // 冒号后为默认值，配置未加载时使用
    private String greeting;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        // 使用配置项拼接返回结果
        return greeting + " " + name + "! This is service-provider response.";
    }

}
