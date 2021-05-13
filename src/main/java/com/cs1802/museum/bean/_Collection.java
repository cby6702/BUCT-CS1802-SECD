package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class _Collection {
    private int cid;
    private String cname;
    private int mid;
    private String colpicture;
    private String cintroduce;
}
