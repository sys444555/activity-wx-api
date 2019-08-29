package com.activity.modules.auth.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.activity.common.utils.GetOpenIdUtils;
import com.activity.modules.auth.entity.UserEntity;
import com.activity.modules.auth.mapper.UserMapper;
import com.activity.modules.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

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

    @Autowired
    private GetOpenIdUtils getOpenIdUtils;

    @Override
    public Integer checkOpenId(EntityWrapper wrapper, UserEntity userEntity) {
        String openId = userEntity.getOpenId();
        //根据openId判断数据库是否存在
        Integer openIdAccount = userMapper.getCount(openId);
        System.out.println("openIdAccount = " + openIdAccount);

        if(openIdAccount != null && openIdAccount != 0){
            wrapper.eq("open_id", openId);
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] bytes = new byte[0];
            try {
                bytes = userEntity.getNickName().getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String nameEncode = encoder.encode(bytes);
            System.out.println("nameEncode = " + nameEncode);
            userEntity.setNickName(nameEncode);
            Integer update = userMapper.update(userEntity, wrapper);
            return update;
        }else {
            return openIdAccount;
        }

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

    @Override
    public List<UserEntity> getUserDetail() {
        EntityWrapper<UserEntity> entityEntityWrapper = new EntityWrapper<>();
        List<UserEntity> userEntities = userMapper.selectList(entityEntityWrapper);
        return userEntities;
    }

    @Override
    public UserEntity getUserFind(String Openid) {
        UserEntity userEntity = userMapper.selectVip(Openid);
        return userEntity;
    }

    @Override
    public void updateUser(String openId) {
        userMapper.updateVip(openId);
    }
}
