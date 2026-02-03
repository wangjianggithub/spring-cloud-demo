package org.example.gatewayserver.controller;

import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

//    @RequestMapping("/getCurrentUser")
//    public Object getCurrentUser(Authentication authentication) {
//        return authentication.getPrincipal();
//    }

    @GetMapping("do1")
    public String do1(String  str) {
        System.out.println(str);
        if(str.equals("true")){
            throw new RuntimeException("");
        }
        return str;
    }

    @GetMapping("/message")
//    @RolesAllowed("default-roles-login-realm")
//    @RolesAllowed("uma_protection")
    public String message(){
        return "看到我说明你已经登录了";
    }

}

