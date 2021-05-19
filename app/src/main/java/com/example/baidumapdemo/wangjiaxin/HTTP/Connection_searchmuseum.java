package com.example.baidumapdemo.wangjiaxin.HTTP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.baidumapdemo.wangjiaxin.collectionexplain;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.HTTP.http_getmuseummid;
import com.example.baidumapdemo.wangjiaxin.museumexplain;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.wangtianzi.HttpGet_Zcomments;
import com.example.baidumapdemo.wangtianzi.Usercomment;
import com.example.baidumapdemo.wangtianzi.comment_museum;

import java.util.List;

public class Connection_searchmuseum extends AppCompatActivity {

    private String museumname;
    private int mid;
    private String searchitem;
    private String serachname;
    private RatingBar ratingBar;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Intent intentwtz = getIntent();
        Bundle bundlewtz = intentwtz.getBundleExtra("Message_museum");
        if(bundlewtz!=null){
            searchitem=bundlewtz.getString("serachitem");
            serachname = bundlewtz.getString("serachname");
        }

        if(searchitem.equals("博物馆"))//这种情况不用找
        {
            museumname=serachname;
            TextView textView = findViewById(R.id.textView2);
            textView.setText(museumname);
        }
        if(searchitem.equals("展览"))
        {

        }
        if(searchitem.equals("藏品"))
        {

        }
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                mid = http_getmuseummid.getText(museumname);
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }).start();


        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {                //进行列表加载
                    Log.i("handler已接受到消息", "" + what);

                    Log.e("test123",""+mid);
                    Log.e("test456",museumname);
                }
            }
        };
        //这里是wtz加的，可以动态显示博物馆的星级
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);	//获取星级评分条
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
                List<Usercomment> commentslist = HttpGet_Zcomments.getText(mid);//获取数据
                System.out.println(commentslist);

                int i= (int)commentslist.get(0).getGeneral_comment();//获取博物馆总评显示出来（用星标）
                System.out.println(i);
                ratingBar.setRating(i);
            }
        });
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
                Bundle bundle = new Bundle();
                bundle.putString("name",museumname);
                bundle.putInt("nummid",mid);
                Log.v("MIT",String.valueOf(mid));
                intent2.putExtras(bundle);
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
                //finish();
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.museum_information:
                Intent intentmuseum=new Intent(getApplicationContext(), museumexplain.class);
                Bundle bundle = new Bundle();
                bundle.putString("museumname",museumname);
                intentmuseum.putExtras(bundle);
                startActivity(intentmuseum);
                break;
            case R.id.collection_information:
                Intent intentcol=new Intent(getApplicationContext(),collectionexplain.class);
                Bundle bundlecol = new Bundle();
                bundlecol.putString("colmid",String.valueOf(mid));
                intentcol.putExtras(bundlecol);
                startActivity(intentcol);
                Toast.makeText(getApplicationContext(),mid+"",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exhibition_information:
                Intent intentex=new Intent(getApplicationContext(),museumexplain.class);
                startActivity(intentex);
                break;
            default:
        }
        return true;
    }
}
