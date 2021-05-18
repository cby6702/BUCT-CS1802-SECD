package com.example.baidumapdemo.guoziyu;

public class class_news {
    private int tid;
    private String comtext;
    private int uid;
    private String ttyoe;
    class_news(int tid,String comtext)
    {
        this.tid=tid;
        this.comtext=comtext;
    }
    public void setTid(int tid) {
        this.tid = tid;
    }
    public int getTid() {
        return tid;
    }

    public void setComtext(String comtext) {
        this.comtext = comtext;
    }
    public String getComtext() {
        return comtext;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getUid() {
        return uid;
    }

    public void setTtyoe(String ttyoe) {
        this.ttyoe = ttyoe;
    }
    public String getTtyoe() {
        return ttyoe;
    }
}
