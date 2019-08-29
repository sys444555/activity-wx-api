package com.activity.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author ：fenghuang
 * @date ：Created in 2019/3/27 11:40
 * @description：Redis序列化工具类
 * @modified By：
 * @version:
 */
public class FastJsonSerializerUtil<T> implements RedisSerializer<T> {

    public static final Charset charset = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJsonSerializerUtil(Class<T> clazz){
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null){
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(charset);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null && bytes.length <= 0){
            return null;
        }
        String str = bytes.toString();
        return JSON.parseObject(str,clazz);
    }
}
