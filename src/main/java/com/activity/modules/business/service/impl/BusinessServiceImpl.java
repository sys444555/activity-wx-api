package com.activity.modules.business.service.impl;

import com.activity.common.exception.JcException;
import com.activity.modules.business.entity.po.BusinessPo;
import com.activity.modules.business.mapper.BusinessMapper;
import com.activity.modules.business.service.BusinessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Moon
 * @create: 2019-09-18 17:28
 * @description:
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessMapper businessMapper;

    @Override
    public void insertAll(BusinessPo businessPo) throws JcException{
        if(findByPhone(businessPo) != 0){
            throw new JcException("电话已存在！");
        }
        businessMapper.insert(businessPo);

    }

    @Override
    public Integer findByPhone(BusinessPo businessPo) {
        Integer phoneCount = businessMapper.selectByPhone(businessPo);
        return phoneCount;
    }
}
