package com.example.baidumapdemo.wangjiaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.HTTP.http_getmuseumbyname;
import com.example.baidumapdemo.zouao.Util.HttpGet;
import com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter;

public class museumexplain extends AppCompatActivity {
    private String museumname;
    private String opentime;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museumexplain);
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.startend);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        museumname = bundle.getString("museumname");
        //Log.e("test",museumname);
        new Thread(new Runnable() {
            @Override
            public void run() {
                opentime = http_getmuseumbyname.getText(museumname);
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

                    TextView open_time = findViewById(R.id.timedetails);
                    open_time.setText(opentime);

                }
            }
        };

        Button btn_start=(Button)findViewById(R.id.start);
        Button btn_pause=(Button)findViewById(R.id.pause);
        Button btn_stop=(Button)findViewById(R.id.stop);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });
    }
}
