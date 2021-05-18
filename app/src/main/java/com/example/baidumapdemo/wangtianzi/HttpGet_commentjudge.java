package com.example.baidumapdemo.wangtianzi;
//直接返回true/false
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet_commentjudge {

    public static String getText(int uid , int mid) {

        try {
            String u = "http://8.140.3.158:81/comments/judge/" + uid+"/" + mid;
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Gson gson = new Gson();
            //读数据
            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            is.close();
            System.out.println("看返回的数据是否写全:" + buffer.toString());//看返回的数据是否写全
            //result = gson.fromJson(buffer.toString(),Judge.class);
            //return result;
            return buffer.toString();
        }
        catch (Exception e) {
            //Log.d("TAG",e.toString());
            e.printStackTrace();
            return "false";
        }

    }
}

