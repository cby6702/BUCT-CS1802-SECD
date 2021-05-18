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
    private List<Map<String,Object>> comment_infos = new ArrayList<>();//定义Usercomment的json数组
    private int mid=1;//通过mid获取博物馆名字显示出来
    private int uid=1;//通过mid获取博物馆名字显示出来

    Handler handler;//为了控制线程

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_museum);

        int[] imageid=new int[100];//放置头像
        String[] title=new String[100];//放置姓名
        String[] comment=new String[100];//放置评论内容

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Usercomment> commentslist = HttpGet_Zcomments.getText(mid);//获取数据
                System.out.println(commentslist);
                int i= (int)commentslist.get(0).getGeneral_comment();//获取博物馆总评显示出来（用星标）
                System.out.println(i);
                ratingbar.setRating(i);

                mid=commentslist.get(0).getMid();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = HttpGet_Museumname.getText(mid);
                        final TextView textViewToChange = (TextView) findViewById(R.id.textView4);
                        textViewToChange.setText(res);//要通过mid找到博物馆的名字显示出来
                        System.out.println(res);
                    }
                }).start();
                comment_infos = addtoList(commentslist);//addtoList添加进comment_infos的json数组
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }
        }).start();


        Button buttonq=(Button)findViewById(R.id.qqbtn);
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转前往评价的页面
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = HttpGet_commentjudge.getText(uid,mid);//这里实现跳转
                        System.out.println(res);
                        if(res.equals("true"))
                        {
                            Message message=new Message();
                            message.what=2;
                            handler.sendMessage(message);
                        }
                        else
                        {
                            Message message=new Message();
                            message.what=3;
                            handler.sendMessage(message);
                        }
                    }
                }).start();

            }
        });

        //handle控制线程
        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                int what = msg.what;
                Log.i("handler", "已经收到消息，消息what：" + what + ",id:" + Thread.currentThread().getId());

                if (what == 1) {
                    Log.i("handler已接受到消息", "" + what);
                    Log.e("信息",comment_infos.toString());
                    show_comment_adapter();// 将适配器与ListView关联
                }
                if (what == 2) {
                    Log.i("handler已接受到消息", "" + what);

                    Intent intent2 = new Intent(getApplicationContext(), Main2Activity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("number1",uid);//用户id
                    bundle.putInt("number2",mid);//博物馆id
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    finish();

                }
                if (what == 3) {
                    Log.i("handler已接受到消息", "" + what);
                    Toast.makeText(comment_museum.this,"您没有评价权限或已经评价",Toast.LENGTH_SHORT).show();
                }
            }
        };



        //以下是评星条的内容
        ratingbar = (RatingBar) findViewById(R.id.ratingBar2);	//获取星级评分条
        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.e("------------","当前的评价等级："+rating);
                List<Usercomment> commentslist = HttpGet_Zcomments.getText(1);//获取数据
                int i= (int)commentslist.get(0).getGeneral_comment();
                System.out.println(i);
                ratingbar.setRating(i);
            }
        });
    }

    public List<Map<String,Object>> addtoList(List<Usercomment>commentsList){
        //addtoList添加进comment_infos的json数组
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
