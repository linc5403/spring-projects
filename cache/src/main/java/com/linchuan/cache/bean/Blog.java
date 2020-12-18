package com.linchuan.cache.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
// 如果只是想将对象放进Redis, 必须将对象序列化, 否则会导致无法放入redis
// public class Blog implements Serializable {
public class Blog implements Serializable {
  Integer id;
  String title;
  String Content;
  Integer userId;
  Date createdTime;

  public Blog(Integer id, String title, String content, Integer userId, Date createdTime) {
    this.id = id;
    this.title = title;
    Content = content;
    this.userId = userId;
    this.createdTime = createdTime;
  }

  public Blog() {}
}
