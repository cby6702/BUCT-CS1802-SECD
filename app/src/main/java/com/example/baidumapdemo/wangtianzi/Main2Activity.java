package com.example.baidumapdemo.wangtianzi;
//这个是提交评价的java，尚未进行前后端的交互
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.baidumapdemo.R;

public class Main2Activity extends AppCompatActivity {

    private RatingBar ratingbar1;	//星级评分条 展览
    private RatingBar ratingbar2;	//星级评分条 服务
    private RatingBar ratingbar3;	//星级评分条 环境
    boolean result;
    private int rating1,rating2,rating3;
    private double rating;
    Handler handler;

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

        //以下是评星条的内容
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
//                List<Usercomment> commentslist = HttpGet_Zcomments.getText(423);//获取数据
//                int i= (int)commentslist.get(0).getGeneral_comment();
//                System.out.println(i);
//                ratingbar.setRating(i);
            }
        });
        ratingbar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
            }
        });
        ratingbar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
            }
        });

        Button button=(Button)findViewById(R.id.btn);		//获取“提交”按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating1 = (int)ratingbar1.getRating();//展览
                rating2 = (int)ratingbar2.getRating();//服务
                rating3 = (int)ratingbar3.getRating();//环境

                rating = (rating1+rating2+rating3)/(3.0);

                EditText input=(EditText) findViewById(R.id.pinglun);//搜索框输入的数据
                final String inn=input.getText().toString().trim();
                Toast.makeText(Main2Activity.this, inn, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        result=HttpPost_comment.sendText(1,1,rating1,rating2,rating3,rating,inn,null);
                        //boolean result=HttpPost_comment.sendText()
                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        //handle控制线程
        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Log.i("handler已接受到消息", "" + what);
                    if(result==true)
                        Toast.makeText(Main2Activity.this,"已成功评价",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Main2Activity.this,"评价失败",Toast.LENGTH_SHORT).show();
                }
            }
        };

        //需要的前后端交接：根据uid、mid判断用户是否可评价

    }
}

