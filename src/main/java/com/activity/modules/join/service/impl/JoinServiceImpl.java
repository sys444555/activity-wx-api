package com.activity.modules.join.service.impl;

import com.activity.modules.join.entity.po.JoinPo;
import com.activity.modules.join.mapper.JoinMapper;
import com.activity.modules.join.service.JoinService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Moon
 * @create: 2019-09-18 17:28
 * @description:
 */
@Service
public class JoinServiceImpl implements JoinService{

    @Resource
    private JoinMapper joinMapper;

    @Override
    public void insertAll(JoinPo joinPo) {
        joinMapper.insert(joinPo);
    }
}
