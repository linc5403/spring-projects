package com.linchuan.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.linchuan.cache")
@EnableCaching
public class CacheApplication {
  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(CacheApplication.class, args);
    //        BlogDao blogDao = context.getBean(BlogDao.class);
    //        List<Blog> blogs = blogDao.findAllBlogs();
    //        System.out.println(blogs);
    //        BlogService blogService = context.getBean(BlogService.class);
    //        List<Blog> blogs = blogService.findBlogs(true);
    //        System.out.println(blogs);
    //    RedisListService service = context.getBean(RedisListService.class);
    /*var service = context.getBean(RedisHashService.class);
    var blog = new Blog(1, "abc", "123", 1, new Date());
    var blog2 = new Blog(2, "abc", "123", 1, new Date());
    List<Blog> blogs = new ArrayList<>();
    blogs.add(blog);
    blogs.add(blog2);
    service.setBlogs(blogs);
    var rBlogs = service.getBlogs();
    for (int i = 0; i < rBlogs.size(); i++) {
      System.out.println(rBlogs.get(i));
    }*/
    //    rBlogs.forEach(System.out::println);
    //    System.out.println(service.setBlogs(blogs));
  }
}
