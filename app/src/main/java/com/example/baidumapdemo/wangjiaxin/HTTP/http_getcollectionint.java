package com.example.baidumapdemo.wangjiaxin.HTTP;

import android.util.Log;

import com.example.baidumapdemo.wangjiaxin.Bean.collectioninfo;
import com.example.baidumapdemo.wangjiaxin.Bean.museumInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class http_getcollectionint {
    public static List<collectioninfo> getmid(String keyword) {
        try {
            String u = "http://8.140.3.158:81/collection/search/"+keyword;//keyword是查询关键字
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);
            conn.connect();


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
            System.out.println(buffer.toString() );//看返回的数据是否写全
            Type listtype = new TypeToken<List<collectioninfo>>(){}.getType();
            List<collectioninfo> list = gson.fromJson(buffer.toString(), listtype);
            return list;//返回名字 同理可以返回对象后使用getName..等方法获得别的信息 然后放到布局文件就可以了

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
