package com.linchuan.cache.service;

import com.linchuan.cache.bean.Blog;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisListService {
  private final RedisTemplate<String, Object> redisTemplate;
  private final BoundListOperations<String, Object> boundListOperations;

  public RedisListService(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.boundListOperations = this.redisTemplate.boundListOps("Blogs");
  }

  public Boolean setBlogs(List<Blog> blogs) {
    /*Long currentLen = 0L;
    for (Blog blog : blogs) {
      Long r = boundListOperations.rightPush(blog);
      if (r != (++currentLen)) {
        return false;
      }
    }*/
    Long r = boundListOperations.rightPushAll(blogs.toArray());
    return r != null && r == blogs.size();
  }
}
