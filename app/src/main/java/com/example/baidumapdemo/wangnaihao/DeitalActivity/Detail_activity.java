package com.example.baidumapdemo.wangnaihao.DeitalActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.baidumapdemo.R;

public class Detail_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);
        //注册组件
        TextView detail_info = findViewById(R.id.detailinfo);
        //接收上一个bundle传递的信息
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String name = bundle.getString("name");
        String distance = bundle.getString("distance");

        detail_info.setText(name+"的详细信息,距离是:"+distance);

    }
}
