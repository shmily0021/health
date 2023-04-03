package com.ujiuye.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ujiuye.service.CheckItemService;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
}
