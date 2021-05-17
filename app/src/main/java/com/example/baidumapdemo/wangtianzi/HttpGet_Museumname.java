package com.example.baidumapdemo.wangtianzi;
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


public class HttpGet_Museumname {

    //static List<Museumname> MuseumnameList;
    //Museumname res;
    public static String getText(int mid) {
        Museumname res;
        try {
            // URL url = new URL("http://openapi.tuling123.com/openapi/api/v2" );
            //URL url = new URL(https://api.ownthink.com/bot")

            String u = "http://8.140.3.158:81/museum/searchId/" + mid;
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);
            conn.connect();


            Gson gson = new Gson();
            //读数据
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            is.close();
            System.out.println("看返回的数据是否写全:" + buffer.toString());//看返回的数据是否写全
            //Type listType = new TypeToken<List<Museumname>>() {}.getType();//java通过反射获取对象类型
            //MuseumnameList = gson.fromJson(buffer.toString(), listType);
            res = gson.fromJson(buffer.toString(), Museumname.class);
            return res.getName();
        } catch (Exception e) {
            //Log.d("TAG",e.toString());
            e.printStackTrace();
            return "error";
        }
        // return res.getName(); ;
        //返回名字 同理可以返回对象后使用getName..等方法获得别的信息 然后放到布局文件就可以了
    }


}

