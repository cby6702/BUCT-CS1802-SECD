package com.example.baidumapdemo.wangjiaxin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangtianzi.comment_museum;
import com.example.baidumapdemo.wangnaihao.Main.MainActivity;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Button button2=(Button)findViewById(R.id.button2);		//获取“进入地图页”按钮
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转地图页
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        Button buttoneva=(Button)findViewById(R.id.eva);		//获取“前往评价”按钮
        buttoneva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转评价页
                Intent intent2 = new Intent(getApplicationContext(), comment_museum.class);
                startActivity(intent2);
                finish();
            }
        });

        Button button1=(Button)findViewById(R.id.button);		//获取“上传视频”按钮
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转上传视频页
               // Intent intent2 = new Intent(getApplicationContext(), .class);//设置跳转页面
                //startActivity(intent2);
                //finish();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Message");
        String name = bundle.getString("MuseumName");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(name);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.museum_information:
                Intent intentmuseum=new Intent(getApplicationContext(),museumexplain.class);
                startActivity(intentmuseum);
                break;
            case R.id.collection_information:
                Intent intentcol=new Intent(getApplicationContext(),museumexplain.class);
                startActivity(intentcol);
                break;
            case R.id.exhibition_information:
                Intent intentex=new Intent(getApplicationContext(),museumexplain.class);
                startActivity(intentex);
                break;
            default:
        }
        return true;
    }
}
