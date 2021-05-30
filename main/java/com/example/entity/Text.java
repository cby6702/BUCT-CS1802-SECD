package com.example.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;


@Data
@TableName("text")
public class Text extends Model<Text> {
    /**
      * 主键
      */
    @TableId(value = "tid", type = IdType.AUTO)
    private Long tid;

    /**
      * 发送的消息 
      */
    private String comtext;

    /**
      * 发送至用户id 
      */
    private Integer uid;

    /**
      * 是否可见 
      */
    private String ttyoe;

}