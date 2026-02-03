package org.example.servercustomer.util;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ServiceRedirectUtil {

    @Resource
    private LoadBalancerClient loadBalancerClient;

    /**
     * 生成带Token的跳转URL
     */
    public String generateRedirectUrl(String serviceName, String path, String token) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        if (instance == null) {
            throw new RuntimeException("服务不可用: " + serviceName);
        }

        String baseUrl = instance.getUri().toString();
        return baseUrl + path + "?token=" + token;
    }

    /**
     * 在网关层面生成跳转URL（推荐）
     */
    public String generateGatewayRedirectUrl(String servicePath, String token) {
        return "http://localhost:8080" + servicePath + "?token=" + token;
    }
}
