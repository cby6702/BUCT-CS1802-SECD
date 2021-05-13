package com.example.baidumapdemo.zouao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baidumapdemo.R;

public class rankActivity extends AppCompatActivity {
    private String cityname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        //获取数据
        Intent intent = getIntent();
        //从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
        if(bundle!=null){
            //获取数据
            cityname = bundle.getString("cityname");
            TextView t=findViewById(R.id.city);
            //显示数据
            t.setText(cityname);
        }
    }
}
