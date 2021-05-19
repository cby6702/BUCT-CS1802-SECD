package com.example.baidumapdemo.guoziyu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidumapdemo.R;

import java.util.List;

public class News_Adapter extends BaseAdapter {
    private int resourceId;
    private LayoutInflater mInflater;//布局装载器对象
    private List<class_news> newsList;
    public News_Adapter(Context context, int textViewResourceId, List<class_news> objects){
//        resourceId=textViewResourceId;
        mInflater = LayoutInflater.from(context);
        newsList=objects;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
       return newsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view = mInflater.inflate(R.layout.news_ltem,null);
        //获取相应索引的NewsItem对象
        final class_news news=newsList.get(i);
        //内部布局
        TextView news_context=(TextView) view.findViewById(R.id.news_in);
        final TextView news_tid=view.findViewById(R.id.news_tid);
        Button button=view.findViewById(R.id.delete);
        //更新ui
        news_context.setText(news.getComtext());
        news_tid.setText(String.valueOf(news.getTid()));
        button.setText("删除");

        System.out.println(news.getTid());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("TID",String.valueOf(news.getTid()));
            }
        });


        return view;
    }

}
