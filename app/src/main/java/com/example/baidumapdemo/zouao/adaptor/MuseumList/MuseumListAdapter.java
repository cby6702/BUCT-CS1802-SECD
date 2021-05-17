package com.example.baidumapdemo.zouao.adaptor.MuseumList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baidumapdemo.R;
import com.example.baidumapdemo.zouao.Bean.Museum;
import com.example.baidumapdemo.zouao.Bean.MuseumItem;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MuseumListAdapter extends BaseAdapter {
    private List<Museum> mList = new ArrayList<>();//数据源
    private LayoutInflater mInflater;//布局装载器对象
    ImageView imageView;    //博物馆图片view
    TextView titleTextView; //博物馆名称view
    private Handler handle= new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {

                case 0:
                    System.out.println("111");
                    Bitmap bmp=(Bitmap)msg.obj;
                    imageView.setImageBitmap(bmp);
                    break;
            }

        };

    };

    // 通过构造方法将数据源与数据适配器关联起来
    // context:要使用当前的Adapter的界面对象
    public MuseumListAdapter(Context context, List<Museum> list) {
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
        view = mInflater.inflate(R.layout.museumlist_item,null);
        /**
         * 找到item布局文件中对应的控件
         */
        imageView = (ImageView) view.findViewById(R.id.museum_image);
        titleTextView = (TextView) view.findViewById(R.id.text1);
        //获取相应索引的ItemBean对象
        final Museum bean = mList.get(i);
        //获取图片资源
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = getURLimage(bean.getPicture());
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bmp;
                handle.sendMessage(msg);
            }

        }).start();
        /**
         * 设置控件的对应属性值
         */
        imageView.setImageResource(bean.getItemImageResId());
        titleTextView.setText(bean.getName());

        return view;
    }
    public Bitmap getURLimage(String url) {

        Bitmap bmp = null;

        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);//读取图像数据
            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return bmp;

    }
}
