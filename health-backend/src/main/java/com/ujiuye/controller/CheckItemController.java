package com.ujiuye.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ujiuye.entity.Checkitem;
import com.ujiuye.result.PageResult;
import com.ujiuye.result.QueryPageBean;
import com.ujiuye.result.ResultVo;
import com.ujiuye.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.ujiuye.Message.MessageConstant.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean) {

        // System.out.println(queryPageBean);
        return checkItemService.findByPage(queryPageBean);
    }

    // 新增检查项
    @RequestMapping("/add")
    public ResultVo addCheckItem(@RequestBody Checkitem checkitem) {

        boolean save = checkItemService.save(checkitem);

        if (save) {
            return new ResultVo(true, ADD_CHECKITEM_SUCCESS, null);
        }

        return new ResultVo(true, ADD_CHECKITEM_FAIL, null);
    }

    // 删除检查项
    @RequestMapping("/del")
    public ResultVo delCheckItem(Integer id) {

        boolean code = checkItemService.delCheckitem(id);
        if (code) {
            return new ResultVo(true, DELETE_CHECKITEM_SUCCESS, null);
        }
        return new ResultVo(false, DELETE_CHECKITEM_FAIL, null);
    }

    // 更新(编辑)检查组
    @RequestMapping("/update")
    public ResultVo updateCheckItem(@RequestBody Checkitem checkitem) {

        boolean b = checkItemService.updateById(checkitem);

        if (b) {
            return new ResultVo(true, EDIT_CHECKITEM_SUCCESS, null);
        }

        return new ResultVo(false, EDIT_CHECKITEM_FAIL, null);
    }

    // 查询所有检查项(检查组的检查项使用)
    @RequestMapping("/findAll")
    public ResultVo findAll() {

        return checkItemService.findAll();
    }
}

