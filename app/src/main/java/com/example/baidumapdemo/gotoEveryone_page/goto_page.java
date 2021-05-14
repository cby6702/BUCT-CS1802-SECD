package com.example.baidumapdemo.gotoEveryone_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.axingbuxiang.Main3Activity;
import com.example.baidumapdemo.axingbuxiang.comment_museum;
import com.example.baidumapdemo.guoziyu.Main4Activity;
import com.example.baidumapdemo.wangjiaxin.Main5Activity;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.wangtianzi.Main2Activity;
import com.example.baidumapdemo.zouao.Main6Activity;

public class goto_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_page);
        //注册王乃豪测试页面的按钮并设置点击事件
        init_wnh();
        //注册王天滋测试页面的按钮并设置点击事件
        init_wtz();
        //注册李坤测试页面的按钮并设置点击事件
        init_axing();
        //注册郭紫玉测试页面的按钮并设置点击事件
        init_gzy();
        //注册邹傲测试页面的按钮并设置点击事件
        init_za();
        //注册王嘉薪测试页面的按钮并设置点击事件
        init_wjx();

    }
    public void init_wnh(){
        Button wnh = findViewById(R.id.wnh);
        wnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init_wtz(){
        Button wtz = findViewById(R.id.wtz);
        wtz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_axing(){
        Button axing = findViewById(R.id.axing);
        axing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init_gzy(){
        Button gzy = findViewById(R.id.gzy);
        gzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_za(){
        Button za = findViewById(R.id.za);
        za.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main6Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
    public void init_wjx(){
        Button wjx = findViewById(R.id.wjx);
        wjx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), Main5Activity.class);
                startActivity(intent2);
                finish();
            }
        });
    }
}

