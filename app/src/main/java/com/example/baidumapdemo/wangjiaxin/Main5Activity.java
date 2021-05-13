package com.example.baidumapdemo.wangjiaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.axingbuxiang.comment_museum;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button button2=(Button)findViewById(R.id.button2);		//获取“进入地图页”按钮
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转地图页
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        Button buttoneva=(Button)findViewById(R.id.eva);		//获取“前往评价”按钮
        buttoneva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转评价页
                Intent intent2 = new Intent(getApplicationContext(), comment_museum.class);
                startActivity(intent2);
                finish();
            }
        });

        Button button1=(Button)findViewById(R.id.button);		//获取“上传视频”按钮
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转上传视频页
               // Intent intent2 = new Intent(getApplicationContext(), .class);//设置跳转页面
                //startActivity(intent2);
                finish();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
