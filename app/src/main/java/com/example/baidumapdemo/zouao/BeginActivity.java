package com.example.baidumapdemo.zouao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.gotoEveryone_page.goto_page;
import com.example.baidumapdemo.guoziyu.Main4Activity;
import com.example.baidumapdemo.wangjiaxin.Main5Activity;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.zouao.Bean.MuseumRootBean;
import com.example.baidumapdemo.zouao.Bean.Museum;
import com.example.baidumapdemo.zouao.Util.HttpGet;
import com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 博物馆首页 邹傲
 */
public class BeginActivity extends AppCompatActivity {
    ListView mListView ;
    //博物馆列表项
    private List<Museum> museumList = new ArrayList<>();
    private List<Museum> tmp=new ArrayList<>();
    private MuseumListAdapter museumListAdapter;
    //列表项页数
    private int page=1;     //从第一页开始查
    private String cityname="北京市";       //默认当前城市为北京
    TextView city;  //当前城市view
    //底部导航
    private TextView mTextView;
    private BottomNavigationView mNavigationView;
    Handler handler1;   //处理请求列表
    Handler handler2;   //处理下拉

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        //首页显示城市
        city=findViewById(R.id.cityname);
        city.setText(cityname); //默认为北京市
        //底部导航监听器
        mTextView = (TextView) findViewById(R.id.text);
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String s=item.getTitle().toString();
                if("地图".equals(s)){
                    //跳转到地图页
                    Intent intent=new Intent(BeginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else if("新闻".equals(s)){
                    //跳转到新闻页

                }else if ("搜索".equals(s)){
                    //跳转到搜索页
                }
                else if("我的".equals(s)){
                    //跳转到登录页
                    Intent intent=new Intent(BeginActivity.this, Main4Activity.class);
                    startActivity(intent);
                }
                return  true;
            }
        });
        //跳转到切换城市页
        Button pickcity=findViewById(R.id.pickcity);
        pickcity.setText("切换城市");
        pickcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BeginActivity.this,CityPickerActivity.class);
                //启动
                startActivity(intent);
            }
        });
        //跳转到切换城市排名页
        new Thread(new Runnable() {
            @Override
            public void run() {
                //跳转到博物馆排名页
                Button museumrank=findViewById(R.id.museumrank);
                museumrank.setText("博物馆排名");
                museumrank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(BeginActivity.this,rankActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("cityname",cityname);
                        intent.putExtra("Message",bundle);
                        startActivity(intent);
                    }
                });
            }
        }).start();
        //获取切换城市回传回的字符串
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取数据
                Intent intent = getIntent();
                //从intent取出bundle
                Bundle bundle = intent.getBundleExtra("Message");
                if(bundle!=null){
                    //获取数据
                    cityname = bundle.getString("cityname");
                    TextView t=findViewById(R.id.cityname);
                    //显示数据
                    t.setText("当前城市:"+cityname);
                    Message message=new Message();
                    message.what=1;
                    handler1.sendMessage(message);
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                tmp = HttpGet.getText(cityname, String.valueOf(page));
                Message message=new Message();
                message.what=1;
                handler1.sendMessage(message);
            }
        }).start();
        mListView = findViewById(R.id.museum_list);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean isBottom=false; //判断是否为底部
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if(isBottom && i==AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            tmp = HttpGet.getText(cityname, String.valueOf(page));
                            Message message=new Message();
                            message.what=1;
                            handler1.sendMessage(message);
                        }
                    }).start();
                }
                museumListAdapter.notifyDataSetChanged();
                isBottom = false;
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(i+i1 == i2){
                    isBottom = true;
                }else{
                    isBottom = false;
                }
            }

        });
        handler1=new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                if (what == 1) {                //进行列表加载
                    if(tmp!=null&&!tmp.isEmpty()){
                        for (Museum museum : tmp) {
                            museumList.add(museum);
                        }
                        museumListAdapter = new MuseumListAdapter(getApplicationContext(), museumList);
                        mListView.setAdapter(museumListAdapter);
                        mListView.setSelection((page-1)*7);
                        page++; //增加一页
                    }
                }
            }
        };
    }

    protected int mFinishCount = 0;

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mFinishCount = 0;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void finish() {
        mFinishCount++;
        if (mFinishCount == 1) {
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG).show();
        } else if (mFinishCount == 2) {
            //跳回测试页
            Intent intent=new Intent(BeginActivity.this, goto_page.class);
            startActivity(intent);
            super.finish();
        }
    }
}
