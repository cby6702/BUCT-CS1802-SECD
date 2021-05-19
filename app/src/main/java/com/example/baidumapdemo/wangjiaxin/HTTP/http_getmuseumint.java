package com.example.baidumapdemo.wangjiaxin.HTTP;

import com.example.baidumapdemo.wangjiaxin.Bean.museumInfo;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class http_getmuseumint {
    public static  String getText(String keyword) {
        try {
            // URL url = new URL("http://openapi.tuling123.com/openapi/api/v2" );
            //URL url = new URL(https://api.ownthink.com/bot")
            String u = "http://8.140.3.158:81/museum/searchName/"+ URLEncoder.encode(keyword,"utf-8");//keyword是查询关键字
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // conn.setDoOutput(true);
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
            museumInfo museumInfo = gson.fromJson(buffer.toString(), museumInfo.class);

            return museumInfo.getIntroduction();//返回名字 同理可以返回对象后使用getName..等方法获得别的信息 然后放到布局文件就可以了

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }


    }


}
