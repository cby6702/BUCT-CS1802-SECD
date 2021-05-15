package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllComments {
    private int aid;
    private int uid;
    private int mid;
    private int exhibitionstar;
    private int servicestar;
    private int environmentstar;
    private double general_comment;
    private String comment;
    private String picture;
    private String account;
    private String avatarUrl;
}
