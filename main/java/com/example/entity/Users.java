package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@TableName("users")
public class Users extends Model<Users> {
    /**
      * 主键
      */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
      * 用户头像url 
      */
    @TableField(value="avatarUrl")
    private String avatarUrl;

    /**
      * 生日 
      */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    /**
      * 上次选择的城市 
      */
    private String city;

    /**
      * 邮箱 
      */
    private String email;

    /**
      * 性别 
      */
    private String gender;

    /**
      * 最后登录时间 
      */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date lasttime;

    /**
      * 登录次数 
      */
    private Integer logincount;

    /**
      * 手机 
      */
    private String mobile;

    /**
      * 姓名 
      */
    private String name;

    /**
      * 密码 
      */
    private String password;

    /**
      * 是否有效（无效0有效1） 
      */
    private Integer validstate;

}