package com.example.redistopic.config;

import com.example.redistopic.subscriber.RedisMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 配置思路:
 * 需要一个listener的容器, 因为可能会监听多个topic
 *    1 容器需要配置和服务器的连接, 因此需要配置ConnectionFactory
 *    2 向容器中添加MessageListener
 *        2.1 Listener需要制定具体干活的人, 也就是我们自己实现的RedisMessageSubscriber
 *        2.2 将这个Listener和channelTopic绑定在一起
 *    https://zhuanlan.zhihu.com/p/59065399
 */
@Configuration
public class RedisTopicConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("redis-topic");
    }

    // 通配符
    PatternTopic patternTopic() {
        return new PatternTopic("redis-*");
    }

    @Autowired
    RedisMessageSubscriber listener;

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        // 精确匹配
        container.addMessageListener(listener, topic());
        // 模式匹配
        container.addMessageListener(listener, patternTopic());
        return container;
    }

}
