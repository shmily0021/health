package com.ujiuye.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ujiuye.Message.MessageConstant;
import com.ujiuye.entity.Checkgroup;
import com.ujiuye.result.PageResult;
import com.ujiuye.result.QueryPageBean;
import com.ujiuye.result.ResultVo;
import com.ujiuye.service.CheckgroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.ujiuye.Message.MessageConstant.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckgroupController {

    @Reference
    private CheckgroupService checkgroupService;

    // 处理分页查询
    @RequestMapping("/findPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean) {

        return checkgroupService.findByPage(queryPageBean);
    }

    // 添加(新增)
    @RequestMapping("/add")
    public ResultVo addCheckGroup(@RequestBody Checkgroup checkgroup, Integer[] ids) {

        boolean b = checkgroupService.saveCheckGroup(checkgroup, ids);

        if (b) {

            return new ResultVo(true, ADD_CHECKGROUP_SUCCESS, null);
        }

        return new ResultVo(false, ADD_CHECKGROUP_FAIL, null);
    }

    // 删除检查组
    @RequestMapping("/del")
    public ResultVo delCheckgroup(Integer id) {

        boolean b = checkgroupService.delectCheckGroupById(id);

        if (b) {

            return new ResultVo(true, DELETE_CHECKGROUP_SUCCESS, null);
        }

        return new ResultVo(false, DELETE_CHECKGROUP_FAIL, null);
    }

    // 编辑检查组保存实现
    @RequestMapping("/update")
    public ResultVo update(@RequestBody Checkgroup checkgroup, Integer[] ids) {

        boolean b = checkgroupService.updateCheckgroup(checkgroup, ids);

        if (b) {

            return new ResultVo(true, EDIT_CHECKGROUP_SUCCESS, null);
        }

        return new ResultVo(false, EDIT_CHECKGROUP_FAIL, null);
    }
}

