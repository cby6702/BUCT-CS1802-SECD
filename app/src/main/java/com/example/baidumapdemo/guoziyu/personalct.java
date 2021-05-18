package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;

import org.json.JSONArray;
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
        final  int uid=intent.getIntExtra("uid",0);
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
                        textView.setTextSize(15);
                        textView.setBackgroundResource(R.drawable.frame1);
                        ll_show.addView(textView);
                    }
                }
            }
        });
        init_gzy1(uid);
        init_gzy2(uid);
        init_gzy3(uid);
        init_gzy4();
        init_gzy5();
    }
    public void init_gzy1(final int uid){//跳转到修改页面
        Button gzy = findViewById(R.id.button2);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), personalUpdate.class);
                intent2.putExtra("uid",uid);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_gzy4(){//退出账户。跳转到登录界面
        Button gzy = findViewById(R.id.button4);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_gzy5(){//切换账户。跳转到登录界面
        Button gzy = findViewById(R.id.button5);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_gzy2(final int uid){
        final Button sendPost = findViewById(R.id.commentary);//设定关联构件为以login为id的
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //类型1——Body型

                new Thread(new Runnable() {
                    @Override
                    public void run() {//后端进行交互
                        try {
                            OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                            System.out.println("ok??");
                            Request request = new Request.Builder()
                                    .url("http://8.140.3.158:81/user/MyComments/"+uid) //后端请求接口的路径http://8.140.3.158:81/user/select/1
                                    .get().build(); //创造http请求
                            Response response = client.newCall(request).execute(); //执行发送指令
                            String s = response.body().string();//接收回返数据
                            JSONArray jsonArray = new JSONArray(s); //将文本格式的JSON转换为JSON数组
                            jiexi_commentary(jsonArray);//josn解析
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
    public void init_gzy3(final int uid){
        final Button sendPost = findViewById(R.id.news);//设定关联构件为以login为id的——————修改1
        sendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //类型1——Body型

                new Thread(new Runnable() {
                    @Override
                    public void run() {//后端进行交互
                        try {
                            OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                            System.out.println("ok??");
                            Request request = new Request.Builder()
                                    .url("http://8.140.3.158:81/user/MyTexts/"+uid) //后端请求接口的路径http://8.140.3.158:81/user/select/1------修改2
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
                                    Toast.makeText(personalct.this, "网络请求失败！", Toast.LENGTH_SHORT).show();//-----修改3
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    private void jiexi_commentary(JSONArray jsonArray) throws JSONException {//解析显示数据：先接收数据，然后传递给评论界面——修改4
        final String[] strings = new String[100];//设定最多显示100个数组
        for(int i=0;i<jsonArray.length();i++){ //遍历这个数组
            JSONObject jsonObject = jsonArray.getJSONObject(i); //取出JSON元素
            System.out.println(jsonObject);
            String conment = jsonObject.getString("comment");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）------修改5
            int tid = jsonObject.getInt("aid");//消息编号
            int mid = jsonObject.getInt("mid");//博物馆编号
            int exhibitionstar = jsonObject.getInt("exhibitionstar");//展览分数
            int servicestar= jsonObject.getInt("servicestar");//服务分数
            int environmentstar= jsonObject.getInt("environmentstar");//环境分数
            double general_comment=jsonObject.getInt("general_comment");//总分
                strings[i]=i+"\t博物馆"+mid+"：展览分数:"+exhibitionstar+
                        "\n服务分数:"+servicestar +
                        "\n环境分数:"+environmentstar+
                        "\n总分分数:"+general_comment+
                        "\n评论:"+conment+"\n";
        }
        Intent intent = new Intent();//转移界面
        intent.setClass(personalct.this, mycomment.class);//--------修改6
        intent.putExtra("strings",strings);//传递string数组
        startActivity(intent);
    }
    private void jiexi_news(JSONArray jsonArray) throws JSONException {//解析显示数据：先接收数据，然后传递给消息界面
        final String[] strings = new String[100];//设定最多显示100个数组
        for(int i=0;i<jsonArray.length();i++){ //遍历这个数组
            JSONObject jsonObject = jsonArray.getJSONObject(i); //取出JSON元素
            System.out.println(jsonObject);
            String comtext= jsonObject.getString("comtext");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）------修改5
            int tid = jsonObject.getInt("tid");//消息编号
            Log.d(String.valueOf(i), "jiexi_news: 次数");
            strings[i]="\t消息\t"+(i+1)+":"+comtext;
        }//用string来记录数组

        Intent intent = new Intent();//转移界面
        intent.setClass(personalct.this, mynew.class);
        intent.putExtra("strings",strings);//传递string数组
        startActivity(intent);
    }
}