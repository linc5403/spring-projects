package com.linchuan.cache.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linchuan.cache.bean.Blog;
import com.linchuan.cache.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    private BlogService blogService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Cacheable(value = "blog", key = "#id")
    @GetMapping("/blog/{id}")
    public ObjectNode getBlog(@PathVariable Integer id) {
        List<Blog> blogs = blogService.findBlogs(false, id);

        ObjectNode rlt = objectMapper.createObjectNode();

        if (blogs == null || blogs.size() == 0) {
            rlt.put("code", -1);
            rlt.put("message", "no such blog " + id);
        } else {
            rlt.put("code", 0);
            rlt.put("message", "ok");
            rlt.put("blog", String.valueOf(blogs.get(0)));
        }
        return rlt;
    }

    // @CacheEvict(value = "blog", key="#id" )
    // @CacheEvict(value = {"blog", "blogs"}, key = "#id", allEntries = true)
    @Caching(
        evict = {
            @CacheEvict(value = "blogs", allEntries = true),
            @CacheEvict(value = "blog", key = "#id")
        }
    )
    @PutMapping("/blog/{id}")
    public ObjectNode putBlog(@PathVariable Integer id,
                              @RequestParam String title,
                              @RequestParam String content) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setId(id);
        Integer r = blogService.updateBlog(blog);
        ObjectNode rlt = objectMapper.createObjectNode();

        if (r != 0) {
            rlt.put("code", -1);
            rlt.put("message", "update blog " + id + " failed!");
        } else {
            rlt.put("code", 0);
            rlt.put("message", "ok");
        }
        return rlt;
    }

    @GetMapping("/blog")
    @Cacheable("blogs")
    public ObjectNode getBlogs() {
        List<Blog> blogs =  blogService.findBlogs(true, 0);
        // return blogs;

        System.out.println("using blogService to process!!!!");
        ObjectNode rlt = objectMapper.createObjectNode();
        ArrayNode list = objectMapper.valueToTree(blogs);
        if (blogs == null) {
            rlt.put("code", -1);
            rlt.put("message", "can't find any blog");
        } else {
            rlt.put("code", 0);
            rlt.put("message", "ok");
        }
        rlt.putArray("blogs").addAll(list);
        return rlt;
    }

    @PostMapping("/blog")
    @CacheEvict(value = "blogs", allEntries = true)
    public ObjectNode postBlog(@RequestParam String title,
                               @RequestParam String content,
                               @RequestParam Integer userId) {
        Blog blog = new Blog();
        blog.setContent(content);
        blog.setTitle(title);
        blog.setUserId(userId);
        Integer id = blogService.addBlog(blog);

        // 构造返回的Json对象
        ObjectNode rlt = objectMapper.createObjectNode();
        if (id != -1) {
            rlt.put("code", 0);
            rlt.put("message", "ok");
            rlt.put("id", blog.getId());
        } else {
            rlt.put("code", id);
            rlt.put("message", "ok");
        }
        return rlt;
    }
}
