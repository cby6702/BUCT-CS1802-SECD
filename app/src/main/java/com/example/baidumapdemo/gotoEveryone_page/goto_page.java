package com.example.baidumapdemo.gotoEveryone_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.wangtianzi.Main2Activity;

public class goto_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto_page);
        //注册王乃豪测试页面的按钮并设置点击事件
        init_wnh();
        //注册王天滋测试页面的按钮并设置点击事件
        init_wtz();

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
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

