package org.example.elkservice.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceConfig {

    @Value("${server.address}")
    public static String address;

    public static String port;
}
