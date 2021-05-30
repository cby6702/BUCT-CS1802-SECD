package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

@Data
@TableName("museums")
public class Museums extends Model<Museums> {
    /**
      * 主键
      */
    @TableId(value = "mid", type = IdType.AUTO)
    private Long mid;

    /**
      * 年展览次数 
      */
    private Integer annualExhibitions;

    /**
      * 所在城市 
      */
    private String city;

    /**
      * 环境评分 
      */
    private Double environmentScore;

    /**
      * 展览评分 
      */
    private Double exhibitionScore;

    /**
      * 总评 
      */
    private Double generalScore;

    /**
      * 基本介绍 
      */
    private String introduction;

    /**
      * 维度 
      */
    private BigDecimal lat;

    /**
      * 经度 
      */
    private BigDecimal lng;

    /**
      * 博物馆名 
      */
    private String name;

    /**
      * 开放时间 
      */
    private String opentime;

    /**
      * 图片url 
      */
    private String picture;

    /**
      * 服务评分 
      */
    private Double serviceScore;

}