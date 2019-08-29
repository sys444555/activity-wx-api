package com.activity.common.annotation;

import java.lang.annotation.*;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/26 19:07
 * @description：Redis缓存注解
 * @modified By：
 * @version:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisCache {
    String key() default "";

    long time() default 60;
}
