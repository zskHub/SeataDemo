package com.zsk.seata.order.controller;

import com.zsk.seata.order.entity.Order;
import com.zsk.seata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author : zsk
 * @CreateTime : 2021-09-06   18:37
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 这里简单写了，直接用个map了
     * 需要的参数：productId,userId
     * */
    @PostMapping("create")
    public Boolean create(@RequestBody Map<String, Object> params){
        Order order = new Order();
        order.setCount(1)
                .setMoney(BigDecimal.valueOf(88))
                .setProductId(Long.valueOf(params.get("productId").toString()))
                .setUserId(Long.valueOf(params.get("userId").toString()))
                .setStatus(0);
        return orderService.create(order);
    }
}
