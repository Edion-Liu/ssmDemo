package com.eliteams.quick4j.web.cache.sample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @作者 刘宝军
 * Created by Edion on 2017/2/21.
 */

public class CacheFaceImpl implements CacheFace {

    @Autowired
    public RedisTemplate redisTemplate;


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 查询缓存
     *
     * @param key
     *            缓存key
     * @return
     */

    @Override
    public Object getCache(String key) {

        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;

    }


    /**
     * 设置缓存
     *
     * @param key
     *            缓存key
     * @param value
     *            缓存内容
     * @param timeOut
     *            缓存超时时间/秒
     */

    @Override
    public void setCache(String key, Object value, Integer timeOut) {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     *
     * @param key
     *            缓存key
     * @param value
     *            缓存内容
     */

    @Override
    public void setCache(String key, Object value) {
        ValueOperations<String,Object> operation = redisTemplate.opsForValue();
        operation.set(key,value);
    }


    /**
     * 删除缓存
     *
     * @param key
     *            缓存key
     */

    @Override
    public void delCache(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }

    }


    /**
     * 模糊删除缓存
     *
     * @param key
     *            缓存key
     */
    @Override
    public void delCacheFuzz(String key) {

    }
}
