package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("exhibition")
public class Exhibition extends Model<Exhibition> {
    /**
      * 主键
      */
    @TableId(value = "eid", type = IdType.AUTO)
    private Long eid;

    /**
      * 博物馆名 
      */
    private String name;

    /**
      * 展览名称 
      */
    private String ename;

    /**
      * 展览简介 
      */
    private String eintroduce;

    /**
      * 开始日期 
      */
    private String startdate;

    /**
      * 对应博物馆id 
      */
    private Integer mid;

    /**
      * 结束日期 
      */
    private String enddate;

}