package org.example.servercustomer.service.impl;

import org.example.servercustomer.mapper.StoresMapper;
import org.example.servercustomer.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoresMapper storeMapper;

    @Override
    public boolean procease(String orderId, int amount) {
        boolean pflag = storeMapper.procease(orderId, amount);

        // 模拟异常，测试事务回滚
         int i = 1/0;

        return pflag;
    }

}
