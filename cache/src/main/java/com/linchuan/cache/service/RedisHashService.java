package com.linchuan.cache.service;

import com.linchuan.cache.bean.Blog;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisHashService {
  private final RedisTemplate<String, Object> redisTemplate;
  private final BoundHashOperations<String, Integer, Blog> boundHashOperations;

  public RedisHashService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    boundHashOperations = this.redisTemplate.boundHashOps("blogHashMap");
  }

  public void setBlogs(List<Blog> blogs) {
    blogs.forEach(b -> boundHashOperations.put(b.getId(), b));
  }

  public List<Blog> getBlogs() {
    return boundHashOperations.values();
  }
}
