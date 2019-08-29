package com.activity.modules.room.service.impl;


import com.activity.common.exception.JcException;
import com.activity.modules.room.entity.RoomVO;
import com.activity.modules.room.entity.po.RoomPO;
import com.activity.modules.room.mapper.RoomMapper;
import com.activity.modules.room.service.RoomService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:31
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, RoomVO> implements RoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<RoomPO> selectPage() {
        List<RoomPO> list = roomMapper.roomList();
       return list;
    }

    @Override
    public void createRoom(RoomVO roomVO, String openId) {
        //获取新增后返回的id值
        Integer roomId = roomMapper.createRoom(roomVO);
        System.out.println("roomId = " + roomVO.getId());
        if(roomId == null || roomId == 0){
            throw new JcException("新建房间失败");
        }
        roomMapper.createRoomer(roomVO.getId(), openId);

    }

    @Override
    public boolean hasRoom(String openId) {
        if(openId != null && openId != ""){
            //判断数据库是否存在当前用户的房间
            Integer count = roomMapper.hasRoom(openId);
            //当前用户存在房间返回true,否则false
            if(count != 0){
                return true;
            }else {
                return false;
            }
        }else {
            throw new JcException("openId为空");
        }


    }

    /**
     *  获取个人所有房间
     * @param openId
     * @return
     */
    @Override
    public List<RoomPO> getUserRooms(Integer status, String openId) {
        //如果状态值为0,查找全部房间，否则按照状态码查找对应的房间
        return roomMapper.getUserRooms(status, openId);
    }

    /**
     * 支付成功后更新个人房间状态
     * @param openId
     * @param roomId
     * @param status
     */
    @Override
    public void updateRoomStatus( Integer roomId, Integer status,String openId) {
        roomMapper.updateRoomStatus(roomId,status,openId);
    }

    @Override
    public void changeUserAccount(String openId, Integer id, Integer roomJoinAccount) {
        Integer count = roomMapper.changeUserAccount(id, roomJoinAccount);
        if(count != null & count != 0){
            roomMapper.createJoiner(id, openId);
        }
    }

    @Override
    public boolean isPay(Integer roomId, String openId) {
        Integer pay = roomMapper.isPay(roomId, openId);
        System.out.println("pay1 = " + pay);
        if(pay != null && pay == 1){
            System.out.println("pay = " + pay);
            return true;
        }else {
            return false;
        }
    }

}
