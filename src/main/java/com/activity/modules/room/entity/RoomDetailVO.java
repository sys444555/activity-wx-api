package com.activity.modules.room.entity;

import com.activity.modules.business.entity.po.BusinessPo;
import com.activity.modules.room.entity.po.RoomPO;
import com.activity.modules.room.entity.po.RoomPic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Charles Chan
 * @Date: 2019/9/11 17:03
 * @Version 1.0
 */
@Repository
public class RoomDetailVO {

    @JsonProperty("room")
    @Autowired
    private RoomVO roomVO;

    @JsonProperty("photo")
    @Autowired
    private List<RoomPic> roomPicList;

    @JsonProperty("payStatus")
    private Integer payStatus;


    public RoomDetailVO(RoomVO roomVO,List<RoomPic> roomPicList,Integer payStatus) {
        this.roomPicList = roomPicList;
        this.roomVO = roomVO;
        this.payStatus = payStatus;
    }

    public RoomDetailVO(){

    }

}
