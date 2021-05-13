package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private int nid;
    private String title;
    private String extract;
    private String content;
    private String url;
    private String imageurl;
    private String author;
    private Date releasetime;
    private int mid;
    private int status;
    private int nature;
    private int commentable;
    private int visible;
}
