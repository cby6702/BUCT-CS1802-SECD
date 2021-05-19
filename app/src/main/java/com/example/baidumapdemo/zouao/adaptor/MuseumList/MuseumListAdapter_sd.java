package com.example.baidumapdemo.zouao.adaptor.MuseumList;

/**
 * 石鼎 博物馆排名页对应
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.Main5Activity;
import com.example.baidumapdemo.zouao.Bean.m_info;

import java.util.ArrayList;
import java.util.List;

public class MuseumListAdapter_sd extends BaseAdapter {
    private List<m_info> mList = new ArrayList<>();//数据源
    private LayoutInflater mInflater;//布局装载器对象
    ImageView imageView;    //博物馆图片view
    TextView titleTextView; //博物馆名称view
    Button button;          //博物馆详情按钮

    // 通过构造方法将数据源与数据适配器关联起来
    // context:要使用当前的Adapter的界面对象
    public MuseumListAdapter_sd(Context context, List<m_info> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.fruit_item,null);
        /**
         * 找到item布局文件中对应的控件
         */
        imageView = (ImageView) view.findViewById(R.id.fruit_image);
        titleTextView = (TextView) view.findViewById(R.id.fruit_name);

        //获取相应索引的ItemBean对象
        final m_info bean = mList.get(i);
        imageView.setMaxHeight(50);
        imageView.setMaxWidth(50);
        Glide.with(mInflater.getContext()).load(bean.getPicture()).into(imageView);
        titleTextView.setText(bean.getName());
        //为按钮设计点击时间
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //获取当前context
                Context context = mInflater.getContext();
                //跳转到博物馆详情页
                Intent intent=new Intent(context, Main5Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//不是在Activity中进行跳转，需要添加这个方法;
                Bundle bundle=new Bundle();
                bundle.putString("MuseumName",bean.getName());
                intent.putExtra("Message",bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
