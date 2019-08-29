package com.activity.modules.auth.service;

import com.activity.modules.auth.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import java.sql.Wrapper;


/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 17:29
 * @description：
 * @modified By：
 * @version:
 */
public interface UserService {

    public Integer checkOpenId(EntityWrapper wrapper, String openId);

    public void insertUser(UserEntity userEntity);

}
