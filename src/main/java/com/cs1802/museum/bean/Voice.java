package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voice {
    private int vid;
    private String voice;
    private int uid;
    private int vcheck;
    private int authority;
    private int type;
    private int mid;
    private int cid;
    private int eid;
}
