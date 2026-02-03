package org.example.serviceprovider;

import org.example.serviceprovider.config.GlobalFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients(defaultConfiguration = GlobalFeignConfig.class) // 启用 Feign 客户端
@EnableFeignClients
@MapperScan(basePackages = "org.example.serviceprovider.mapper")
public class ServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderApplication.class, args);
	}

}
