package com.example.redistopic.controller;

import com.example.redistopic.publisher.RedisMessagePublisher;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TriggerController {
    @Autowired
    private RedisMessagePublisher publisher;

    @Autowired
    private Gson gson;

    @GetMapping("/trigger")
    HashMap<String, String> trigger(@RequestParam String key, @RequestParam String value) {
        HashMap<String, String> map = new HashMap<>();
        map.put(key, value);
        publisher.publish(gson.toJson(map));
        return map;
    }
}
