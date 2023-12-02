package com.shubham.project.spring_network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // ...
}
