package httpUtils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class HttpUtil {
    public static void sendHttpResquset(final String httpUrl) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                String result =null;
                StringBuilder response = new StringBuilder();
                try {
                    URL url = new URL(httpUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("apikey", "fec11918da9ce96f5d51ac4e1feac1c7");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        response.append(strRead);
                        response.append("\r\n");
                    }
                    reader.close();
                    result = response.toString();
                    parseJsonData(result);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    private  static void  parseJsonData(String result) {
        try{
            JSONObject jsonObject = new JSONObject(result);
            String taici = jsonObject.getString("taici");
            String source = jsonObject.getString("source");
            Log.d("msg",taici);
            Log.d("msg",source);
           saveInfo(taici,source);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private static void saveInfo(String taici, String source) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        edit.putString("taici",taici);
        edit.putString("source",source);
        edit.commit();

    }
}
