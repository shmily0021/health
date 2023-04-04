package com.ujiuye.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ujiuye.result.ResultVo;
import com.ujiuye.service.CheckgroupCheckitemService;
import org.apache.zookeeper.Op;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/checkgroup-checkitem")
public class CheckgroupCheckitemController {

    @Reference
    private CheckgroupCheckitemService checkgroupCheckitemService;

    // 获取检查组中检查项id
    @RequestMapping("/getCheckitemId")
    public ResultVo getCheckitemId(Integer gid) {

        List<Integer> list = checkgroupCheckitemService.findByGid(gid);

        return new ResultVo(true, "获取成功", list);
    }
}

