package org.example.serviceprovider.util;

import org.example.serviceprovider.feign.UserServiceClient;
import org.example.serviceprovider.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * openfeign 异常回调处理机制
 */
@Component
public class GlobalFeignFallback implements UserServiceClient {

    @Override
    public ApiResponse<UserEntity> findUserinfoById(String id) {
        //这里书写触发熔断后的业务逻辑
        return ApiResponse.error("500");
    }

}
