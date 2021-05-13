package com.cs1802.museum.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    public static final Integer PAGE_SIZE = 7;
    private Integer pageNo;//当前页码
    private Integer pageTotal;//总页数
    private Integer pageSize = PAGE_SIZE;//每页显示数量
    private Integer pageTotalCount;//总记录数
    private List<T> items;//当前页数据
}
