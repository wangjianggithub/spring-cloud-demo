package org.example.servercustomer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StoresMapper {

    boolean procease(@Param("orderId") String orderId, @Param("amount") int amount);

}
