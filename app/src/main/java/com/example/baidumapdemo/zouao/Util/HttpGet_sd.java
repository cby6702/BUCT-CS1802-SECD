package com.example.baidumapdemo.zouao.Util;

import android.util.Log;

import com.example.baidumapdemo.zouao.Bean.m_info;
import com.example.baidumapdemo.zouao.Bean.m_root;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class HttpGet_sd {
    public static  List<m_info> getText(String city, int url_rank, int url_ways, int url_page) {
        m_root res=null;
        try {
            String u = "http://8.140.3.158:81/museum/sort/"+ URLEncoder.encode(city,"utf-8")+"/"+URLEncoder.encode(String.valueOf(url_rank),"utf-8")+"/"+URLEncoder.encode(String.valueOf(url_ways),"utf-8")+"/"+URLEncoder.encode(String.valueOf(url_page),"utf-8");
            //System.out.println(u);
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);
            conn.connect();

            System.out.println(conn);
            Gson gson = new Gson();
            //读数据
            InputStream is = conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br= new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while((line=br.readLine())!=null){
                buffer.append(line);

            }
            is.close();
            //System.out.println(buffer.toString() );//看返回的数据是否写全
            res = gson.fromJson(buffer.toString(), m_root.class);

            return res.getItems();//返回名字 同理可以返回对象后使用getName..等方法获得别的信息 然后放到布局文件就可以了

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("error","error");
            return  null;
        }

    }

}