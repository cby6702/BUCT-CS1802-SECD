package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    private int aid;
    private int uid;
    private int mid;
    private int exhibitionstar;
    private int servicestar;
    private int environmentstar;
    private float general_comment;
    private String comment;
    private String picture;
}
