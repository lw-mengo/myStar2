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

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class HttpUtil {
    public static void  sendHttpResquset(final String httpUrl) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                String result = null;
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
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        response.append(strRead);
                        response.append("\r\n");
                    }
                    reader.close();
                    result = response.toString();
                    parseJsonDate(result);
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

    private static void parseJsonDate(String result) {
        try {
            JSONObject json = new JSONObject(result);
            String taici = json.getString("taici");
            String source = json.getString("source");

            saveDate(taici,source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void saveDate(String taici, String source) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
        editor.putString("taici",taici);
        editor.putString("source",source);
        editor.commit();
    }
}
