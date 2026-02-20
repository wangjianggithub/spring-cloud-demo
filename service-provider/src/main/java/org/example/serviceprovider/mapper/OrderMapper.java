package org.example.serviceprovider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.serviceprovider.entity.po.OrderEntity;

@Mapper
public interface OrderMapper {

    Boolean saveOrder(OrderEntity order);

}
