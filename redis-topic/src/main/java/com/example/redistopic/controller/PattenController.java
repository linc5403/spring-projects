package com.example.redistopic.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PattenController {

    @Autowired
    Gson gson;

    @Autowired
    RedisTemplate<String, String> template;

    @GetMapping("/patten")
    HashMap<String, String> patten(@RequestParam String key, @RequestParam String value) {
        HashMap<String, String > map = new HashMap<>();
        map.put(key, value);
        // 所以可以直接使用redisTemplate的convertAndSend方法发布消息
        template.convertAndSend("redis-pattern", gson.toJson(map));
        return map;
    }
}
