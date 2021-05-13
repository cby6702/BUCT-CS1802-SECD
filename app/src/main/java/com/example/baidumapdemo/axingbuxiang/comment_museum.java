package com.example.baidumapdemo.axingbuxiang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangtianzi.Main2Activity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class comment_museum extends AppCompatActivity {

    private RatingBar ratingbar;	//星级评分条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_museum);

        ListView listView=(ListView)findViewById(R.id.listview);// 获取列表视图
        int[] imageid=new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,
                R.drawable.img04,R.drawable.img05,R.drawable.img06,
                R.drawable.img07,R.drawable.img08,R.drawable.img09};// 定义并初始化保存图片id的数组
        String[] title=new String[] {"用户001","用户002","用户003",
                "用户004","用户005","用户006",
                "用户007","用户008","用户009"};//定义并初始化保存列表项文字的数组(需要通过后端返回数据进行数组定义）
        String[] comment =new String[] {"真棒！","下次一定","真好用！"
                ,"好棒！","谢谢","很久没有遇到这么好的APP了！"
                ,"该用户没有句子评价","希望多多完善","界面真棒！"
        };
        List<Map<String ,Object >> listitem = new ArrayList<Map<String ,Object >>();// 创建一个list集合
        // 通过for循环将图片id和列表项文字放到Map中，并添加到list集合中
        for(int i=0;i<imageid.length;i++){
            Map<String,Object> map =new HashMap<String, Object>();// 实例化Map对象
            map.put("image",imageid[i]);
            map.put("name",title[i]);
            map.put("comment",comment[i]);
            listitem.add(map);// 将map对象添加到List集合中
        }
        SimpleAdapter adapter=new SimpleAdapter
                (this,listitem,R.layout.main,
                        new String[]{"name","image","comment"},new int[]{R.id.title,R.id.image});
                // 创建SimpleAdapter

        listView.setAdapter(adapter); // 将适配器与ListView关联
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);//获取选择项的值
                Toast.makeText(comment_museum.this,map.get("name").toString(),Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonq=(Button)findViewById(R.id.qqbtn);		//获取“返回”按钮
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转回上一个页面 即 博物馆评价表总页面
                Intent intent2 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent2);
                finish();
            }
        });

        //以下是评星条的内容
        ratingbar = (RatingBar) findViewById(R.id.ratingBar2);	//获取星级评分条

    }
}
