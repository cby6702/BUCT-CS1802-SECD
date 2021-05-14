package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class personalct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalct);
        Intent intent = getIntent();//跳转页面
        final String[] strings = intent.getStringArrayExtra("strings");//传递后台返回信息
        runOnUiThread(new Runnable() {//显示后台返回个人信息
            @Override
            public void run() {
                LinearLayout ll_show = (LinearLayout) findViewById(R.id.ll_show);
                if(strings != null ) {
                    for (String s :
                            strings) {
                        TextView textView = new TextView(getApplicationContext());
                        textView.setTextColor(Color.BLACK);
                        textView.setText(s);
                        textView.setTextSize(30);
                        ll_show.addView(textView);
                    }
                }
            }
        });
        init_gzy1();
        init_gzy2();
        init_gzy3();
    }
    public void init_gzy1(){
        Button gzy = findViewById(R.id.button2);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), personalifo.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_gzy2(){
        final Button sendPost = findViewById(R.id.commentary);//设定关联构件为以login为id的
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //类型1——Body型

                new Thread(new Runnable() {
                    @Override
                    public void run() {//后端进行交互
                        try {
                            int u_id = 1, target_id = 1;
                            OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                            System.out.println("ok??");
                            Request request = new Request.Builder()
                                    .url("http://8.140.3.158:81/user/select/1") //后端请求接口的路径http://8.140.3.158:81/user/select/1
                                    .get().build(); //创造http请求
                            Response response = client.newCall(request).execute(); //执行发送指令
                            String s = response.body().string();//接收回返数据
                            jiexi_commentary(s);//josn解析
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(personalct.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    public void init_gzy3(){
        final Button sendPost = findViewById(R.id.news);//设定关联构件为以login为id的——————修改1
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //类型1——Body型

                new Thread(new Runnable() {
                    @Override
                    public void run() {//后端进行交互
                        try {
                            int u_id = 1, target_id = 1;
                            OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                            System.out.println("ok??");
                            Request request = new Request.Builder()
                                    .url("http://8.140.3.158:81/user/select/1") //后端请求接口的路径http://8.140.3.158:81/user/select/1------修改2
                                    .get().build(); //创造http请求
                            Response response = client.newCall(request).execute(); //执行发送指令
                            String s = response.body().string();//接收回返数据
                            jiexi_news(s);//josn解析
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(personalct.this, "网络请求失败！", Toast.LENGTH_SHORT).show();//-----修改3
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    private void jiexi_commentary(String s) throws JSONException {//解析显示数据：先接收数据，然后传递给评论界面——修改4
        JSONObject object = new JSONObject(s);
        System.out.println(s);
        String name = object.getString("name");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）------修改5
        String gender = object.getString("gender");
        String logincount = object.getString("logincount");
        String city = object.getString("city");
        final String[] strings = {"name " + name, "gender " + gender, "logincount  " + logincount, "city     " + city};//用string来记录数组
        Intent intent = new Intent();//转移界面
        intent.setClass(personalct.this, mycomment.class);//--------修改6
        intent.putExtra("strings",strings);//传递string数组
        startActivity(intent);
    }
    private void jiexi_news(String s) throws JSONException {//解析显示数据：先接收数据，然后传递给消息界面
        JSONObject object = new JSONObject(s);
        System.out.println(s);
        String name = object.getString("name");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）
        String gender = object.getString("gender");
        String logincount = object.getString("logincount");
        String city = object.getString("city");
        final String[] strings = {"name " + name, "gender " + gender, "logincount  " + logincount, "city     " + city};//用string来记录数组
        Intent intent = new Intent();//转移界面
        intent.setClass(personalct.this, mynew.class);
        intent.putExtra("strings",strings);//传递string数组
        startActivity(intent);
    }
}