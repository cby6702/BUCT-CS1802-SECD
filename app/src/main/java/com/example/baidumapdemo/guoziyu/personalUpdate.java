package com.example.baidumapdemo.guoziyu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baidumapdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class personalUpdate extends AppCompatActivity {
    Handler handler;
    private String name;
    private String mail;
    private String mobile;
    private String gender;
    boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_update);
        Intent intent = getIntent();//跳转页面
        final  int uid=intent.getIntExtra("uid",0);
        init_sign(uid);
    }
    public void init_sign(final int uid) {
        Button button = (Button) findViewById(R.id.sign);        //获取“提交”按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input_name = (EditText) findViewById(R.id.name);//姓名
                name = input_name.getText().toString().trim();
                EditText input_mail = (EditText) findViewById(R.id.mail);//邮箱
                mail = input_mail.getText().toString().trim();
                EditText input_mobile = (EditText) findViewById(R.id.mobile);//邮箱
                mobile = input_mobile.getText().toString().trim();
                EditText input_gender = (EditText) findViewById(R.id.gender);//邮箱
                gender = input_gender.getText().toString().trim();
                //   Toast.makeText(personalifo.this, inn, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = HttpPut_update.sendText(uid,name, mobile, mail, gender);
                        Log.d(name, "run: 姓名：");
                        Log.d(mobile, "run:电话 ");
                        Log.d(mail, "run:邮箱 ");
                        Log.d(gender, "run:性别");
                        if (result == true)
                        {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);

                        }
                        else if(result==false)
                        {
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                        }
                    }
                }).start();

            }
        });

        Button button1 = (Button) findViewById(R.id.return1);        //获取“返回”按钮
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               next_personal(uid);

            }
        });

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    next_personal(uid);
                    finish();

                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalUpdate.this, "已成功修改！", Toast.LENGTH_SHORT).show();
                }
                if (what == 2) {
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalUpdate.this, "未成功修改，请重新填写！", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    private void jiexi(String s) throws JSONException {//解析显示数据：先接收数据，然后传递给个人中心界面
        JSONObject object = new JSONObject(s);
        System.out.println(s);
        String name = object.getString("name");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）
        String mobile = object.getString("mobile");
        String email = object.getString("email");
        int uid=object.getInt("uid");
        final String[] strings = { "账号 "+ uid,"姓名 "+name, "手机号 " + mobile, "邮箱  " + email};//用string来记录数组
        Intent intent = new Intent();//转移界面
        intent.setClass(personalUpdate.this, personalct.class);
        intent.putExtra("strings",strings);//传递string数组
        intent.putExtra("uid",uid);
        startActivity(intent);
    }
    private void next_personal(final int s)  {//解析显示数据：先接收数据，然后传递给个人中心界面
        new Thread(new Runnable() {
            @Override
            public void run() {//后端进行交互
                try {
                    OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                    System.out.println("ok??");
                    Request request = new Request.Builder()
                            .url("http://8.140.3.158:81/user/select/"+s) //后端请求接口的路径http://8.140.3.158:81/user/select/1
                            .get().build(); //创造http请求
                    Response response = client.newCall(request).execute(); //执行发送指令
                    String s = response.body().string();//接收回返数据
                    Log.d(s, "run: 显示全部数据");
                    jiexi(s);//josn解析
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(personalUpdate.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}
