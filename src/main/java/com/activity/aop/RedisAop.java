package com.activity.aop;


import com.activity.common.annotation.RedisCache;
import com.activity.common.exception.JcException;
import com.activity.common.utils.RedisUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/26 19:10
 * @description：Redis缓存AOP
 * @modified By：
 * @version:
 */
@Aspect
@Component
public class RedisAop {

    public static final Logger logger = LoggerFactory.getLogger(RedisAop.class);

    @Autowired
    private RedisUtil redisUtil;

    /**
     *  给所有使用RedisCache注解的方法设置切点
     */
    @Pointcut("@annotation(com.activity.common.annotation.RedisCache)")
    public void redisCache(){}

    /**
     *  给所有业务的数据的插入，更新，删除设置切点
     */
    @Pointcut("execution(* com.activity.modules.*.service..*.insert*(..)) || " +
              "execution(* com.activity.modules.*.service..*.batch*(..)) || " +
              "execution(* com.activity.modules.*.service..*.update*(..)) || " +
              "execution(* com.activity.modules.*.service..*.delete*(..))" )
    public void cleanCache(){}

    @Around("redisCache()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{

        System.out.println("RedisAop.around");
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //获取注解类
        RedisCache annotation = methodSignature.getMethod().getAnnotation(RedisCache.class);
        //获取注解key的值
        String key = annotation.key();
        if(key == null && key == ""){
            throw new JcException("后台需要设置缓存的键");
        }
        if(key.isEmpty()){
            logger.info("RedisCache注解传入的key为空");
        }
        //判断redis是否存在当前key
        if(!redisUtil.hasKey(key)){
            Object proceed = joinPoint.proceed();
            final Object[] args = joinPoint.getArgs();
            //设置失效时间

            boolean isSetCache = redisUtil.set(key,proceed, annotation.time());
            if(!isSetCache){
                logger.info("设置redis缓存失败");
                return new JcException("设置redis缓存失败");
            }
            return proceed;
        }

        //从缓存中获取数据
        Object cacheBytes = redisUtil.get(key);
        System.out.println("cacheBytes = " + cacheBytes);
        if(cacheBytes == null){
            logger.info("从redis缓存中获取数据失败");
            return new JcException("从redis缓存中获取数据失败");
        }
        return cacheBytes;
    }

    @Around("cleanCache()")
    public void cleanCacheAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("joinPoint = [" + joinPoint + "]");
        Object object = joinPoint.proceed();
        //通过java反射获取当前AOP截取的类
        Class clazz = Class.forName(joinPoint.getTarget().getClass().getName());
        //获取当前类所有的方法
        Method[] methods = clazz.getMethods();
        for(Method method :methods){
            boolean annotationPresent = method.isAnnotationPresent(RedisCache.class);
            //判断当前方法是否有RedisCache缓存注解
            if(annotationPresent){
                RedisCache annotation = method.getAnnotation(RedisCache.class);
                //获取redis缓存注解的key
                String key = annotation.key();
                if(key.isEmpty()){
                    logger.info("当前RedisCache注解没有key");
                    throw  new JcException("当前RedisCache注解没有key");
                }
                redisUtil.deleteCache(key);
            }
        }
    }

}
