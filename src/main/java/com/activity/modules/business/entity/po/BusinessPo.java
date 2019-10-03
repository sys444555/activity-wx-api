package com.activity.modules.business.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author: Moon
 * @create: 2019-09-18 17:25
 * @description:
 */
@Data
@TableName("business")
@Repository
public class BusinessPo {

    @ApiModelProperty(value = "主键",name = "id")
    @TableField("id")
    private Integer id;

    @ApiModelProperty(value = "姓名",name = "business_name")
    @TableField("business_name")
    private String businessName;

    @ApiModelProperty(value = "电话",name = "business_phone")
    @TableField("business_phone")
    private String businessPhone;

    @ApiModelProperty(value = "qq",name = "business_qq")
    @TableField("business_qq")
    private String businessQq;

    @ApiModelProperty(value = "店名",name = "business_store")
    @TableField("business_store")
    private String businessStore;

    @ApiModelProperty(value = "店铺地址",name = "business_addr")
    @TableField("business_addr")
    private String businessAddr;

    @ApiModelProperty(value = "合作意向",name = "business_message")
    @TableField("business_message")
    private String businessMessage;

    @ApiModelProperty(value = "纬度",name = "latitude")
    @TableField("latitude")
    private Double latitude;

    @ApiModelProperty(value = "经度",name = "longitude")
    @TableField("longitude")
    private Double longitude;

}
