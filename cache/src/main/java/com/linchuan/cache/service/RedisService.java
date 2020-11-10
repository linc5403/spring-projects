package com.linchuan.cache.service;

import com.google.gson.Gson;
import com.linchuan.cache.bean.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private Gson gson;

    public void setBlogToCache(Blog blog) {
        // blog使用的cache对应的key是什么
        String key = "blog:"+blog.getId();
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

    public void test() {
        //BoundValueOperations<String, String> valueOps = redisTemplate.boundValueOps("haha");
        Blog blog = new Blog();
        blog.setId(1);
        blog.setContent("abcd");
        blog.setTitle("title");

        String redisKey = "blog::" + blog.getId();

        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(redisKey);

        System.out.println(ops.get());

//        Gson gson = new Gson();
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
