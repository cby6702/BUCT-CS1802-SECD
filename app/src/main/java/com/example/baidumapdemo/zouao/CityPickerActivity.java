package com.example.baidumapdemo.zouao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.baidumapdemo.R;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.HotCity;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;

/**
 * 邹傲 城市选择
 */
public class CityPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("北京市", "北京", "101010100"));
        hotCities.add(new HotCity("上海市", "上海", "101020100"));
        hotCities.add(new HotCity("河北省", "河北省", "101280101"));
        hotCities.add(new HotCity("广西", "广西省", "101280601"));
        hotCities.add(new HotCity("天津市", "天津", "101210101"));
        hotCities.add(new HotCity("黑龙江", "黑龙江", "101280602"));

        int anim=0;
        CityPicker.getInstance()
                .setFragmentManager(getSupportFragmentManager())  //此方法必须调用
                .enableAnimation(true)//启用动画效果
                .setAnimationStyle(anim)  //自定义动画
                .setLocatedCity(new LocatedCity("北京市", "北京", "101010100"))  //APP自身已定位的城市，默认为null（定位失败）
                .setHotCities(hotCities)
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        //Log.v("城市名称",data.getName());
                        Bundle bundle=new Bundle();
                        Toast.makeText(getApplicationContext(), data.getName(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(CityPickerActivity.this, BeginActivity.class);
                        bundle.putString("cityname",data.getName());
                        intent.putExtra("Message",bundle);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //定位完成之后更新数据
                                CityPicker.getInstance()
                                        .locateComplete(new LocatedCity("北京市", "北京", "101010100"), LocateState.SUCCESS);
                            }
                        }, 2000);
                    }
                })
                .show();


    }
}
