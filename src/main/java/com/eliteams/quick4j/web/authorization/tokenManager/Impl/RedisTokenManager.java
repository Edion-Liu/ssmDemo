package com.eliteams.quick4j.web.authorization.tokenManager.Impl;

import com.eliteams.quick4j.core.Config.Constants;
import com.eliteams.quick4j.web.authorization.tokenManager.TokenManager;
import com.eliteams.quick4j.web.authorization.tokenModel.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @作者 刘宝军
 * Created by Edion on 2017/2/16.
 */
@Component
public class RedisTokenManager implements TokenManager{


    private RedisTemplate<Long,String> redisTemplate;

    @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redisTemplate = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Override
    public TokenModel createToken(long userId) {
        String token= UUID.randomUUID().toString().replace("-","");
        TokenModel model=new TokenModel(userId,token);
        //存储到redis并设置过期时间   把value的对象保存在一个key对应的value里面
        redisTemplate.boundValueOps(userId).set(token,Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redisTemplate.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(long userId) {
        redisTemplate.delete(userId);
    }
}
