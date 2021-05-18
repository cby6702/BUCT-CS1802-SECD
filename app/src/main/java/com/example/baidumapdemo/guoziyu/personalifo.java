package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangtianzi.HttpPost_comment;
import com.example.baidumapdemo.wangtianzi.Main2Activity;

import java.io.InputStream;

import okhttp3.internal.http.StatusLine;

public class personalifo extends AppCompatActivity {
    Handler handler;
    private String name;
    private String mail;
    private String mobile;
    private String password;
    boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalifo);
        init_sign();
       // init_gzy1();
    }
    public void init_sign() {
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
                EditText input_password = (EditText) findViewById(R.id.password);//邮箱
                password = input_password.getText().toString().trim();
                //   Toast.makeText(personalifo.this, inn, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result = HttpPost_sign.sendText(name, mobile, mail, password);
                        Log.d(name, "run: 姓名：");
                        Log.d(mobile, "run:电话 ");
                        Log.d(mail, "run:邮箱 ");
                        Log.d(password, "run:password ");
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
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Intent intent2 = new Intent(getApplicationContext(), Main4Activity.class);
                    startActivity(intent2);
                    finish();
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalifo.this, "已成功注册,请登录！", Toast.LENGTH_SHORT).show();
                }
                if (what == 2) {
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalifo.this, "用户名已存在，请重新注册！", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
//    public void init_gzy1(){
//        Button gzy = findViewById(R.id.button6);
//        gzy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(getApplicationContext(), personalct.class);
//                startActivity(intent2);
//                finish();
//            }
//        });
//    }
}