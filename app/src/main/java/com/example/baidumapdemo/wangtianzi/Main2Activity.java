package com.example.baidumapdemo.wangtianzi;
//这个是提交评价的java，尚未进行前后端的交互
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.example.baidumapdemo.R;

public class Main2Activity extends AppCompatActivity {

    private RatingBar ratingbar1;	//星级评分条 展览
    private RatingBar ratingbar2;	//星级评分条 服务
    private RatingBar ratingbar3;	//星级评分条 环境
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button buttonre=(Button)findViewById(R.id.return1);		//获取“返回”按钮
        buttonre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转回上一个页面 即 博物馆总评价页面
                Intent intent2 = new Intent(getApplicationContext(), comment_museum.class);
                startActivity(intent2);
                finish();
            }
        });

        //获取星级评分条
        ratingbar1 = (RatingBar) findViewById(R.id.ratingBar1);//星级评分条 展览
        ratingbar2 = (RatingBar) findViewById(R.id.ratingBar2);//星级评分条 服务
        ratingbar3 = (RatingBar) findViewById(R.id.ratingBar3);//星级评分条 环境

        Button button=(Button)findViewById(R.id.btn);		//获取“提交”按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result1 = ratingbar1.getProgress();
                int result2 = ratingbar1.getProgress();
                int result3 = ratingbar1.getProgress();            //获取进度
                float rating1 = ratingbar1.getRating();
                float rating2 = ratingbar1.getRating();
                float rating3 = ratingbar1.getRating();            //获取等级
                float step1 = ratingbar1.getStepSize();
                float step2 = ratingbar1.getStepSize();
                float step3 = ratingbar1.getStepSize();            //获取每次最少要改变多少个星级
                float rating = (rating1+rating2+rating3)/3;
                //Log.i("星级评分条","step="+step1+" result="+result1+" rating="+rating);
                //Toast.makeText(Main2Activity.this, "你评价了" + rating + "颗星", Toast.LENGTH_SHORT).show();
            }
        });

        //需要的前后端交接：通过post传editText2文本框的内容和ratingBar1、ratingBar2、ratingBar3的内容给后端
        //上传成功则返回true；失败返回false

        //需要的前后端交接：根据uid、mid判断用户是否可评价

    }
}

