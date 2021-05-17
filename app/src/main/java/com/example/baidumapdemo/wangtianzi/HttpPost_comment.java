package com.example.baidumapdemo.wangtianzi;

import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPost_comment {

    public static boolean  sendText(int uid,int mid,int exhibitionsta,int servicestar,int environmentstar,double general_comment,String comment,String picture)
    {
        int k;
        String s=new String();
        String url="http://8.140.3.158:81/comments/upload";
        OkHttpClient okHttpClient=new OkHttpClient();

        //OkHttpClient formBody=new FormBody.Builer()
        RequestBody body = new FormBody.Builder()
                .add("uid",String.valueOf(uid))
                .add("mid",String.valueOf(mid))
                .add("exhibitionsta",String.valueOf(mid))
                .add("servicestar",String.valueOf(servicestar))
                .add("environmentstar",String.valueOf(environmentstar))
                .add("general_comment",String.valueOf(general_comment))
                .add("comment",comment)
                .add("picture",picture)
                .build();

        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        final Call call=okHttpClient.newCall(request);
        try
        {
            Response response=call.execute();
            s=response.body().string();
            Log.d("TAG","run:"+s);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
