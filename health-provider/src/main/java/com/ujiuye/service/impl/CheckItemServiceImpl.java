package com.ujiuye.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ujiuye.entity.Checkitem;
import com.ujiuye.mapper.CheckgroupCheckitemMapper;
import com.ujiuye.mapper.CheckitemMapper;
import com.ujiuye.result.PageResult;
import com.ujiuye.result.QueryPageBean;
import com.ujiuye.result.ResultVo;
import com.ujiuye.service.CheckItemService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.ujiuye.Message.MessageConstant.QUERY_CHECKITEM_SUCCESS;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@Component
@Service(interfaceClass = CheckItemService.class)
// @Transactional // 事务
public class CheckItemServiceImpl extends ServiceImpl<CheckitemMapper, Checkitem> implements CheckItemService {

    @Resource  // 注入Mapper
    private CheckitemMapper checkitemMapper;

    @Resource
    private CheckgroupCheckitemMapper checkgroupCheckitemMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {

        // 分页查询
        // 1. 创建page对象
        Page page = new Page(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 2. 调用MybatisPlus分页方法
        QueryWrapper<Checkitem> queryWrapper = new QueryWrapper();

        if (queryPageBean.getQueryString() != null) {
            // 查询条件不为空时查询条件像编号和名字时获取查询条件
            queryWrapper.like("code", queryPageBean.getQueryString()).or()
                    .like("name", queryPageBean.getQueryString());
        }

        Page page1 = checkitemMapper.selectPage(page, queryWrapper);

        // 3. 封装到PageResult 中
        return new PageResult(page1.getTotal(), page1.getRecords());
    }

    // 删除检查项
    @Override
    public boolean delCheckitem(Integer id) {

        // 处理检查项删除
        // 1. 检查项关联的先删除
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("checkitem_id", id);

        checkgroupCheckitemMapper.delete(queryWrapper);

        // 2. 删除检查项
        int row = checkitemMapper.deleteById(id);

        return row > 0 ? true : false;
    }

    // 查询所有检查项
    @Override
    public ResultVo findAll() {

        // 从redis中获取数据
        HashOperations<String, Object, List<Checkitem>> opsForHash = redisTemplate.opsForHash();

        // 取数据
        List<Checkitem> checkitems = opsForHash.get("checkItem", "checkItemList");

        // 2. 判断
        if (checkitems == null || checkitems.size() < 1) {
            // redis没有缓存从数据库查询并且保存到redis
            checkitems = checkitemMapper.selectList(null);

            // 缓存到redis中
            opsForHash.put("checkItem", "checkItemList", checkitems);
        }

        return new ResultVo(true, QUERY_CHECKITEM_SUCCESS, checkitems);
    }
}
