package org.example.serviceprovider.entity.Dto;

import lombok.Data;

@Data
public class OrderDto {

    private String orderId;
    private String orderName;
    private int count;

}
