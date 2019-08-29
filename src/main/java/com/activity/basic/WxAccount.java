package com.activity.basic;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/8/16 12:01
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class WxAccount {


    /**
     *  id
     */
    private Integer id;

    /**
     * 微信sessionKeyopenId
     */
    private String openId;

    /**
     * 微信sessionKey
     */
    private String sessionKey;


    /**
     * 微信accessToken
     */
    private String accessToken;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastTime;

}
