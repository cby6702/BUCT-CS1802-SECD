package com.example.baidumapdemo.zouao;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.gotoEveryone_page.goto_page;
import com.example.baidumapdemo.guoziyu.Main4Activity;
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
    MuseumRootBean jsonRootBean;  //json实体类
    //列表项页数
    private int page=1;
    private String cityname="北京市";       //默认当前城市为北京
    TextView city;  //当前城市view
    //底部导航
    private TextView mTextView;
    private BottomNavigationView mNavigationView;
    Handler handler;

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
                mTextView.setText(item.getTitle().toString().toUpperCase());
                String s=item.getTitle().toString();
                if("地图".equals(s)){
                    //跳转到地图页
                    Intent intent=new Intent(BeginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else if("新闻".equals(s)){
                    //跳转到新闻页

                }else if("我的".equals(s)){
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
                    handler.sendMessage(message);
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                museumList = HttpGet.getText(cityname, String.valueOf(page));
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }).start();
        handler=new Handler() {
            private MuseumListAdapter museumListAdapter;
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {                //进行列表加载
                    Log.i("handler已接受到消息", "" + what);
//                    for (int i = 0; i < museumList.size(); i++) {
//                        Log.v("博物馆" + i, museumList.get(i).getName());
//                    }
                    mListView = findViewById(R.id.museum_list);
                    museumListAdapter = new MuseumListAdapter(getApplicationContext(), museumList);
                    mListView.setAdapter(museumListAdapter);
                    //设置向下滚动事件
                    mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        private boolean isBottom=false; //判断是否为底部
                        @Override
                        public void onScrollStateChanged(AbsListView absListView, int i) {

                            if(isBottom &&page<=10&& i==AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                                page++; //增加一页
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        museumList = HttpGet.getText(cityname, String.valueOf(page));
                                        Message message=new Message();
                                        message.what=1;
                                        handler.sendMessage(message);
                                    }
                                    }).start();
                                }
                                //museumListAdapter.notifyDataSetChanged();
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

                }
            }
        };

    }
    protected int mFinishCount = 0;

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
    //获取图片资源
    public Bitmap getURLimage(String url) {

        Bitmap bmp = null;

        try {

            URL myurl = new URL(url);

            // 获得连接

            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();

            conn.setConnectTimeout(6000);//设置超时

            conn.setDoInput(true);

            conn.setUseCaches(false);//不缓存

            conn.connect();

            InputStream is = conn.getInputStream();//获得图片的数据流

            bmp = BitmapFactory.decodeStream(is);//读取图像数据

            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bmp;

    }


}

