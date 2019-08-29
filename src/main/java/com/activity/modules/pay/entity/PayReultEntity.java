package com.activity.modules.pay.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/8/27 17:18
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class PayReultEntity {

    @ApiModelProperty(value = "微信订单号",name="transactionId")
    @TableField("transaction_id")
    private String transactionId;

    @ApiModelProperty(value = "随机字符串",name="nonceStr")
    @TableField("nonce_str")
    private String nonceStr;

    @ApiModelProperty(value = "付款银行",name="bankType")
    @TableField("bank_type")
    private String bankType;

    @ApiModelProperty(value = "用户标识",name="openid")
    @TableField("openid")
    private String openid;

    @ApiModelProperty(value = "签名",name="sign")
    @TableField("sign")
    private String sign;

    @ApiModelProperty(value = "标价金额",name="feeType")
    @TableField("fee_type")
    private String feeType;

    @ApiModelProperty(value = "商户号",name="mchId")
    @TableField("mch_id")
    private String mchId;

    @ApiModelProperty(value = "现金支付金额",name="cashFee")
    @TableField("cash_fee")
    private String cashFee;

    @ApiModelProperty(value = "商户订单号",name="outTradeNo")
    @TableField("out_trade_no")
    private String outTradeNo;

    @ApiModelProperty(value = "小程序ID",name="appid")
    @TableField("appid")
    private String appid;

    @ApiModelProperty(value = "标价金额",name="totalFee")
    @TableField("total_fee")
    private String totalFee;

    @ApiModelProperty(value = "交易类型",name="tradeType")
    @TableField("trade_type")
    private String tradeType;

    @ApiModelProperty(value = "业务结果",name="resultCode")
    @TableField("result_code")
    private String resultCode;

    @ApiModelProperty(value = "支付完成时间",name="timeEnd")
    @TableField("time_end")
    private String timeEnd;

    @ApiModelProperty(value = "是否关注公众账号",name="isSubscribe")
    @TableField("is_subscribe")
    private String isSubscribe;

    @ApiModelProperty(value = "返回状态码",name="returnCode")
    @TableField("return_code")
    private String returnCode;

    @ApiModelProperty(value = "更新时间",name="updateTime")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间",name="createTime")
    @TableField("create_time")
    private Date createTime;


}
