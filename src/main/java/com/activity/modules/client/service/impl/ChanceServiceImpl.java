package com.activity.modules.client.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.activity.modules.client.entity.ChanceEntity;
import com.activity.modules.client.mapper.ChanceMapper;
import com.activity.modules.client.service.ChanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/22 14:56
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class ChanceServiceImpl extends ServiceImpl<ChanceMapper,ChanceEntity> implements ChanceService {

    @Resource
    private ChanceMapper chanceMapper;

    @Override
    public void insertChance(ChanceEntity chanceEntity) {
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(chanceEntity.getTime() == null || "".equals(chanceEntity.getTime())){
           chanceEntity.setTime(df.format(day));
        }
        if(chanceEntity.getAuth()==null || "".equals(chanceEntity.getAuth())){
            chanceEntity.setAuth("作者");
        }
        chanceMapper.insert(chanceEntity);
    }

    @Override
    public void updateChance(ChanceEntity chanceEntity) {
        chanceMapper.updateById(chanceEntity);
    }

    @Override
    public void deleteById(Integer chanceid) {
        chanceMapper.deleteById(chanceid);
    }

    @Override
    public List<ChanceEntity> getList(EntityWrapper<ChanceEntity> wrapper) {
        List<ChanceEntity> entityList=chanceMapper.selectList(wrapper);
        return entityList;
    }


}
