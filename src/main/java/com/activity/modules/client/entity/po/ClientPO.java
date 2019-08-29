package com.activity.modules.client.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/27 11:02
 * @description：
 * @modified By：
 * @version:
 */
@ApiModel("客户信息汇总表视图实体类")
@Data
public class ClientPO implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键",name = "linkId")
    private Integer linkId;

    @ApiModelProperty(value = "名称",name = "linkName")
    private String linkName;

    @ApiModelProperty(value = "公司编号",name = "linkCode")
    private String linkCode;

    @ApiModelProperty(value = "性别: 1、男 2、女 3、保密",name = "link_gender")
    private Integer linkGender;

    @ApiModelProperty(value = "生日",name = "link_birth")
    private Date linkBirth;

    @ApiModelProperty(value = "职务",name = "link_pose")
    private String linkPose;

    @ApiModelProperty(value = "称呼",name = "link_call")
    private String linkCall;

    @ApiModelProperty(value = "电话",name = "link_Phone")
    private String linkPhone;

    @ApiModelProperty(value = "联系人座机",name = "link_tel")
    private String linkTel;

    @ApiModelProperty(value = "联系人邮箱",name = "link_email")
    private String linkEmail;
}
