package com.activity.modules.auth.mapper;


import com.activity.modules.auth.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/7/2 19:05
 * @description：
 * @modified By：
 * @version:
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    public Integer getCount(@Param(value = "openId") String openId);
}
