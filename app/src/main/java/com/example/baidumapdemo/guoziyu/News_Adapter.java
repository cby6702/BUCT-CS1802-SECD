package com.example.baidumapdemo.guoziyu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.baidumapdemo.R;

import java.util.List;

public class News_Adapter extends ArrayAdapter<class_news> {
    private int resourceId;
    public News_Adapter(Context context, int textViewResourceId, List<class_news> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        class_news news=getItem(position);   //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView news_context=(TextView) view.findViewById(R.id.news_in);//内部布局
        news_context.setText(news.getComtext());
        return view;
    }

}
