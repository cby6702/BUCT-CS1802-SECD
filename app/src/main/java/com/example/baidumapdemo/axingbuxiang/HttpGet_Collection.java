package com.example.baidumapdemo.axingbuxiang;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class HttpGet_Collection {
    static List<Collection> CollectionList;
    static String name;
    public  static List<Collection> getText(String cname) {

                try {
                    // URL url = new URL("http://openapi.tuling123.com/openapi/api/v2" );
                    //URL url = new URL(https://api.ownthink.com/bot")
                    String u = "http://8.140.3.158:81/museum/search/"+URLEncoder.encode("藏品")+"/"+URLEncoder.encode(cname);
                    URL url = new URL(u);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
//                    conn.setDoOutput(true);
                    conn.connect();


                    Gson gson = new Gson();
                    //读数据
//                    Log.d("ResponseCode", conn.getRequestMethod());
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is,"utf-8");
                    BufferedReader br= new BufferedReader(isr);
                    StringBuffer buffer = new StringBuffer();
                    String line = null;
                    while((line=br.readLine())!=null){
                        buffer.append(line);
                    }
                    is.close();
                    System.out.println("看返回的数据是否写全:"+buffer.toString() );//看返回的数据是否写全
                    Type listType = new TypeToken<List<Collection>>() {}.getType();//java通过反射获取对象类型
                    CollectionList = gson.fromJson(buffer.toString(), listType);
                } catch (Exception e) {
                    Log.d("TAG",e.toString());
                }


        return CollectionList ;//返回名字 同理可以返回对象后使用getName..等方法获得别的信息 然后放到布局文件就可以了


    }
}
