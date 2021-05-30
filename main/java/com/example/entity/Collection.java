package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("collection")
public class Collection extends Model<Collection> {
    /**
      * 主键
      */
    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    /**
      * 藏品名称 
      */
    private String cname;

    /**
      * 博物馆名称 
      */
    private String name;

    /**
      * 所属博物馆id 
      */
    private Integer mid;

    /**
      * 藏品图片url 
      */
    private String colpicture;

    /**
      * 藏品简介 
      */
    private String cintroduce;

    /**
      * 删除时间 
      */
    private Integer deleteTime;

}