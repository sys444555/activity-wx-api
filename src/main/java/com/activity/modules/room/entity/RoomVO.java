package com.activity.modules.room.entity;

import com.activity.modules.business.entity.po.BusinessPo;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:32
 * @description：
 * @modified By：
 * @version:
 */
@Data
@ApiModel("活动房间实体类")
@TableName("room")
@Repository
public class RoomVO implements Serializable {

    @ApiModelProperty(value = "自增ID",name = "id")
    private Integer id;

    @ApiModelProperty(value = "房间名字",name = "id")
    @TableField("room_name")
    private String roomName;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("room_type")
    private String roomType;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("room_focus")
    private Integer roomFocus;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("room_volume")
    private Integer roomVolume;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("room_join_account")
    private Integer roomJoinAccount;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private String address;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("deadline")
    private Date deadline;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("deposit")
    private Integer deposit;


    @ApiModelProperty(value = "自增ID",name = "id")
    @TableField("room_describtion")
    private String roomDescribtion;

    @ApiModelProperty(value = "房主手机",name = "phone")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "名字",name = "name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "邮箱",name = "email")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "商店名",name = "shopName")
    @TableField("shop_name")
    private String shopName;

    @ApiModelProperty(value = "套餐选择",name = "menuDescribtion")
    @TableField("menu_describtion")
    private String menuDescribtion;

    @ApiModelProperty(value = "单价",name = "totalPrice")
    @TableField("total_price")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "房间主题",name = "roomTopic")
    @TableField("room_topic")
    private String roomTopic;


    @ApiModelProperty(value = "套餐",name = "carArray")
    @TableField("car_array")
    private String carArray;


    @ApiModelProperty(value = "图片",name = "photo")
    @TableField("photo")
    private String photo;

    private String star;

    @TableField(exist = false)
    private String startTimeStr;

    @TableField(exist = false)
    private String typeStr;

    private String plaintext;

    private String password;

    private Integer isPasswordOut;


    private Integer joinId;

    @TableField(exist = false)
    private Double km;

    @TableField(exist = false)
    private BusinessPo businessPo;

}
