package com.activity.modules.business.controller;

import com.activity.common.utils.ResponseUtil;
import com.activity.modules.business.entity.po.BusinessPo;
import com.activity.modules.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Moon
 * @create: 2019-09-18 17:25
 * @description:
 */
@RestController
@RequestMapping("/wx/api/join")
public class BusinessController {

    @Autowired
    BusinessService joinService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseUtil insertJoinEntity(BusinessPo businessPo){
        System.err.println("============================insertJoinEntity");
        System.err.println(businessPo);
        joinService.insertAll(businessPo);
        return ResponseUtil.success();
    }

}
