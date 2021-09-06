package com.zsk.seata.storage.controller;

import com.zsk.seata.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author : zsk
 * @CreateTime : 2021-09-06   18:39
 */
@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 这里简单省事，就直接用map接受了
     * 必要参数：productId，used
     * */
    @PostMapping("change")
    public Boolean changeStorage(@RequestBody Map<String, Object> params)  {
        Long productId = Long.valueOf(params.get("productId").toString());
        Long used = Long.valueOf(params.get("used").toString());

        return storageService.updateUseNum(productId , used);
    }
}
