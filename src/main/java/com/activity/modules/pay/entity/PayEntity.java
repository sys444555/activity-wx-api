package com.activity.modules.pay.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/16 17:51
 * @description：
 * @modified By：
 * @version:
 */
@Data
@TableName("pay")
public class PayEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增ID",name = "id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名",name="body")
    @TableField("body")
    private String body;

    @ApiModelProperty(value = "订单号",name="orderOn")
    @TableField("order_on")
    private String orderOn;

    @ApiModelProperty(value = "支付金额",name="payNum")
    @TableField("pay_num")
    private String payNum;

    @ApiModelProperty(value = "openId",name="openId")
    @TableField("open_id")
    private String openId;

    @ApiModelProperty(value = "退款金额",name="refundFee")
    @TableField("refund_fee")
    private String refundFee;

    @ApiModelProperty(value = "房间id",name="roomId")
    @TableField("room_id")
    private Integer roomId;

}

