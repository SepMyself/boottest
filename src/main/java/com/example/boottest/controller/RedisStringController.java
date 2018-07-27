package com.example.boottest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strredis")
public class RedisStringController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/{para}")
    public @ResponseBody String env(@PathVariable String para) throws Exception {
        redisTemplate.opsForValue().set("testenv", para);
        String str = redisTemplate.opsForValue().get("testenv");

        return str;
    }
}
