package com.activity.modules.auth.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.activity.basic.WxAccount;
import com.activity.common.exception.JcException;
import com.activity.common.utils.HttpRequest;
import com.activity.common.utils.ResponseUtil;
import com.activity.common.utils.WebGetTokenUtils;
import com.activity.config.JwtConfig;
import com.activity.modules.auth.entity.UserEntity;
import com.activity.modules.auth.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/4 11:30
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/wx")
public class AuthController {

    @Autowired
    private UserService userService;


    @Autowired
    private JwtConfig jwtConfig;

    @RequestMapping(value = "/user/check-token", method = RequestMethod.POST)
    public ResponseUtil checkToken(String token){

        if(StringUtils.isNotEmpty(token)){
            boolean b = jwtConfig.verifyToken(token);
            if(!b){
                return ResponseUtil.success(2000, "当前登录token无效，请重新登录");
            }
            return ResponseUtil.success();
        }else {
            throw new JcException("传入token为空");
        }
    }

    /**
     * 获取用户userInfo
     */
    @RequestMapping(value = "/user/getUserInfo", method = RequestMethod.POST)
    public ResponseUtil getUserInfo(String appid, String secret, String js_code, String grantType){

        Map map = new HashMap();

        // 登录凭证不能为空
        if (js_code == null || js_code.length() == 0) {
            throw new JcException("code不能为空");
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = appid;
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = secret;
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        //////////////// ////////////////
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + js_code + "&grant_type=" + grantType;
        // 发送请求
        System.out.println("params = " + params);
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        System.out.println("sr = " + sr);
        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        System.out.println("json = " + json);
        // 获取会话密钥（session_key）
        if(json.containsKey("errmsg")){
            throw new JcException(json.containsKey("errmsg") + "");
        }

        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        map.put("session_key", session_key);
        map.put("openid", openid);
        String client_credential = "client_credential";
        String params1 = "&grant_type=" + client_credential +"&appid=" + wxspAppid + "&secret=" + wxspSecret  ;
        String getSr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",params1);
        JSONObject getSrJson = new JSONObject(getSr);
        if(json.containsKey("errmsg")){
            throw new JcException(json.containsKey("errmsg") + "");
        }
        String access_token = (String)getSrJson.get("access_token");

        WxAccount wxAccount = new WxAccount();
        wxAccount.setOpenId(openid);
        wxAccount.setSessionKey(session_key);
        wxAccount.setLastTime(new Date());
        System.out.println("jwtConfig = " + jwtConfig);
        String tokenByWxAccount = jwtConfig.createTokenByWxAccount(wxAccount);
        map.put("access_token", tokenByWxAccount);
        return ResponseUtil.success(map);
    }

    /**
     *  微信信息存入数据库
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/api/user/decodeUserInfo", method = RequestMethod.POST)
    public ResponseUtil decodeUserInfo(UserEntity userEntity) {
        String token = WebGetTokenUtils.getToken();
        String openId = jwtConfig.getWxOpenIdByToken(token);
        EntityWrapper<Integer> wrapper = new EntityWrapper<>();
        Integer result = userService.checkOpenId(wrapper, userEntity);
        if(result != null && result != 0){
            return ResponseUtil.success();
        }
        userService.insertUser(userEntity);
        return ResponseUtil.success();
    }


    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public ResponseUtil getUserDetail(String token){
        List<UserEntity> userDetail = userService.getUserDetail();
        return ResponseUtil.success(userDetail);
    }

    @RequestMapping(value ="/api/user/find",method = RequestMethod.GET)
    public ResponseUtil getUserFind(String Openid){
        UserEntity userEntity = userService.getUserFind(Openid);
        return ResponseUtil.success(userEntity);
    }

    @RequestMapping(value="/api/user/vip",method = RequestMethod.POST)
    public ResponseUtil updateUser(String openId){
        userService.updateUser(openId);
        return  ResponseUtil.success();
    }

}
