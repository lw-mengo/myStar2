package httpUtils;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class HttpUtil {
    public static String sendHttpResquset(String httpUrl){
                HttpURLConnection connection = null;
                String result = null;
                StringBuilder response = new StringBuilder();

                try {
                    URL url = new URL(httpUrl);
                    Log.d("msg1",url.toString());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("apikey", "fec11918da9ce96f5d51ac4e1feac1c7");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    Log.d("msg4", inputStream.toString());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
                    String strRead = null;
                    Log.d("msg4", reader.readLine());
                    while ((strRead = reader.readLine()) != null) {
                        response.append(strRead);
                        response.append("\r\n");
                        Log.d("msg2", response.toString());
                    }
                    reader.close();
                    result = response.toString();
                    Log.d("msg3", result);
                }catch (Exception e){
                    e.printStackTrace();
                    } finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
        return result ;
    }
}
