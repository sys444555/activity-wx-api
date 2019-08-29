package com.activity.config;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/12 16:35
 * @description：
 * @modified By：
 * @version:
 */
public class WechatConfig {
    //小程序appid
    public static final String appid = "wxfd945e2b4a767dc3";
    //微信支付的商户id
    public static final String mch_id = "1543400671";
    //微信支付的商户密钥
    public static final String key = "14789632147896321478963214789632";
    //支付成功后的服务器回调url，这里填PayControlle里的回调函数地址
    public static final String notify_url = "http://123.207.230.97:8099/wxNotify";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
