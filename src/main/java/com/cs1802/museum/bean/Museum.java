package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Museum {
    private int mid;
    private String name;
    private String introduction;
    private String opentime;
    private BigDecimal Ing;
    private BigDecimal lat;
    private String picture;
    private String city;
}
