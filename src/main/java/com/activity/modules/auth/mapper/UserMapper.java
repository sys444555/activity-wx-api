package com.activity.modules.auth.mapper;



import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.activity.modules.auth.entity.UserEntity;
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

    public UserEntity selectVip(@Param(value = "Openid") String Openid);

    public void updateVip(@Param(value = "openId") String openId);
}
