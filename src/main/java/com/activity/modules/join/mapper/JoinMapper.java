package com.activity.modules.join.mapper;

import com.activity.modules.join.entity.po.JoinPo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Moon
 * @create: 2019-09-18 17:25
 * @description:
 */
@Mapper
public interface JoinMapper{

    void insert(JoinPo joinPo);
}
