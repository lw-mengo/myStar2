package httpUtils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/12/6 0006.
 * 使用回调机制
 */

public class StarHttp {
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";


    public static  void getStarHttp(final String address,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection  = null;
                try {
                 //String urlStar = URLEncoder.encode(address,"gbk");
                    URL url = new URL(address);
                    Log.d("msg5",address);
                    connection  = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-agent",userAgent);
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                    StringBuilder response = new StringBuilder();
                    String line = null;
                    while ((line=bufferedReader.readLine())!=null){
                        response.append(line);
                    }
                    if(listener!=null){
                        //回调onfinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if(listener!=null)
                    listener.onError(e);
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
