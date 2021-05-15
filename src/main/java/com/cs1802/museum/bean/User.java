package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int uid;
    private String password;
    private String name;
    private String gender;
    private String birthday;
    private String email;
    private String mobile;
    private String avatarUrl;
    private String lasttime;
    private int logincount;
    private int validstate;
    private String city;
}
