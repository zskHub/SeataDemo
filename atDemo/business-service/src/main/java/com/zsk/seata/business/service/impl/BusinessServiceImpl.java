package com.zsk.seata.business.service.impl;

import com.zsk.seata.business.api.OrderApi;
import com.zsk.seata.business.api.StorageApi;
import com.zsk.seata.business.dto.BuyDto;
import com.zsk.seata.business.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : zsk
 * @CreateTime : 2021-09-06   20:08
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private OrderApi orderApi;

    @Autowired
    private StorageApi storageApi;

    @Override
    @GlobalTransactional
    public String buy(BuyDto buyDto) {
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("productId", buyDto.getProductId());
        orderMap.put("userId", buyDto.getUserId());
        orderApi.create(orderMap);

        Map<String, Object> storageMap = new HashMap<>();
        storageMap.put("productId", buyDto.getProductId());
        storageMap.put("used", 1);
        storageApi.changeStorage(storageMap);

        return "ok";
    }
}
