package com.zsk.seata.business.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "order-service")
@Component
public interface OrderApi {

    @PostMapping("order/create")
    Boolean create(@RequestBody Map<String, Object> params);

}
