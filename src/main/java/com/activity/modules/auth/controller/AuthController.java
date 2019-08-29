package com.activity.modules.auth.controller;


import cn.hutool.json.JSONObject;
import com.activity.common.exception.JcException;
import com.activity.common.utils.AesCbcUtil;
import com.activity.common.utils.HttpRequest;
import com.activity.common.utils.ResponseUtil;
import com.activity.modules.auth.entity.UserEntity;
import com.activity.modules.auth.service.UserService;
import com.activity.modules.room.entity.RoomVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/4 11:30
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/wx/user")
public class AuthController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户userInfo
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
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
        String access_token = (String)getSrJson.get("access_token");
        map.put("access_token",access_token);
        return ResponseUtil.success(map);
    }


    /**
     *  微信信息存入数据库
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)
    public ResponseUtil decodeUserInfo(UserEntity userEntity) {
        /*
        Map map = new HashMap();

        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            throw new JcException("code不能为空");
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wxdea09027e1bab606";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "9bdd8d777468867c36935bdaedec6ea3";
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        //////////////// ////////////////
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求
        System.out.println("params = " + params);
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        System.out.println("sr = " + sr);
        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        System.out.println("json = " + json);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        UserEntity userEntity = new UserEntity();
        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = new JSONObject(result);

                userEntity.setOpenId((String)userInfoJSON.get("openId"));
                userEntity.setNickName((String)userInfoJSON.get("nickName"));
                userEntity.setGender((Integer)userInfoJSON.get("gender"));
                userEntity.setCity((String)userInfoJSON.get("city"));
                userEntity.setProvince((String)userInfoJSON.get("province"));
                userEntity.setCountry((String)userInfoJSON.get("country"));
                userEntity.setAvatarUrl((String)userInfoJSON.get("avatarUrl"));
                userEntity.setUnionId((String)userInfoJSON.get("unionId"));*/

           /* } else {
                map.put("status", 0);
                map.put("msg", "解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
           //每次授权判断是否有opnId,如果有则不插入数据库
        EntityWrapper<Integer> wrapper = new EntityWrapper<>();
        Integer openId = userService.checkOpenId(wrapper, userEntity.getOpenId());
        if(openId != null && openId != 0){
            return ResponseUtil.success();
        }
        userService.insertUser(userEntity);
        return ResponseUtil.success();
    }



}
