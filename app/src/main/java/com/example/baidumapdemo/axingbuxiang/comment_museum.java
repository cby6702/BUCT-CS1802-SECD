package com.example.baidumapdemo.axingbuxiang;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.baidumapdemo.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class comment_museum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] imageid=new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,
                R.drawable.img04,R.drawable.img05,R.drawable.img06,
                R.drawable.img07,R.drawable.img08,R.drawable.img09};
        String[] title=new String[] {"用户001","用户002","用户003",
                "用户004","用户005","用户006",
                "用户007","用户008","用户009"};
        List<Map<String ,Object >> listitem = new ArrayList<Map<String ,Object >>();
        for(int i=0;i<imageid.length;i++){
            Map<String,Object> map =new HashMap<String, Object>();
            map.put("image",imageid[i]);
            map.put("name",title);
            listitem.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,listitem,R.layout.activity_main,new String[]{"name","image"},new int[]{R.id.title,R.id.image});
        ListView listView=(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);
                Toast.makeText(comment_museum.this,map.get("名字").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
