package com.zsk.seata.business.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "storage-service")
@Component
public interface StorageApi {

    @PostMapping("storage/change")
    Boolean changeStorage(Map<String, Object> params);

}
