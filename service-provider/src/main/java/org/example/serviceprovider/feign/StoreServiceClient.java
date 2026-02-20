package org.example.serviceprovider.feign;

import org.example.serviceprovider.config.GlobalFeignConfig;
import org.example.serviceprovider.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "server-customer", contextId = "customer2", fallbackFactory = GlobalFeignConfig.class)
public interface StoreServiceClient {

    @GetMapping("/api/proc")
    ApiResponse<Boolean> decrease(@RequestParam("orderId") String orderId, @RequestParam("amount") int amount) ;

}
