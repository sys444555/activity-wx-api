package com.activity.common.annotation;

import java.lang.annotation.*;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/28 10:27
 * @description：清理缓存annotation注解
 * @modified By：
 * @version:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CleanCache {

    String key() default "";
}
