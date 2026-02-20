package org.example.serviceprovider.service;

import org.example.serviceprovider.entity.Dto.OrderDto;

public interface OrderService {

    Boolean saveOrder(OrderDto order);

}
