package org.example.serviceprovider.feign;


import org.example.serviceprovider.util.GlobalFeignFallback;
import org.example.serviceprovider.util.ApiResponse;
import org.example.serviceprovider.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "server-customer", fallback = GlobalFeignFallback.class)
public interface UserServiceClient {

    @GetMapping("/user/{id}")
    ApiResponse<UserEntity> findUserinfoById(@PathVariable String id);

}
