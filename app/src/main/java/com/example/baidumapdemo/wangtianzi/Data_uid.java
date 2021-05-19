package com.example.baidumapdemo.wangtianzi;

import android.app.Application;

public class Data_uid {
    private static int uid;

    public static int getid(){
        return uid;
    }
    public static  void setid(int c){
        Data_uid.uid=c;
    }


}
