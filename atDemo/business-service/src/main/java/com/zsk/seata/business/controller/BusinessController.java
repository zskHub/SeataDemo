package com.zsk.seata.business.controller;

import com.zsk.seata.business.dto.BuyDto;
import com.zsk.seata.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author : zsk
 * @CreateTime : 2021-09-06   18:41
 */
@RestController
@RequestMapping("business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("buy")
    public String buy(@RequestBody BuyDto buyDto){
        return businessService.buy(buyDto);
    }
}
