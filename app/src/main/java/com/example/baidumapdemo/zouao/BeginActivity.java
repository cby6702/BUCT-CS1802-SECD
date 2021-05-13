package com.example.baidumapdemo.zouao;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baidumapdemo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 博物馆首页 邹傲
 */
public class BeginActivity extends AppCompatActivity {

    //底部导航
    private TextView mTextView;
    private BottomNavigationView mNavigationView;

    //搜索框
    private SearchView searchView;
    private ListView listView;
    //定义自动完成的列表
    private final String[] mStrings = {"国家博物馆","首都博物馆","北京博物馆","国家博物馆","首都博物馆","北京博物馆","国家博物馆","首都博物馆","北京博物馆","国家博物馆","首都博物馆","北京博物馆","国家博物馆","首都博物馆","北京博物馆"};
    private String cityname="北京";       //默认当前城市为北京
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        listView = findViewById(R.id.lv);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrings);
        listView.setAdapter(adapter);
        //为ListView启动过滤
        listView.setTextFilterEnabled(true);
        searchView = (SearchView) findViewById(R.id.sv);
        //设置SearchView自动缩小为图标
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成一个图标点击展开
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        searchView.setQueryHint("输入您想查找的内容");
        //配置监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                //此处添加查询开始后的具体时间和方法
                Toast.makeText(BeginActivity.this,"you choose:" + query,Toast.LENGTH_SHORT).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText(newText);
                    //adapter.getFilter().filter(newText.toString());//替换成本句后消失黑框！！！
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String string = (String)adapter.getItem(position);
                searchView.setQuery(string,true);
            }
        });

        //底部导航监听器
        mTextView = (TextView) findViewById(R.id.text);
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mTextView.setText(item.getTitle().toString().toUpperCase());
                return true;
            }
        });
        //跳转到切换城市页
        Button pickcity=findViewById(R.id.pickcity);
        pickcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BeginActivity.this,CityPickerActivity.class);
                //启动
                startActivity(intent);
            }
        });
        //跳转到博物馆排名页
        Button museumrank=findViewById(R.id.museumrank);
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
        }
    }



}

