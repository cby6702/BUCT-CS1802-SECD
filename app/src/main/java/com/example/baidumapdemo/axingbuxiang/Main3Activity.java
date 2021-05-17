package com.example.baidumapdemo.axingbuxiang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.baidumapdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    private String cityname="北京";       //默认当前城市为北京
    private int flagg=0;
    private List<Map<String,Object>> collection_infos = new ArrayList<>();//定义json数组
    Handler handler;//为了控制线程

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        //建立数据源

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();//这个result就是三个下拉的搜索内容
                if(result.equals("博物馆")) flagg=0;
                if(result.equals("展览")) flagg=1;
                if(result.equals("藏品")) flagg=2;
                Toast.makeText(Main3Activity.this, result, Toast.LENGTH_SHORT).show();//把result显示出来但不影响用户操作的提示栏

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        ListView listView=(ListView)findViewById(R.id.listviewm);// 获取列表视图
        final String[] title =new String[100];
        Button buttonq=(Button)findViewById(R.id.qbtn);		//获取“确认”按钮
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input=(EditText) findViewById(R.id.sous);//搜索框输入的数据
                final String inn=input.getText().toString().trim();
                Toast.makeText(Main3Activity.this, inn, Toast.LENGTH_SHORT).show();

                if(flagg==0)//按博物馆名称搜索
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            List<Museums> museumsList = HttpGet_Museums.getText(inn);//获取数据
                            System.out.println(museumsList);

                            collection_infos = addtoList0(museumsList);
                        }
                    }).start();
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);

                }
                if(flagg==1)//按展览名称搜索
                {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            List<Exhibition> exhibitionList = HttpGet_Exhibition.getText(inn);//获取数据
                            System.out.println(exhibitionList);

                            collection_infos = addtoList1(exhibitionList);
                        }
                    }).start();
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
                if(flagg==2)//按藏品名称搜索
                {
                    new Thread(new Runnable() {
                                @Override
                        public void run() {
                            List<Collection> collectionlist = HttpGet_Collection.getText(inn);//获取数据
                             // System.out.println(collectionlist);
                             collection_infos = addtoList(collectionlist);
                                }
                    }).start();
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }
                    }


        });

        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Log.i("handler已接受到消息", "" + what);
                    Log.e("信息",collection_infos.toString());
                    show_museum_adapter();
                }
            }
        };

        List<Map<String ,Object >> listitem = new ArrayList<Map<String ,Object >>();// 创建一个list集合
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for(int i=0;i<title.length;i++){
            Map<String,Object> map =new HashMap<String, Object>();// 实例化Map对象
            map.put("name",title[i]);
            listitem.add(map);// 将map对象添加到List集合中
        }
        SimpleAdapter adapter=new SimpleAdapter
                (this,listitem,R.layout.xmain,
                        new String[]{"name"},new int[]{R.id.title});
        // 创建SimpleAdapter

        listView.setAdapter(adapter); // 将适配器与ListView关联
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);//获取选择项的值
                Toast.makeText(Main3Activity.this,map.get("name").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    public List<Map<String,Object>> addtoList0(List<Museums> museumsList){
        List<Map<String,Object>> collect_info = new ArrayList<>();

        for (Museums museums : museumsList) {
            Map<String,Object> mapinfo = new HashMap<>();
            mapinfo.put("title",museums.getName());
            collect_info.add(mapinfo);
        }
        return collect_info;
    }
    public List<Map<String,Object>> addtoList1(List<Exhibition> exhibitionList){
        List<Map<String,Object>> collect_info = new ArrayList<>();

        for (Exhibition exhibition : exhibitionList) {
            Map<String,Object> mapinfo = new HashMap<>();
            mapinfo.put("title",exhibition.getEname());
            collect_info.add(mapinfo);
        }
        return collect_info;
    }
    public List<Map<String,Object>> addtoList(List<Collection> collectionList){
        List<Map<String,Object>> collect_info = new ArrayList<>();

        for (Collection collection : collectionList) {
            Map<String,Object> mapinfo = new HashMap<>();
            mapinfo.put("title",collection.getCname());
            collect_info.add(mapinfo);
        }
        return collect_info;
    }
    public void show_museum_adapter(){
        SimpleAdapter adapter=new SimpleAdapter
                (this,collection_infos,R.layout.xmain,
                        new String[]{"title"},new int[]{R.id.title});
        ListView listView = findViewById(R.id.listviewm);
        listView.setAdapter(adapter); // 将适配器与ListView关联
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);//获取选择项的值
                Toast.makeText(getApplicationContext(),map.get("title").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

