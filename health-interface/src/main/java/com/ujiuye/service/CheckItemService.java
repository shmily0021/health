package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.entity.Checkitem;
import com.ujiuye.result.PageResult;
import com.ujiuye.result.QueryPageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
public interface CheckItemService extends IService<Checkitem> {


    PageResult findByPage(QueryPageBean queryPageBean);

    boolean delCheckitem(Integer id);
}
