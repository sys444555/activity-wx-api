package com.activity.modules.pay.mapper;

import com.activity.modules.pay.entity.PayEntity;
import com.activity.modules.pay.entity.PayReultEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/18 15:22
 * @description：
 * @modified By：
 * @version:
 */
public interface PayMapper extends BaseMapper<PayEntity> {

    public Integer createPayMessage(PayReultEntity payReultEntity);

    public Integer updateOrderStatus(@Param(value = "transactionId") String transactionId,
                                     @Param(value = "outTradeNo") String outTradeNo,
                                     @Param(value = "openid") String openid);

    public void updatePayStatus(@Param(value = "roomId")Integer roomId , @Param(value = "openId")String openId);
}
