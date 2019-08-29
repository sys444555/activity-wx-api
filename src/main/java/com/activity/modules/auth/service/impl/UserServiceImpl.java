package com.activity.modules.auth.service.impl;

import com.activity.common.exception.JcExceptionHandler;
import com.activity.modules.auth.entity.UserEntity;
import com.activity.modules.auth.mapper.UserMapper;
import com.activity.modules.auth.service.UserService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Wrapper;
import java.util.Base64;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:31
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    @Override
    public Integer checkOpenId(EntityWrapper wrapper, String openId) {
        //根据openId判断数据库是否存在
        Integer openIdAccount = userMapper.getCount(openId);
        System.out.println("openIdAccount = " + openIdAccount);
        return openIdAccount;
    }

    @Override
    public void insertUser(UserEntity userEntity) {

        BASE64Encoder encoder = new BASE64Encoder();

        System.out.println("userEntity = " + userEntity.getNickName());
        try {
            //防止数据库不支持特殊符号,对微信名进行编码
            byte[] bytes = userEntity.getNickName().getBytes("UTF-8");
            String nameEncode = encoder.encode(bytes);
            System.out.println("nameEncode = " + nameEncode);
            userEntity.setNickName(nameEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userMapper.insert(userEntity);
    }
}
