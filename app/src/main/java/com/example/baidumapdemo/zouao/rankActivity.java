package com.example.baidumapdemo.zouao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;

import java.util.ArrayList;
import java.util.List;

public class rankActivity extends AppCompatActivity {
    private String cityname="";
    ListView mListView;
    //博物馆列表项
    private List<com.example.baidumapdemo.zouao.Bean.m_info> museumList = new ArrayList<>();
    private List<com.example.baidumapdemo.zouao.Bean.m_info> tmp = new ArrayList<>();
    private com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter_sd museumListAdapterSd;
    //列表项页数
    private int page = 1;     //从第一页开始查
    //底部导航
    private TextView mTextView;
    Handler handler1;   //处理请求列表
    Handler handler2;   //处理下拉
    private List<com.example.baidumapdemo.zouao.Fruit> fruitList = new ArrayList<>();
    private int rank_p = 0;
    private int ways_p = 0;

    @SuppressLint("HandlerLeak")
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
        }// fruitList用于存储数据
        // 先拿到数据并放在适配器上
        //initFruits(); //初始化水果数据
        com.example.baidumapdemo.zouao.FruitAdapter adapter = new com.example.baidumapdemo.zouao.FruitAdapter(getApplicationContext(), R.layout.fruit_item, fruitList);

        // 将适配器上的数据传递给listView
        ListView listView = findViewById(R.id.list_rank_view);
        listView.setAdapter(adapter);

        // 为ListView注册一个监听器，当用户点击了ListView中的任何一个子项时，就会回调onItemClick()方法
        // 在这个方法中可以通过position参数判断出用户点击的是那一个子项
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                com.example.baidumapdemo.zouao.Fruit fruit = fruitList.get(position);
                Toast.makeText(getApplicationContext(), "加载中……", Toast.LENGTH_SHORT).show();
            }
        });

        TextView city_tv = findViewById(R.id.city);
        city_tv.setText(cityname);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(1000);
//                tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
//                Message message = new Message();
//                message.what = 1;
//                handler1.sendMessage(message);
//            }
//        }).start();

        //通过监听器，获得两个下拉列表的参数值
        Spinner spinner_rank = findViewById(R.id.id_rank_choice);
        spinner_rank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rank_p = position;
                String res_rank = String.valueOf(position);
                Toast.makeText(rankActivity.this, "加载中……", Toast.LENGTH_SHORT).show();
                if (museumList.isEmpty()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(1000);
                            tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
                            Message message = new Message();
                            message.what = 1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                } else {
                    museumList.clear();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(1000);
                            tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
                            Message message = new Message();
                            message.what = 1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Spinner spinner_ways = findViewById(R.id.id_ways_choice);
        spinner_ways.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ways_p = position;
                String res_ways = String.valueOf(position);
                Toast.makeText(rankActivity.this, "加载中……", Toast.LENGTH_SHORT).show();
                museumList.clear();

                if (museumList.isEmpty()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(1000);
                            tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
                            Message message = new Message();
                            message.what = 1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                } else {
                    museumList.clear();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(1000);
                            tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
                            Message message = new Message();
                            message.what = 1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mListView = findViewById(R.id.list_rank_view);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean isBottom = false; //判断是否为底部

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (isBottom && i == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            tmp = com.example.baidumapdemo.zouao.Util.HttpGet_sd.getText(cityname, rank_p, ways_p, page);
                            for (com.example.baidumapdemo.zouao.Bean.m_info m_info : tmp) {
                                System.out.println(m_info.getName());
                            }
                            Message message = new Message();
                            message.what = 1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                }
                museumListAdapterSd.notifyDataSetChanged();
                isBottom = false;
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i + i1 == i2) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }

        });

        handler1 = new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                if (what == 1) {                //进行列表加载
                    if (tmp != null && !tmp.isEmpty()) {
                        for (com.example.baidumapdemo.zouao.Bean.m_info museum : tmp) {
                            museumList.add(museum);
                        }
                        museumListAdapterSd = new com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter_sd(getApplicationContext(), museumList);
                        mListView.setAdapter(museumListAdapterSd);
                        mListView.setSelection((page - 1) * 7);
                        page++; //增加一页

                    }
                }
            }
        };



    }
}
