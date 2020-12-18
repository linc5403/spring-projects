package com.linchuan.cache.dao;

import com.linchuan.cache.bean.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
  List<Blog> findAllBlogs();

  Blog findBlogById(Integer id);

  void addBlog(Blog blog);

  void updateBlog(Blog blog);
}
