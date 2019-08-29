package com.activity.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/8/19 18:12
 * @description：
 * @modified By：
 * @version:
 */
public class WebGetTokenUtils {


    public static String getToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        return token;
    }
}
