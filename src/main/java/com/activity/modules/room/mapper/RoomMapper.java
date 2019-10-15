package com.activity.modules.room.mapper;

import com.activity.modules.room.entity.RoomVO;
import com.activity.modules.room.entity.po.RoomPO;
import com.activity.modules.room.entity.po.RoomPic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 19:05
 * @description：

 * @modified By：
 * @version:
 */
public interface RoomMapper extends BaseMapper<RoomVO> {

    List<RoomPO> roomList();
    Integer createRoom(RoomVO roomVO);

    Integer createRoomer(@Param(value = "roomId") Integer roomId,@Param(value = "openId") String openId);

    Integer hasRoom(@Param(value = "openId") String openId);

    List<RoomPO> getUserRooms(@Param(value = "status") Integer status,
                              @Param(value = "openId") String openId);

    void updateRoomStatus(@Param(value = "roomId") Integer roomId,
                          @Param(value = "status") Integer status,
                          @Param(value = "openId") String openId);

    Integer changeUserAccount(@Param(value = "roomId") Integer roomId,
                          @Param(value = "roomJoinAccount") Integer roomJoinAccount);


    Integer createJoiner(@Param(value = "roomId") Integer roomId,
                         @Param(value = "openId") String openId);

    Integer isPay(@Param(value = "roomId") Integer roomId,
                  @Param(value = "openId") String openId);

    List<RoomPic> selectPic(Integer id);

    List<RoomPO> roomListById(Integer roomId);

    void createPic(@Param(value = "roomId") Integer roomId);

    void updateRoomJoinAccount(@Param(value = "roomId") Integer roomId);
}
