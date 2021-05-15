package com.example.baidumapdemo.axingbuxiang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        //建立数据源
//        String[] mItems={"博物馆","展览","藏品"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  //      spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();//这个result就是三个下拉的搜索内容
                if(result.equals("博物馆")) flagg=0;
                if(result.equals("展览")) flagg=1;
                if(result.equals("藏品")) flagg=2;
                Toast.makeText(Main3Activity.this, result, Toast.LENGTH_SHORT).show();//把result显示出来但不影响用户操作的提示栏
//                String res = HttpGet_Museums.getText("1");//获取数据
//                System.out.println(res);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        ListView listView=(ListView)findViewById(R.id.listviewm);// 获取列表视图
        final String[] title
                =new String[100];
//                = new String[] {"用户001","用户002","用户003",
//                "用户004","用户005","用户006",
//                "用户007","用户008","用户009"};//定义并初始化保存列表项文字的数组(需要通过后端返回数据进行数组定义）
        Button buttonq=(Button)findViewById(R.id.qbtn);		//获取“确认”按钮
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input=(EditText) findViewById(R.id.sous);//搜索框输入的数据
                String inn=input.getText().toString().trim();
                Toast.makeText(Main3Activity.this, inn, Toast.LENGTH_SHORT).show();

                if(flagg==0)//按博物馆名称搜索
                {
                    int i=0;
                    List<Museums> museumsList = HttpGet_Museums.getText(inn);//获取数据
                    for (Museums museums : museumsList) {
                       // System.out.println(museums.getName());
                        title[i]=museums.getName().toString();
                        i++;
                        //System.out.println(museums.getMid());
                    }

                }
                if(flagg==1)//按展览名称搜索
                {
                    int i=0;
                    List<Exhibition> exhibitionList = HttpGet_Exhibition.getText(inn);//获取数据
                    for (Exhibition exhibition : exhibitionList) {
                       // System.out.println(exhibition.getEname());
                        //System.out.println(exhibition.getMid());
                        title[i]=exhibition.getEname();
                        i++;
                    }
                }
                if(flagg==2)//按藏品名称搜索
                {
                    int i=0;
                    List<Collection> collectionList = HttpGet_Collection.getText(inn);//获取数据
                    for (Collection collection : collectionList) {
                        //System.out.println(collection.getCname());
                        //System.out.println(collection.getMid());
                        title[i]=collection.getCname();
                        i++;
                    }
                }
            }
        });


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
}

