package com.activity.modules.client.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.activity.modules.client.entity.po.ClientPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/5/23 10:36
 * @description：
 * @modified By：
 * @version:
 */
@ApiModel("客户信息汇总表视图实体类")
@Data
public class ClientVO extends ClientPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id",name = "id")
    private Integer id;

    @ApiModelProperty(value = "客户所属类型: 1、潜在客户 2、意向客户 3、成交客户 4、失败客户 5、已流失客户",name = "clientType")
    private Integer clientType;

    @ApiModelProperty(value = "客户名字",name = "clientName")
    private String clientName;

    @ApiModelProperty(value = "客户类型: 1、普通客户 2、老客户 3、代理商 4、合作伙伴 5、其他",name = "type")
    private Integer type;

    @ApiModelProperty(value = "成熟度",name = "muturity")
    private String muturity;

    @ApiModelProperty(value = "客户状态:  1、潜在客户 2、意向客户 3、成交客户 4、失败客户 5、已流失客户",name = "status")
    private Integer status;

    @ApiModelProperty(value = "客户等级: 1、★ 2、★★ 3、★★★ 4、★★★★ 5、★★★★★",name = "grade")
    private Integer grade;

    @ApiModelProperty(value = "合作意向: 1、明确成交意向 2、有初步成交意向 3、关键人有成交意向 4、关键人成交意向不确定 5、初步联系/有意向 6、初步联系/意向不确定 7、新建客户 8、明显拒绝 9、无效客户",name = "direction")
    private Integer direction;

    @ApiModelProperty(value = "客户来源: 1、手工录入 2、外包渠道 3、推广渠道 4、客户介绍 5、广告渠道 6、职员引荐 7、其他",name = "source")
    private Integer source;

    @ApiModelProperty(value = "意向产id",name = "pro_Id")
    private Integer proId;

    @ApiModelProperty(value = "客户意向",name = "intention")
    private String intention;

    @ApiModelProperty(value = "客户介绍",name = "introduction")
    private String introduction;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "获取客户时间",name = "access_time")
    private Date accessTime;

    @ApiModelProperty(value = "所有者",name = "owner")
    private String owner;

    @ApiModelProperty(value = "公司编号",name = "companyCode")
    private String companyCode;

    @ApiModelProperty(value = "公司名称",name = "companyName")
    private String companyName;

    @ApiModelProperty(value = "公司所属行业: 1、农、林、牧、渔业 2、采矿业 3、制造业 4、电力生产和供应 5、建筑业 6、交通运输、仓储和邮政 7、计算机服务和软件业 8、批发和零售业 9、住宿和餐饮业 10、金融业 11、房地产业 12、租赁和商务服务 13、科学研究 14、水利、环境管理 15、居民服务和其他服务 16、教育 17、社会保障和社会福利 18、文化、体育和娱乐业 19、公共管理和社会组织 20、国际组织",name = "industry")
    private Integer industry;

    @ApiModelProperty(value = "公司座机",name = "tel")
    private String tel;

    @ApiModelProperty(value = "公司地址",name = "address")
    private String address;

    @ApiModelProperty(value = "公司所在城市",name = "companyCity")
    private String companyCity;

    @ApiModelProperty(value = "备注",name = "remarks")
    private String remarks;
}
