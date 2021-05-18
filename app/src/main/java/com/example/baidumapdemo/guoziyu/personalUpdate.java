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
        init_gzy1();
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
        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Intent intent2 = new Intent(getApplicationContext(), Main4Activity.class);
                    startActivity(intent2);
                    finish();
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalUpdate.this, "已成功修改,请登录！", Toast.LENGTH_SHORT).show();
                }
                if (what == 2) {
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(personalUpdate.this, "未成功修改，请重新填写！", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public void init_gzy1(){
        Button gzy = findViewById(R.id.button6);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), personalct.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}
