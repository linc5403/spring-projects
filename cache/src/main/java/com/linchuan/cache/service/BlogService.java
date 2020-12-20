package com.linchuan.cache.service;

import com.linchuan.cache.bean.Blog;
import com.linchuan.cache.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
  private BlogDao blogDao;

  @Autowired
  public void setBlogDao(BlogDao blogDao) {
    this.blogDao = blogDao;
  }

  @Autowired private RedisValueService redisValueService;

  @Cacheable(value = "blog", key = "{#findAll,#id}")
  public List<Blog> findBlogs(Boolean findAll, Integer id) {
    List<Blog> rlt;
    if (findAll) {
      rlt = blogDao.findAllBlogs();
    } else {
      // 其他查找, 还没写
      rlt = new ArrayList<>();
      // 查找blog
      // DB之前先访问Cache, 如果Cache有了, 直接用
      Blog blog;
      blog = redisValueService.getBlogFromCache(id);
      if (blog == null) {
        // db
        System.out.println("using db.....");
        blog = blogDao.findBlogById(id);
        // save to cache
        redisValueService.setBlogToCache(blog);
      }
      rlt.add(blog);
    }
    return rlt;
  }

  public Integer addBlog(Blog blog) {
    try {
      blogDao.addBlog(blog);
      return 0;
    } catch (Exception e) {
      return -1;
    }
  }

  public Integer updateBlog(Blog blog) {
    redisValueService.removeBlogInCache(blog.getId());
    try {
      blogDao.updateBlog(blog);
      return 0;
    } catch (Exception e) {
      System.out.println(e);
      return -1;
    }
  }
}
