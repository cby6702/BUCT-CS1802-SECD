package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("news")
public class News extends Model<News> {
    /**
      * 主键
      */
    @TableId(value = "nid", type = IdType.AUTO)
    private Long nid;

    /**
      * 新闻作者 
      */
    private String author;

    /**
      * 是否可评论，1为可评论，0为不可评论 
      */
    private Integer commentable;

    /**
      * 新闻正文内容 
      */
    private String content;

    /**
      * 新闻摘要 
      */
    private String extract;

    /**
      * 图片地址 
      */
    private String imageurl;

    /**
      * 对应博物馆id 
      */
    private Integer mid;

    /**
      * 新闻标题 
      */
    private String title;

    /**
      * 新闻性质，1为正面，0为负面 
      */
    private Integer nature;

    /**
      * 发布时间 
      */
    private String releasetime;

    /**
      * 新闻状态，1为正常，0为异常 
      */
    private Integer status;

    /**
      * 新闻地址 
      */
    private String url;

    /**
      * 
是否可见，1可见，0,不可见 
      */
    private Integer visible;

}