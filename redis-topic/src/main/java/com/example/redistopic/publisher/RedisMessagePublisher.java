package com.example.redistopic.publisher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;


@Component
public class RedisMessagePublisher implements MessagePublisher {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ChannelTopic topic;

    @Override
    public void publish(String message) {
        // 和config下面的配置没有什么关系, 除了是想发送到那个topic上面去而已
        redisTemplate.convertAndSend(topic.getTopic(), message);
        // 上面这句话和下面这句等效
        //redisTemplate.convertAndSend("redis-topic", message);
    }
}
