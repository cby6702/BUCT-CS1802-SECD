package com.example.baidumapdemo.gotoEveryone_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.axingbuxiang.Main3Activity;
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
        init_za();
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
}

