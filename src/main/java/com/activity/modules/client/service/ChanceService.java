package com.activity.modules.client.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.activity.modules.client.entity.ChanceEntity;
import com.activity.modules.client.entity.vo.ClientVO;

import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/22 14:55
 * @description：
 * @modified By：
 * @version:
 */
public interface ChanceService extends IService<ChanceEntity>{

    void insertChance(ChanceEntity chanceEntity);

   void updateChance(ChanceEntity chanceEntity);

    void deleteById(Integer chanceid);

    List<ChanceEntity> getList(EntityWrapper<ChanceEntity> wrapper);
}
