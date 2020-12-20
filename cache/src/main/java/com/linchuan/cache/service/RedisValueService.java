package com.linchuan.cache.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linchuan.cache.bean.Blog;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.List;

@Service
public class RedisValueService {
  private final RedisTemplate<String, String> redisTemplate;

  private final Gson gson;

  public RedisValueService(RedisTemplate<String, String> redisTemplate, Gson gson) {
    this.redisTemplate = redisTemplate;
    this.gson = gson;
  }

  public void setBlogToCache(Blog blog) {
    // blog使用的cache对应的key是什么
    String key = "blog:" + blog.getId();
    BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
    ops.set(gson.toJson(blog));
  }

  public Blog getBlogFromCache(Integer id) {
    String key = "blog:" + id;
    BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
    String json = ops.get();
    return gson.fromJson(json, Blog.class);
  }

  public void removeBlogInCache(Integer id) {
    String key = "blog:" + id;
    redisTemplate.delete(key);
  }

  public void setBlogs(List<Blog> blogs) {
    String key = "blogs";
    var ops = redisTemplate.boundListOps(key);

    //    var ops = redisTemplate.boundValueOps(key);
    //    ops.set(gson.toJson(blogs));
  }

  public List<Blog> getBlogs() {
    String key = "blogs";
    var ops = redisTemplate.boundValueOps(key);
    var json = ops.get();
    Type listType = new TypeToken<List<Blog>>() {}.getType();
    return gson.fromJson(json, listType);
  }

  public void clearBlogs() {
    String key = "blogs";
    redisTemplate.delete(key);
  }

  public void test() {
    // BoundValueOperations<String, String> valueOps = redisTemplate.boundValueOps("haha");
    Blog blog = new Blog();
    blog.setId(1);
    blog.setContent("abcd");
    blog.setTitle("title");

    String redisKey = "blog::" + blog.getId();

    BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(redisKey);

    System.out.println(ops.get());

    // set cache
    String jsonStr = gson.toJson(blog);
    ops.set(jsonStr, Duration.ofMinutes(5));

    // get cache
    String jsonFromCache = ops.get();
    Blog rlt = gson.fromJson(jsonFromCache, com.linchuan.cache.bean.Blog.class);
    System.out.println(rlt);

    // delete
    redisTemplate.delete(redisKey);
  }
}
