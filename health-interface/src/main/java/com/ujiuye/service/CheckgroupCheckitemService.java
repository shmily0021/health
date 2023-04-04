package com.ujiuye.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ujiuye.entity.CheckgroupCheckitem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ujiuye
 * @since 2023-04-03
 */
@Service
public interface CheckgroupCheckitemService extends IService<CheckgroupCheckitem> {

    List<Integer> findByGid(Integer gid);
}
