package com.activity.common.exception;


import com.activity.common.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/11 18:54
 * @description：统一异常处理类
 * @modified By：
 * @version:
 */
@RestControllerAdvice
public class JcExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(JcExceptionHandler.class);

    /**
     * 处理自定义异常
     * @param
     * @return
     */
    @ExceptionHandler(JcException.class)
    public ResponseUtil handleJcException(JcException e){
        logger.error(e.getMsg(),e);
        return ResponseUtil.error(e.getCode(), "服务器端出现异常");
    }

    //处理其他异常类
    /**
     * 处理RuntimeException
     * @param
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseUtil handleRuntimeException(Exception e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.error(500, "服务器端出现异常");
    }

    /**
     * 处理未知异常
     * @param
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseUtil handleException(Exception e){
        logger.error(e.getMessage(),e);
        return ResponseUtil.error(-1, "未知错误");
    }

}
