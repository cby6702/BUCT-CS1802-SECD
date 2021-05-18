package com.example.baidumapdemo.wangjiaxin.DeitalActivity;

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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.HTTP.http_getmuseummid;
import com.example.baidumapdemo.wangjiaxin.museumexplain;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.wangtianzi.comment_museum;

public class Detail_activity extends AppCompatActivity {
    private String museumname;
    private String address;
    private int mid;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //注册组件
        TextView museum_name = findViewById(R.id.textView2);
        TextView address_view = findViewById(R.id.textView3);
        //接收上一个bundle传递的信息
        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        museumname = bundle.getString("name");
        address = bundle.getString("address");
        address_view.setText(address);
//        Bundle bundlezo = intent.getBundleExtra("Message");
//        String namezo = bundle.getString("MuseumName");

        museum_name.setText(museumname);


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
                }
            }
        };
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
                bundle.putInt("nummid",mid);
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
                Intent intentcol=new Intent(getApplicationContext(),museumexplain.class);
                startActivity(intentcol);
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
