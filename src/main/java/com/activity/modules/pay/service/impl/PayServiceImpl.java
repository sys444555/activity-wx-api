package com.activity.modules.pay.service.impl;

import com.activity.modules.room.mapper.RoomMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.activity.common.exception.JcException;
import com.activity.common.utils.HttpRequest;
import com.activity.common.utils.PayUtil;
import com.activity.common.utils.UUIDUtils;
import com.activity.config.WechatConfig;
import com.activity.modules.pay.entity.PayEntity;
import com.activity.modules.pay.entity.PayReultEntity;
import com.activity.modules.pay.mapper.PayMapper;
import com.activity.modules.pay.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/16 18:00
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, PayEntity> implements PayService {

    @Resource
    private PayMapper payMapper;

    @Resource
    private RoomMapper roomMapper;

    /**
     *  微信统一下单
     * @param payEntity
     * @return
     */
    @Override
    public Map<String, Object> wxPay(HttpServletRequest request, PayEntity payEntity) {

        //返回给小程序端需要的参数
        Map<String, Object> response = null;
        try {
            //生成的随机字符串
            String nonce_str = UUIDUtils.getRandomStringByLength(32);
            //商品名称
            String body = "abc";
            //获取客户端的ip地址
            String spbill_create_ip = HttpRequest.getIpAddr(request);

            //组装参数，用户生成统一下单接口的签名
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid", WechatConfig.appid);
            packageParams.put("mch_id", WechatConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", payEntity.getBody());
            packageParams.put("out_trade_no", payEntity.getOrderOn());//商户订单号,自己的订单ID
            packageParams.put("total_fee", payEntity.getPayNum());//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WechatConfig.notify_url);//支付成功后的回调地址
            packageParams.put("trade_type", WechatConfig.TRADETYPE);//支付方式
            packageParams.put("openid", payEntity.getOpenId());//用户的openID，自己获取

            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WechatConfig.key, "utf-8").toUpperCase();
            System.out.println("mysign = " + mysign);
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WechatConfig.appid + "</appid>"
                    + "<body>" + payEntity.getBody() + "</body>"
                    + "<mch_id>" + WechatConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WechatConfig.notify_url + "</notify_url>"
                    + "<openid>" + payEntity.getOpenId() + "</openid>"
                    + "<out_trade_no>" + payEntity.getOrderOn() + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + payEntity.getPayNum() + "</total_fee>"//支付的金额，单位：分
                    + "<trade_type>" + WechatConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            System.out.println("xml = " + xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WechatConfig.pay_url, "POST", xml);

            // 将解析结果存储在HashMap中
            Map<Object,Object> map = PayUtil.doXMLParse(result);
            for(Map.Entry<Object,Object> entry : map.entrySet()){
                System.out.println("entry = " + entry.getKey());
                System.out.println("entry.getValue() = " + entry.getValue());
            }
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");//返回状态码
            System.out.println("result_code = " + result_code);
            System.out.println("return_code = " + return_code);
            response = new HashMap<String, Object>();

            if (return_code.equals("SUCCESS") && return_code.equals(result_code)) {
                System.out.println(" hello");
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + WechatConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WechatConfig.key, "utf-8").toUpperCase();

                response.put("paySign", paySign);
            }

            response.put("appid", WechatConfig.appid);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void createPayMessage(PayReultEntity payReultEntity) {
        //插入微信支付订单
        Integer payMessage = payMapper.createPayMessage(payReultEntity);
        if(payMessage != null && payMessage != 0){
            //微信支付订单id关联订单id
            Integer result = payMapper.updateOrderStatus(payReultEntity.getTransactionId(),
                    payReultEntity.getOutTradeNo(), payReultEntity.getOpenid());
            if(result == null || result != 1){
                throw new JcException("更新订单信息失败");
            }

        }else {
            throw new JcException("更新订单信息失败");
        }

    }

    @Override
    public Integer orderCreate(PayEntity payEntity) {
        Integer status = payMapper.insert(payEntity);
        return status == 1 ? 1 : 0;
    }

    @Override
    public void updatePayStatus(Integer roomId,String openId) {

        payMapper.updatePayStatus(roomId,openId);

        roomMapper.updateRoomJoinAccount(roomId);


    }


}
