package com.activity.modules.pay.service;

import com.activity.modules.pay.entity.PayEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/16 18:00
 * @description：
 * @modified By：
 * @version:
 */
public interface PayService {

    public Map<String, Object> wxPay(HttpServletRequest request, PayEntity payEntity);
}
