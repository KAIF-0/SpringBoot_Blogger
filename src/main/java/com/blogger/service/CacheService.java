package com.blogger.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    
    //redis
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> void set(String key, T value, Long expirationInSeconds) {
        System.out.println("caching data for key: " + key + value);
        redisTemplate.opsForValue().set(key, value, expirationInSeconds, TimeUnit.SECONDS);
    }

    public <T> List<T> get(String key, Class<T> classType) {
        return  (List<T>) redisTemplate.opsForValue().get(key);
    }
}
