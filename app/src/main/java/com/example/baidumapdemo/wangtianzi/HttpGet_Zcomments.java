package com.example.baidumapdemo.wangtianzi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet_Zcomments {

    public static  float getText(int mid) {
        try {
            // URL url = new URL("http://openapi.tuling123.com/openapi/api/v2" );
            //URL url = new URL(https://api.ownthink.com/bot")
            String u = "http://8.140.3.158:81/comments/search/"+mid ;//keyword是查询关键字
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
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
            Usercomment res = gson.fromJson(buffer.toString(), Usercomment.class);

            return res.getGeneral_comment();

        } catch (Exception e) {
            e.printStackTrace();
            return 9;
        }

    }
}
