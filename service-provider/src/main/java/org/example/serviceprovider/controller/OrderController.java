package org.example.serviceprovider.controller;

import org.example.serviceprovider.entity.Dto.OrderDto;
import org.example.serviceprovider.service.OrderService;
import org.example.serviceprovider.util.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public ApiResponse<Boolean> saveOrder(@RequestBody OrderDto order){
        return ApiResponse.success(orderService.saveOrder(order));
    }

}
