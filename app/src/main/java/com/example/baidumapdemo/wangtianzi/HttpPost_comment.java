package com.example.baidumapdemo.wangtianzi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPost_comment {

    //static String result;//用来输出判断的
    public static boolean  sendText(int uid,int mid,int exhibitionstar,int servicestar,int environmentstar,double general_comment,String comment,String picture)
    {
        //设置媒体类型。application/json表示传递的是一个json格式的对象
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

        //使用JSONObject封装参数
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid",uid);
            jsonObject.put("mid",mid);
            jsonObject.put("exhibitionstar",exhibitionstar);
            jsonObject.put("servicestar",servicestar);
            jsonObject.put("environmentstar",environmentstar);
            jsonObject.put("general_comment",general_comment);
            jsonObject.put("comment",comment);
            jsonObject.put("picture",picture);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url="http://8.140.3.158:81/comments/upload";
        OkHttpClient okHttpClient=new OkHttpClient();//创建okHttpClient对象

        //设置okhttp超时
        okHttpClient.newBuilder().connectTimeout(10000L, TimeUnit.MILLISECONDS).readTimeout(50000,TimeUnit.MILLISECONDS).build();

        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody body = RequestBody.create(mediaType, jsonObject.toString());

        //创建一个Request
        Request request=new Request
                .Builder()
                .url(url)
                .post(body)
                .build();
        try
        {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful())
            {
                //throw new IOException("unexpected code.."+response);
                //result=response.body().string();//如果成功 s=success
                return true;
           }
            else
                return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
