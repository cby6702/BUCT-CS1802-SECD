package com.example.baidumapdemo.wangtianzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.baidumapdemo.R;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class comment_museum extends AppCompatActivity {

    private RatingBar ratingbar;	//星级评分条
    private List<Map<String,Object>> comment_infos = new ArrayList<>();

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_museum);

       // ListView listView=(ListView)findViewById(R.id.listview);// 获取列表视图
        int[] imageid=new int[100];
//                new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,
//                R.drawable.img04,R.drawable.img05,R.drawable.img06,
//                R.drawable.img07,R.drawable.img08,R.drawable.img09};// 定义并初始化保存图片id的数组
//        for(int i=0;i<100;i++)
//        {
//            imageid[i]=R.drawable.img01;//现在是全都设置成一张作为头像了
//        }

        String[] title=new String[100];
//                =new String[] {"用户001","用户002","用户003",
//                "用户004","用户005","用户006",
//                "用户007","用户008","用户009"};//定义并初始化保存列表项文字的数组(需要通过后端返回数据进行数组定义）
        String[] comment=new String[100];
//                =new String[] {"真棒！","下次一定","真好用！"
//                ,"好棒！","谢谢","很久没有遇到这么好的APP了！"
//                ,"该用户没有句子评价","希望多多完善","界面真棒！"
//        };
        //R.id.textView4;

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Usercomment> commentslist = HttpGet_Zcomments.getText(423);//获取数据
                System.out.println(commentslist);
                int i= (int)commentslist.get(0).getGeneral_comment();//获取博物馆总评显示出来
                System.out.println(i);
                ratingbar.setRating(i);

                final TextView textViewToChange = (TextView) findViewById(R.id.textView4);
                textViewToChange.setText(commentslist.get(0).getName());//这里博物馆名字显示的是“张三”，要通过mid找到博物馆的名字显示出来


                comment_infos = addtoList(commentslist);
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }).start();

        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Log.i("handler已接受到消息", "" + what);
                    Log.e("信息",comment_infos.toString());
                    show_comment_adapter();
                }
            }
        };



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
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
                List<Usercomment> commentslist = HttpGet_Zcomments.getText(423);//获取数据
                int i= (int)commentslist.get(0).getGeneral_comment();
                System.out.println(i);
                ratingbar.setRating(i);
            }
        });

    }
    public List<Map<String,Object>> addtoList(List<Usercomment>commentsList){
        List<Map<String,Object>> com_info = new ArrayList<>();

        for (Usercomment usercomment : commentsList) {
            Map<String,Object> mapinfo = new HashMap<>();
            mapinfo.put("image",usercomment.getAvatarUrl());
            mapinfo.put("title",usercomment.getName());
            mapinfo.put("comment",usercomment.getComment());
            com_info.add(mapinfo);
        }
        return com_info;
    }

    public void show_comment_adapter()
    {
        SimpleAdapter adapter=new SimpleAdapter
                (this,comment_infos,R.layout.main,
                        new String[]{"image","title","comment"},new int[]{R.id.image,R.id.title,R.id.comment});
        // 创建SimpleAdapter
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter); // 将适配器与ListView关联

        //回调函数
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);//获取选择项的值
//                Toast.makeText(comment_museum.this,map.get("name").toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}
