package com.activity.modules.client.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.activity.basic.BasicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("机会销售信息汇总表视图实体类")
@Data
@TableName("custom_information")
public class ChanceEntity extends BasicEntity {

    @ApiModelProperty(value = "自增ID",name = "chanceid")
    @TableId(type = IdType.AUTO)
    private Integer chanceid;

    @ApiModelProperty(value = "机会名称",name="chanceName")
    @TableField("chance_name")
    private String chanceName;

    @ApiModelProperty(value = "客户名称",name="clientName")
    @TableField("client_name")
    private String clientName;

    @ApiModelProperty(value = "类型",name="type")
    private Integer type;

    @ApiModelProperty(value = "机会来源",name="source")
    private Integer source;

    @ApiModelProperty(value = "成交日期",name="date_time")
    @TableField("date_time")
    private String dateTime;

    @ApiModelProperty(value = "成交金额",name="money")
    private String money;

    @ApiModelProperty(value = "阶段",name="stage")
    private Integer stage;

    @ApiModelProperty(value = "可能性",name="possibility")
    private String possibility;

    @ApiModelProperty(value = "备注",name="remarks")
    private String remarks;

    @ApiModelProperty(value = "地址",name="address")
    private String address;

    @ApiModelProperty(value = "状态",name="status")
    private Integer status;

    @ApiModelProperty(value = "录入时间",name="time")
    private String time;

    @ApiModelProperty(value = "录入人",name="auth")
    private String auth;
}
