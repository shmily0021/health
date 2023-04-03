package com.ujiuye.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ujiuye.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference  // 远程调用
    private CheckItemService checkItemService;

    @RequestMapping("/xx")
    public String xx() {
        return "hello";
    }
}
