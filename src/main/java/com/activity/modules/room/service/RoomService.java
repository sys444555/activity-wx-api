package com.activity.modules.room.service;


import com.activity.modules.room.entity.RoomDetailVO;
import com.activity.modules.room.entity.RoomVO;
import com.activity.modules.room.entity.po.RoomPO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:29
 * @description：
 * @modified By：
 * @version:
 */
public interface RoomService {

    public List<RoomPO> selectPage(Double latitude , Double longitude);

    public void createRoom(RoomVO roomVO, String openId);

    public boolean hasRoom(String openId);

    public List<RoomPO> getUserRooms(Integer status, String openId);

    public void updateRoomStatus( Integer roomId, Integer status,String openId);

    public void changeUserAccount(String openId, Integer id, Integer roomJoinAccount);

    public boolean isPay(Integer roomId, String openId);

    public RoomDetailVO getRoomDetailById(Integer id, String openId);

    public Integer checkPwd(String id, String pwd);


}
