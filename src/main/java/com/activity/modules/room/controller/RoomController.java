package com.activity.modules.room.controller;

import com.activity.common.utils.ResponseUtil;
import com.activity.modules.client.entity.ChanceEntity;
import com.activity.modules.room.entity.RoomVO;
import com.activity.modules.room.entity.po.RoomPO;
import com.activity.modules.room.service.RoomService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:25
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/wx/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * 活动房间列表查询
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseUtil roomList(){
       // PageHelper.startPage(pageNo, pageSize);
        List<RoomPO> pageList = roomService.selectPage();
       // PageInfo<RoomPO> pageInfo=new PageInfo<>(pageList);
        return ResponseUtil.success(pageList);
    }

    /**
     *  新增活动房间
     * @param roomVO
     * @param openId
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseUtil createRoom(RoomVO roomVO, String openId){
        System.out.println("RoomVO = " + roomVO);
        roomService.createRoom(roomVO,openId);
        return ResponseUtil.success();
    }


    @RequestMapping(value = "/checkRoom",method = RequestMethod.GET)
    public ResponseUtil checkRoom(String openId){
        boolean b = roomService.hasRoom(openId);
        if(b == true){
            return ResponseUtil.success(1);
        }else {
            return ResponseUtil.success(0);
        }
    }

    /**
     *  获取个人所有房间controller
     * @param openId
     * @return
     */
    @RequestMapping(value = "/user/list/{status}", method = RequestMethod.GET)
    public ResponseUtil getUserRooms(@PathVariable(value = "status") Integer status, String openId){
        List<RoomPO> userRooms = roomService.getUserRooms(status, openId);
        return ResponseUtil.success(userRooms);
    }

    /**
     *  支付成功后更新个人房间状态
     * @param openId
     * @param status
     * @return
     */
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public ResponseUtil updateRoomStatus(String openId, Integer roomId, Integer status){
        roomService.updateRoomStatus(roomId,status,openId);
        return ResponseUtil.success();
    }

    /**
     * 改變房間數量
     * @param openId
     * @param id
     * @param roomJoinAccount
     * @return
     */
    @RequestMapping(value = "/changeUserAccount", method = RequestMethod.POST)
    public ResponseUtil changeUserAccount(String openId, Integer id, Integer roomJoinAccount){
        roomService.changeUserAccount(openId, id , roomJoinAccount);
        return ResponseUtil.success();
    }

    @RequestMapping(value = "/isPay", method = RequestMethod.POST)
    public ResponseUtil isPay(String openId, Integer id){
        Map map = new HashMap();
        boolean isPay = roomService.isPay(id, openId);
        map.put("isPay", isPay);
        return ResponseUtil.success(map);
    }
}
