package com.example.baidumapdemo.wangtianzi;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.os.Bundle;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.axingbuxiang.Main3Activity;

public class Main2Activity extends AppCompatActivity {

    private RatingBar ratingbar;	//星级评分条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button buttonre=(Button)findViewById(R.id.return1);		//获取“返回”按钮
        buttonre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转回上一个页面 即 博物馆评价表总页面
                Intent intent2 = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent2);
                finish();
            }
        });

        ratingbar = (RatingBar) findViewById(R.id.ratingBar1);	//获取星级评分条
        Button button=(Button)findViewById(R.id.btn);		//获取“提交”按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = ratingbar.getProgress();            //获取进度
                float rating = ratingbar.getRating();            //获取等级
                float step = ratingbar.getStepSize();            //获取每次最少要改变多少个星级
                Log.i("星级评分条","step="+step+" result="+result+" rating="+rating);
                Toast.makeText(Main2Activity.this, "你评价了" + rating + "颗星", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

