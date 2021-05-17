package com.example.baidumapdemo.zouao.Bean;

import java.util.List;

/**
 * Copyright 2021 bejson.com
 *
import java.util.List;

/**
 * Auto-generated: 2021-05-16 16:1:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MuseumRootBean {

    private int pageNo;
    private int pageTotal;
    private int pageSize;
    private String pageTotalCount;
    private List<Museum> items;
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageNo() {
        return pageNo;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageTotalCount(String pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }
    public String getPageTotalCount() {
        return pageTotalCount;
    }

    public void setItems(List<Museum> items) {
        this.items = items;
    }
    public List<Museum> getItems() {
        return items;
    }

}