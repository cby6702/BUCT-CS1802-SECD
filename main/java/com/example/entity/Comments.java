package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("comments")
public class Comments extends Model<Comments> {
    /**
      * 主键
      */
    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;

    /**
      * 环境分数(1-5分) 
      */
    private Integer environmentstar;

    /**
      * 展览分数(1-5分) 
      */
    private Integer exhibitionstar;

    /**
      * 总体评价(1-5分) 
      */
    private Double generalComment;

    /**
      * 博物馆id 
      */
    private Integer mid;

    /**
      * 评论 
      */
    private String comment;

    /**
      * 图片 
      */
    private String picture;

    /**
      * 服务分数(1-5分) 
      */
    private Integer servicestar;

    /**
      * 用户id 
      */
    private Integer uid;

}