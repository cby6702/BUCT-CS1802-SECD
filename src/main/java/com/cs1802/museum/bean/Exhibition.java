package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.sql.Date;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exhibition {
    private int eid;
    private String ename;
    private String eintroduce;
    private int mid;
    private Date startdate;
    private Date enddate;
}
