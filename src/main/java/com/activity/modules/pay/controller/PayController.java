package com.activity.modules.pay.controller;

import com.activity.common.utils.PayUtil;
import com.activity.common.utils.ResponseUtil;
import com.activity.common.utils.WXPayUtil;
import com.activity.config.WechatConfig;
import com.activity.modules.pay.entity.PayEntity;
import com.activity.modules.pay.entity.PayReultEntity;
import com.activity.modules.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;


/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/12 16:33
 * @description：
 * @modified By：
 * @version:
 */
@Api(tags = "支付模块")
@RestController
@RequestMapping("/wx")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     *  微信请求支付controller
     * @param request
     * @param payEntity
     * @return
     */
    @ApiOperation(value = "请求支付接口")
    @RequestMapping(value = "/api/wxPay", method = RequestMethod.POST)
    public ResponseUtil wxPay(HttpServletRequest request, PayEntity payEntity) {
        System.out.println("payEntity = " + payEntity);
        Map<String, Object> map = payService.wxPay(request, payEntity);
        return ResponseUtil.success(map);
    }

    //这里是支付回调接口，微信支付成功后会自动调用
    @RequestMapping(value = "/wxPay/wxNotify", method = RequestMethod.POST)
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(new Date());
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";

        Map<String, String> map = PayUtil.doXMLParse(notityXml);
        String returnCode = (String) map.get("return_code");
        if ("SUCCESS".equals(returnCode)) {
            System.out.println("进来了");
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数
            String prestr = PayUtil.createLinkString(validParams);
            System.out.println("prestr = " + prestr);
            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            /**if (PayUtil.verify(prestr, (String) map.get("sign"), WechatConfig.key, "utf-8")) {
                //**此处添加自己的业务逻辑代码start**//*
                System.out.println("验证成功了 = ");
                //注意要判断微信支付重复回调，支付成功后微信会重复的进行回调

                //**此处添加自己的业务逻辑代码end**//*
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }**/
            if (WXPayUtil.isSignatureValid(notityXml, WechatConfig.key) ) {
                /**此处添加自己的业
                 * 至于返回结果可自己看着判断返回
                 * 至于支付成功会返回什么结果去下面去看
                 * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_7&index=8
                 * 	Map map =  WXPayUtil.xmlToMap(notityXml);
                 map即为返回结果的转换封装xml
                 *
                 *
                 * 务逻辑代码start**/
                //插入支付订单
                PayReultEntity payReultEntity = new PayReultEntity();
                payReultEntity.setTransactionId(map.get("transaction_id"));
                payReultEntity.setNonceStr(map.get("nonce_str"));
                payReultEntity.setBankType(map.get("bank_type"));
                payReultEntity.setOpenid(map.get("openid"));
                payReultEntity.setSign(map.get("sign"));
                payReultEntity.setFeeType(map.get("fee_type"));
                payReultEntity.setMchId(map.get("mch_id"));
                payReultEntity.setCashFee(map.get("cash_fee"));
                payReultEntity.setOutTradeNo(map.get("out_trade_no"));
                payReultEntity.setAppid(map.get("appid"));
                payReultEntity.setTotalFee(map.get("total_fee"));
                payReultEntity.setTradeType(map.get("trade_type"));
                payReultEntity.setResultCode(map.get("result_code"));
                payReultEntity.setTimeEnd(map.get("time_end"));
                payReultEntity.setIsSubscribe(map.get("is_subscribe"));
                payReultEntity.setReturnCode(map.get("return_code"));
                payService.createPayMessage(payReultEntity);
                System.out.println("生成订单啦");

                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

            }
        } else {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }

        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


    @RequestMapping("/api/order/create")
    public ResponseUtil orderCreate (PayEntity payEntity){
        Integer status = payService.orderCreate(payEntity);
        return ResponseUtil.success(status);
    }


    @RequestMapping("/api/order/updatePayStatus")
    public ResponseUtil updatePayStatus (Integer roomId,String openId){
        payService.updatePayStatus(roomId,openId);
        return ResponseUtil.success();
    }
}
