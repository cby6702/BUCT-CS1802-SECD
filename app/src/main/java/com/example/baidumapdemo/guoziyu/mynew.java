package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class mynew extends AppCompatActivity {
    //获取数据
    List<class_news> news = new ArrayList<>();//设置list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynew);


        Intent intent = getIntent();//跳转页面
        final int uid=intent.getIntExtra("uid",0);

        new Thread(new Runnable() {
            @Override
            public void run() {//后端进行交互
                try {
                    OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                    System.out.println("ok??");
                    Request request = new Request.Builder()
                            .url("http://8.140.3.158:81/user/MyTexts/"+uid) //后端请求接口的路径http://8.140.3.158:81/user/select/1
                            .get().build(); //创造http请求
                    Response response = client.newCall(request).execute(); //执行发送指令
                    String s = response.body().string();//接收回返数据
                    JSONArray jsonArray = new JSONArray(s); //将文本格式的JSON转换为JSON数组
                    jiexi_news(jsonArray);//josn解析
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mynew.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();

        //适配器对应
         News_Adapter adapter=new News_Adapter(mynew.this,R.layout.news_ltem,news);
            ListView listview = (ListView) findViewById(R.id.list_view);
            listview.setAdapter(adapter);
    }
    private void jiexi_news(JSONArray jsonArray) throws JSONException {//解析显示数据：先接收数据，然后传递给消息界面
        for(int i=0;i<jsonArray.length();i++){ //遍历这个数组
            JSONObject jsonObject = jsonArray.getJSONObject(i); //取出JSON元素
            System.out.println(jsonObject);
            String comtext= jsonObject.getString("comtext");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）------修改5
            int tid = jsonObject.getInt("tid");//消息编号
            Log.d(String.valueOf(i), "jiexi_news: 次数");
            class_news news_temp=new class_news(tid,comtext);
            news.add(news_temp);

        }//用string来记录数组
    }

}