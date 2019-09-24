package com.activity.modules.auth.entity;

import com.activity.common.utils.BaseCodeUtil;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:32
 * @description：
 * @modified By：
 * @version:
 */
@Data
@ApiModel("微信用户信息表")
@TableName("user")
public class UserEntity implements Serializable {

    @ApiModelProperty(value = "open_Id",name = "openId")
    private String openId;

    @ApiModelProperty(value = "微信名字",name = "nickName")
    @TableField("nick_name")
    private String nickName;

    @ApiModelProperty(value = "性别: 0.女 1.男",name = "gender")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "城市",name = "city")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "省份",name = "province")
    @TableField("province")
    private String province;

    @ApiModelProperty(value = "国家",name = "country")
    @TableField("country")
    private String country;

    @ApiModelProperty(value = "实际路径",name = "avatarUrl")
    @TableId("avatar_url")
    private String avatarUrl;

    @ApiModelProperty(value = "union_id",name = "unionId")
    @TableId("union_id")
    private String unionId;

    @ApiModelProperty(value ="会员状态",name="vip")
    @TableField("vip")
    private Integer vip;



}
