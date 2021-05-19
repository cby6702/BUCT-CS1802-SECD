package com.example.baidumapdemo.wangjiaxin.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangjiaxin.Bean.collectioninfo;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdpter extends BaseAdapter {
    private List<collectioninfo> mList = new ArrayList<>();//数据源
    private LayoutInflater mInflater;//布局装载器对象
    ImageView imageView;    //collection图片view
    TextView titleTextView; //collection名称view
    TextView contentTextView;        //collection详情view
    public CollectionAdpter(Context context, List<collectioninfo> list){
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
        view = mInflater.inflate(R.layout.collection_item,null);
        /**
         * 找到item布局文件中对应的控件
         */
        imageView = (ImageView) view.findViewById(R.id.collection_image);
        titleTextView = (TextView) view.findViewById(R.id.text1);
        contentTextView=view.findViewById(R.id.text2);
        //获取相应索引的ItemBean对象
        final collectioninfo bean=mList.get(i);
        Glide.with(mInflater.getContext()).load(bean.getColpicture()).into(imageView);
        titleTextView.setText(bean.getCname());
        contentTextView.setText(bean.getCintroduce());

        return view;
    }
}
