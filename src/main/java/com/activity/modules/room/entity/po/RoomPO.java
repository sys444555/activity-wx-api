package com.activity.modules.room.entity.po;

import com.activity.modules.room.entity.RoomVO;
import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/10 16:09
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class RoomPO extends RoomVO {

    @ApiModelProperty(value = "openId",name = "openId")
    @TableField("open_id")
    private String openId;


    @ApiModelProperty(value = "状态",name = "status")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "用户角色",name = "userRole")
    @TableField("user_role")
    private Integer userRole;
}
