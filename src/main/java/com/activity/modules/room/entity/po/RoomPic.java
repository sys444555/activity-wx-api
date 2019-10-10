package com.activity.modules.room.entity.po;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @Author: Charles Chan
 * @Date: 2019/9/11 17:04
 * @Version 1.0
 */
@Data
@TableName("room_pic")
@Repository
public class RoomPic {

    private Integer id;

    private Integer roomId;

    private String  type;

    private String url;

    public RoomPic(Integer id, Integer roomId, String type, String url) {
        this.id = id;
        this.roomId = roomId;
        this.type = type;
        this.url = url;
    }

    public RoomPic(){

    }
}
