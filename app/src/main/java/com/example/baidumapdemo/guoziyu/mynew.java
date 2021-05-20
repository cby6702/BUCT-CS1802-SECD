package com.example.baidumapdemo.guoziyu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.baidumapdemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class mynew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynew);
        Intent intent = getIntent();//跳转页面
        String[] strings = intent.getStringArrayExtra("strings");//传递后台返回信息
        //传递信息
        List<String> datas = new ArrayList<>();

        for (int i=0;strings[i]!=null;i++) {
            datas.add(Arrays.toString(new String[]{strings[i]}));
        }
        System.out.println(Arrays.toString(strings));

        ListView listView = findViewById( R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mynew.this, R.layout.news_ltem,R.id.news_in,datas
        );

        listView.setAdapter(adapter);

    }

}