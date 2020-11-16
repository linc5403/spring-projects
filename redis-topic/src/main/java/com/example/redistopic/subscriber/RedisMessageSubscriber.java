package com.example.redistopic.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RedisMessageSubscriber implements MessageListener {
    @Override
    // 固定写法, 接受到消息时调用下面这个方法, pattern就是配置订阅的pattenTopic时的patten
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message receive" + message);
        if (pattern != null) {
            System.out.println(new String(pattern));
        }
    }
}
