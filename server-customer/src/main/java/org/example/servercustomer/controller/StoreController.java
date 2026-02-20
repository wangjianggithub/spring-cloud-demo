package org.example.servercustomer.controller;

import org.example.servercustomer.service.StoreService;
import org.example.servercustomer.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StoreController {

    @Resource
    private StoreService storeService;

    @GetMapping("/api/proc")
    public ApiResponse<Boolean> procease(@RequestParam("orderId") String orderId, @RequestParam("amount") Integer amount){
        return ApiResponse.success(storeService.procease(orderId, amount));
    }

}
