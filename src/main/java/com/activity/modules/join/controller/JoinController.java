package com.activity.modules.join.controller;

import com.activity.common.utils.ResponseUtil;
import com.activity.modules.join.entity.po.JoinPo;
import com.activity.modules.join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class JoinController {

    @Autowired
    JoinService joinService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseUtil insertJoinEntity(JoinPo joinPo){
        System.err.println("============================insertJoinEntity");
        System.err.println(joinPo);
        joinService.insertAll(joinPo);
        return ResponseUtil.success();
    }

}
