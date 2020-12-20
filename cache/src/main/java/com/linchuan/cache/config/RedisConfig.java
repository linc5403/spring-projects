package com.linchuan.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
  @Bean
  RedisConnectionFactory redisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
    template.setConnectionFactory(redisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    template.setHashKeySerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

    return template;
  }
}
