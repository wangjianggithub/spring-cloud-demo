package org.example.serviceprovider.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.example.serviceprovider.entity.Dto.OrderDto;
import org.example.serviceprovider.entity.po.OrderEntity;
import org.example.serviceprovider.feign.StoreServiceClient;
import org.example.serviceprovider.mapper.OrderMapper;
import org.example.serviceprovider.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
//@Transactional
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StoreServiceClient storeServiceClient;

//    @GlobalTransactional
//    @Transactional(propagation = Propagation.REQUIRED,
//        isolation = Isolation.READ_COMMITTED,
//        rollbackFor = Exception.class)
    @GlobalTransactional(name = "create-order" , rollbackFor = Exception.class)
    @Override
    public Boolean saveOrder(OrderDto order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setOrderName(order.getOrderName());
        Boolean orderFlag = orderMapper.saveOrder(orderEntity);
        //调取库存服务
        storeServiceClient.decrease(order.getOrderId(), order.getCount());
        return orderFlag;

    }

}
