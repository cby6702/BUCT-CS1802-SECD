package com.example.baidumapdemo.zouao.Bean;
import java.util.List;

public class m_root {

    private int pageNo;
    private int pageTotal;
    private int pageSize;
    private int pageTotalCount;
    private List<com.example.baidumapdemo.zouao.Bean.m_info> items;
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

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }
    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setItems(List<com.example.baidumapdemo.zouao.Bean.m_info> items) {
        this.items = items;
    }
    public List<m_info> getItems() {
        return items;
    }

}