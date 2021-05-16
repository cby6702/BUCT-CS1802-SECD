package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baidumapdemo.R;

public class mynew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynew);
        Intent intent = getIntent();//跳转页面
        final String[] strings = intent.getStringArrayExtra("strings");//传递后台返回信息
        runOnUiThread(new Runnable() {//显示后台返回个人信息
            @Override
            public void run() {
                LinearLayout ll_show = (LinearLayout) findViewById(R.id.ll_news);//——————修改1
                if(strings != null ) {
                    for (String s :
                            strings) {
                        TextView textView = new TextView(getApplicationContext());
                        textView.setTextColor(Color.BLACK);
                        textView.setText(s);
                        textView.setTextSize(30);
                        textView.setBackgroundResource(R.drawable.frame);
                        ll_show.addView(textView);
                    }
                }
            }
        });
        init_gzy1();
    }
    public void init_gzy1(){
        Button gzy = findViewById(R.id.button11);
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