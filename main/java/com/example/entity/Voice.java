package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("voice")
public class Voice extends Model<Voice> {
    /**
      * 主键
      */
    @TableId(value = "vid", type = IdType.AUTO)
    private Long vid;

    /**
      * 是否被评官方 
      */
    private Integer authority;

    /**
      * 展品id 
      */
    private Integer cid;

    /**
      * 展览id 
      */
    private Integer eid;

    /**
      * 博物馆id 
      */
    private Integer mid;

    /**
      * 音频url 
      */
    private String voice;

    /**
      * 博物馆讲解0|展览讲解1|藏品讲解2 
      */
    private Integer type;

    /**
      * 上传用户id 
      */
    private Integer uid;

    /**
      * 0为未审核，1为审核通过，2为审核不通过 
      */
    private Integer vcheck;

}