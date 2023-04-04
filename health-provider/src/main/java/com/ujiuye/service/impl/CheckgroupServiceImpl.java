package com.ujiuye.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.entity.Checkgroup;
import com.ujiuye.entity.CheckgroupCheckitem;
import com.ujiuye.mapper.CheckgroupCheckitemMapper;
import com.ujiuye.mapper.CheckgroupMapper;
import com.ujiuye.mapper.SetmealCheckgroupMapper;
import com.ujiuye.result.PageResult;
import com.ujiuye.result.QueryPageBean;
import com.ujiuye.service.CheckgroupService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@Component
@Service(interfaceClass = CheckgroupService.class)
public class CheckgroupServiceImpl extends ServiceImpl<CheckgroupMapper, Checkgroup> implements CheckgroupService {

    @Resource
    private CheckgroupMapper checkgroupMapper;

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Resource
    private SetmealCheckgroupMapper setmealCheckgroupMapper;

    // 检查组分页
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {

        // 分页查询
        // 1. 创建page对象
        Page page = new Page(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 2. 调用MybatisPlus分页方法
        QueryWrapper<Checkgroup> queryWrapper = new QueryWrapper();

        if (queryPageBean.getQueryString() != null && !"".equals(queryPageBean.getPageSize())) {

            queryWrapper.like("code", queryPageBean.getQueryString())
                    .or().like("name", queryPageBean.getQueryString())
                    .or().like("helpCode", queryPageBean.getQueryString());
        }

        Page page1 = checkgroupMapper.selectPage(page, queryWrapper);

        // 3. 封装到PageResult 中
        return new PageResult(page1.getTotal(), page1.getRecords());
    }

    // 新增检查组保存功能
    @Override
    public boolean saveCheckGroup(Checkgroup checkgroup, Integer[] ids) {
        // 保存检查组
        int row = checkgroupMapper.insert(checkgroup);

        // 保存检查组和检查项中间表
        if (row > 0) {
            for (Integer id : ids) {
                CheckgroupCheckitem checkgroupCheckitem = new CheckgroupCheckitem();

                checkgroupCheckitem.setCheckgroupId(checkgroup.getId());
                checkgroupCheckitem.setCheckitemId(id);

                checkgroupCheckitemMapper.insert(checkgroupCheckitem);
            }

            return true;
        }

        return false;
    }

    // 删除检查组
    @Override
    public boolean delectCheckGroupById(Integer id) {
        // 1. 删除套餐和检查组的中间数据
        QueryWrapper queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("checkgroup_id", id);

        setmealCheckgroupMapper.delete(queryWrapper);

        // 2. 删除检查组和检查项
        checkgroupCheckitemMapper.delete(queryWrapper);

        // 3. 删除检查组
        int row = checkgroupMapper.deleteById(id);

        if (row > 0) {

            return true;
        }

        return false;
    }

    // 检查组编辑保存功能
    @Override
    public boolean updateCheckgroup(Checkgroup checkgroup, Integer[] ids) {
        // 1.检查组信息修改
        int row = checkgroupMapper.updateById(checkgroup);

        // 2.检查组和检查项中间表修改
        // 先删除
        QueryWrapper queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("checkgroup_id", checkgroup.getId());

        checkgroupCheckitemMapper.delete(queryWrapper);

        // 然后插入
        for (Integer id : ids) {
            CheckgroupCheckitem checkgroupCheckitem = new CheckgroupCheckitem();

            checkgroupCheckitem.setCheckgroupId(checkgroup.getId());
            checkgroupCheckitem.setCheckitemId(id);

            checkgroupCheckitemMapper.insert(checkgroupCheckitem);
        }

        return row > 0 ? true : false;
    }
}
