package com.linchuan.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/")
    @Loggable
    @Statistic
    public String showHome(@RequestParam(required = false) String name) {
        String echo = "Hello, this is home, and you are using get";
        if (name==null)
            return echo;
        return echo + ", " + name;
    }
}
