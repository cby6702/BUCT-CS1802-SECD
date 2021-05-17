package com.example.baidumapdemo.axingbuxiang;
//用来获取mid name
public class Museums {
    private int mid;

    private String name;

    private String introduction;

    private String opentime;

    private String collection;

    private String picture;

    public void setMid(int mid){
        this.mid = mid;
    }
    public int getMid(){
        return this.mid;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
    }
    public void setOpentime(String opentime){
        this.opentime = opentime;
    }
    public String getOpentime(){
        return this.opentime;
    }
    public void setCollection(String collection){
        this.collection = collection;
    }
    public String getCollection(){
        return this.collection;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
    public String getPicture(){
        return this.picture;
    }

}