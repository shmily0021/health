package com.ujiuye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ujiuye.entity.CheckgroupCheckitem;
import com.ujiuye.mapper.CheckgroupCheckitemMapper;
import com.ujiuye.service.CheckgroupCheckitemService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@Component
@Service(interfaceClass = CheckgroupCheckitemService.class)
public class CheckgroupCheckitemServiceImpl extends ServiceImpl<CheckgroupCheckitemMapper, CheckgroupCheckitem> implements CheckgroupCheckitemService {

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Override
    public List<Integer> findByGid(Integer gid) {
        // 定义集合只存储gid
        List<Integer> ids = new ArrayList<>();

        QueryWrapper queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("checkgroup_id", gid);

        List<CheckgroupCheckitem> list = checkgroupCheckitemMapper.selectList(queryWrapper);

        for (CheckgroupCheckitem checkgroupCheckitem : list) {
            ids.add(checkgroupCheckitem.getCheckitemId());
        }

        return ids;
    }
}
