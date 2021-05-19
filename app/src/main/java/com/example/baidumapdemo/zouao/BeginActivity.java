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
import com.example.baidumapdemo.axingbuxiang.Main3Activity;
import com.example.baidumapdemo.gotoEveryone_page.goto_page;
import com.example.baidumapdemo.guoziyu.Main4Activity;
import com.example.baidumapdemo.guoziyu.personalUpdate;
import com.example.baidumapdemo.guoziyu.personalct;
import com.example.baidumapdemo.wangjiaxin.Main5Activity;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;
import com.example.baidumapdemo.zouao.Bean.MuseumRootBean;
import com.example.baidumapdemo.zouao.Bean.Museum;
import com.example.baidumapdemo.zouao.Util.HttpGet;
import com.example.baidumapdemo.zouao.adaptor.MuseumList.MuseumListAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 博物馆首页 邹傲
 */
public class BeginActivity extends AppCompatActivity {
    ListView mListView ;
    int uid;//用户uid

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();//接收登录页面回传信息
        final  int uid=intent.getIntExtra("uid",0);

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
                    Intent intent=new Intent(BeginActivity.this, Main3Activity.class);
                    startActivity(intent);
                    //跳转到搜索页
                }
                else if("我的".equals(s)){
                    //跳转到个人中心页
                    next_personal(uid);
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
                        museumListAdapter = new MuseumListAdapter(getApplicationContext(), museumList,uid);
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
    //焦春雨：回返信息给个人中心
    private void jiexi(String s) throws JSONException {//解析显示数据：先接收数据，然后传递给个人中心界面
        JSONObject object = new JSONObject(s);
        System.out.println(s);
        String name = object.getString("name");//根据需要显示数据库返回的属性（数据库返回的信息可以在下面“run"里看到）
        String mobile = object.getString("mobile");
        String email = object.getString("email");
        int uid=object.getInt("uid");
        final String[] strings = { "账号 "+ uid,"姓名 "+name, "手机号 " + mobile, "邮箱  " + email};//用string来记录数组
        Intent intent = new Intent();//转移界面
        intent.setClass(BeginActivity.this, personalct.class);
        intent.putExtra("strings",strings);//传递string数组
        intent.putExtra("uid",uid);
        startActivity(intent);
    }
    private void next_personal(final int s)  {//解析显示数据：先接收数据，然后传递给个人中心界面
        new Thread(new Runnable() {
            @Override
            public void run() {//后端进行交互
                try {
                    OkHttpClient client = new OkHttpClient(); //创建HTTP客户端
                    System.out.println("ok??");
                    Request request = new Request.Builder()
                            .url("http://8.140.3.158:81/user/select/"+s) //后端请求接口的路径http://8.140.3.158:81/user/select/1
                            .get().build(); //创造http请求
                    Response response = client.newCall(request).execute(); //执行发送指令
                    String s = response.body().string();//接收回返数据
                    Log.d(s, "run: 显示全部数据");
                    jiexi(s);//josn解析
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BeginActivity.this, "网络请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}
