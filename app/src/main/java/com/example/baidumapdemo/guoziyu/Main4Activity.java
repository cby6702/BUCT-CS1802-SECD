package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.baidumapdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Main4Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main4);
            init_gzy();
        }
    public void init_gzy(){
        final Button sendPost = findViewById(R.id.login);//设定关联构件为以login为id的
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
                            jiexi(s);//josn解析
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Main4Activity.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    private void jiexi(String s) throws JSONException {//解析显示数据：先接收数据，然后传递给个人中心界面
        JSONObject object = new JSONObject(s);
        System.out.println(s);
        String name = object.getString("name");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）
        String gender = object.getString("gender");
        String logincount = object.getString("logincount");
        String city = object.getString("city");
        final String[] strings = {"name " + name, "gender " + gender, "logincount  " + logincount, "city     " + city};//用string来记录数组
        Intent intent = new Intent();//转移界面
        intent.setClass(Main4Activity.this, personalct.class);
        intent.putExtra("strings",strings);//传递string数组
        startActivity(intent);
    }
}



