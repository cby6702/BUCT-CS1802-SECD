package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private double exhibition_score;
    private double environment_score;
    private double service_score;
    private double general_score;
}
