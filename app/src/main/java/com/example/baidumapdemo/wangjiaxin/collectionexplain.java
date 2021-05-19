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
import android.widget.ListView;
import android.widget.TextView;

import com.example.baidumapdemo.wangjiaxin.Bean.collectioninfo;
import com.example.baidumapdemo.wangjiaxin.HTTP.http_getcollectionint;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.HTTP.http_getmuseumbyname;
import com.example.baidumapdemo.wangjiaxin.adpter.CollectionAdpter;
import com.example.baidumapdemo.zouao.Util.HttpGet;
import com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter;

import java.util.List;

public class collectionexplain extends AppCompatActivity {
    private String museumname;
    private String colintroduction;
    private String mid;
    CollectionAdpter collectionAdpter;
    List<collectioninfo> collections;
    //组件
    ListView collectionView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectionexplain);
        Log.v("collection","你好");
        Intent intent=getIntent();
        Bundle bundleExtra = intent.getExtras();
        mid = bundleExtra.getString("colmid");
       new Thread(new Runnable() {
           @Override
           public void run() {
               collections = http_getcollectionint.getmid(mid);
               Message message=new Message();
               message.what=1;
               handler.sendMessage(message);
           }
       }).start();
        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                if (what == 1) {                //进行列表加载

                        collectionAdpter = new CollectionAdpter(getApplicationContext(), collections);
                        collectionView=findViewById(R.id.collections);
                        collectionView.setAdapter(collectionAdpter);

                }
            }
        };
        System.out.println(mid);
    }
}
