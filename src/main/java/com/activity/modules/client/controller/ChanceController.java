package com.activity.modules.client.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.activity.common.utils.ResponseUtil;
import com.activity.modules.client.entity.ChanceEntity;
import com.activity.modules.client.service.ChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chance")
public class ChanceController{

    @Autowired
    private ChanceService chanceService;

    @RequestMapping(value="/insert",method= RequestMethod.POST)
    public ResponseUtil insertChanceEntity(ChanceEntity chanceEntity){
        chanceService.insertChance(chanceEntity);
        return ResponseUtil.success();
    }

    @RequestMapping(value="/update",method=RequestMethod.POST)
    public ResponseUtil updateChanceEntity(ChanceEntity chanceEntity){
        chanceService.updateChance(chanceEntity);
        return ResponseUtil.success();
    }

    @RequestMapping(value="/delete/{chanceid}",method=RequestMethod.POST)
    public ResponseUtil deleteByChanceId(@PathVariable Integer chanceid){
        chanceService.deleteById(chanceid);
        return ResponseUtil.success();
    }





}
