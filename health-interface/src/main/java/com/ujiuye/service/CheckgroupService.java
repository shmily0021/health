package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.entity.Checkgroup;
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
public interface CheckgroupService extends IService<Checkgroup> {

    PageResult findByPage(QueryPageBean queryPageBean);

    boolean saveCheckGroup(Checkgroup checkgroup, Integer[] ids);

    boolean delectCheckGroupById(Integer id);

    boolean updateCheckgroup(Checkgroup checkgroup, Integer[] ids);
}
