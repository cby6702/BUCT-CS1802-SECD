package com.cs1802.museum.bean;

import lombok.Data;

@Data
public class User {
    int uid;
    String account;
    String password;
    String name;
    String gender;
    String birthday;    //数据库用的类型是datetime yyyy-MM-dd HH:mm:ss 需要格式化时间后再存入数据库
    String email;
    String mobile;
    String lasttime;
    int logincount;
    int validstate;
    String city;
}
