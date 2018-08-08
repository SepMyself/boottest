package com.example.boottest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/strredis")
public class RedisStringController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/{para}")
    public @ResponseBody String env(@PathVariable String para) throws Exception {
        redisTemplate.opsForValue().set("testenv", para);
        String str = redisTemplate.opsForValue().get("testenv");

        return str;
    }
}
