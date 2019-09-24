package com.activity.modules.business.mapper;

import com.activity.modules.business.entity.po.BusinessPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Moon
 * @create: 2019-09-18 17:25
 * @description:
 */
@Mapper
public interface BusinessMapper {

    void insert(BusinessPo businessPo);
}
