package com.activity.modules.join.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Moon
 * @create: 2019-09-18 17:25
 * @description:
 */
@Data
public class JoinPo {

    @ApiModelProperty(value = "主键",name = "id")
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "姓名",name = "name")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "电话",name = "phone")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "qq",name = "qq")
    @TableField("qq")
    private String qq;

    @ApiModelProperty(value = "店名",name = "business")
    @TableField("business")
    private String business;

    @ApiModelProperty(value = "店铺地址",name = "addr")
    @TableField("addr")
    private String addr;

    @ApiModelProperty(value = "合作意向",name = "message")
    @TableField("message")
    private String message;

    @ApiModelProperty(value = "纬度",name = "latitude")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "经度",name = "longitude")
    @TableField("longitude")
    private BigDecimal longitude;

}
