package com.example.baidumapdemo.guoziyu;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPut_update {
    public static boolean  sendText(int uid,String name,String mobile,String email,String gender)
    {
        // int k;
        //String s=new String();

        //OkHttpClient formBody=new FormBody.Builer()
//        RequestBody body = new FormBody.Builder()
//                .add("name",name)
//                .add("mobile",mobile)
//                .add("email",email)
//                .add("password",password)
//                .build();

        //设置媒体类型。application/json表示传递的是一个json格式的对象
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");

        //使用JSONObject封装参数
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name);
            jsonObject.put("mobile",mobile);
            jsonObject.put("email",email);
            jsonObject.put("gender",gender);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url="http://8.140.3.158:81/user/updateUser/"+uid;
        OkHttpClient okHttpClient=new OkHttpClient();

        //设置okhttp超时
        okHttpClient.newBuilder().connectTimeout(10000L, TimeUnit.MILLISECONDS).readTimeout(50000,TimeUnit.MILLISECONDS).build();

        //创建RequestBody对象，将参数按照指定的MediaType封装
        RequestBody body = RequestBody.create(mediaType, jsonObject.toString());


        Request request=new Request
                .Builder()
                .url(url)
                .put(body)
                .build();
        //final Call call=okHttpClient.newCall(request);
        try
        {
            Response response = okHttpClient.newCall(request).execute();
            Log.d(String.valueOf(response), "sendText:接受返回 ");
            //s=response.body().string();
            //Log.d("TAG","run:"+s);
            if(response.isSuccessful())
            {
                //throw new IOException("unexpected code.."+response);
                //result=response.body().string();//如果成功 s=success
                return true;
            }
            else
                return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
