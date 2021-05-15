package com.example.baidumapdemo.axingbuxiang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangtianzi.Main2Activity;

public class Main3Activity extends AppCompatActivity {

    private String cityname="北京";       //默认当前城市为北京


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
                Toast.makeText(Main3Activity.this, result, Toast.LENGTH_SHORT).show();//把result显示出来但不影响用户操作的提示栏

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        Button buttonq=(Button)findViewById(R.id.qbtn);		//获取“确认”按钮
        buttonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果能搜索到，跳转到博物馆详情页面，否则跳转false

            }
        });




    }
}

