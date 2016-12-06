package com.example.lemgo.mystar.myactivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lemgo.mystar.R;

import httpUtils.DateUtils;
import httpUtils.HttpCallBackListener;
import httpUtils.HttpUtil;
import httpUtils.MyApplication;
import httpUtils.StarHttp;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class MainActiviy extends Activity{
    private static final String HTTP_URL ="http://apis.baidu.com/acman/zhaiyanapi/tcrand?fangfa=json";
    private static final  String KEY = "35f5b278c09b8254744b83c1039b2f4c";
    private static final String URL_STAR="http://web.juhe.cn:8080/constellation/getAll?consName=" ;
    private Button btn,star ;
    private TextView textView,starView;
    private EditText editText;
    private String httpUrl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.sendHttpResquset(HTTP_URL);
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
                String taici = preferences.getString("taici","");
                String source = preferences.getString("source","");
                String showText = "         "+taici+"\n"+"                                                 \t----------------"+source;
                textView.setText(showText);
            }
        });

        starView = (TextView) findViewById(R.id.starView);
        star = (Button) findViewById(R.id.star);
        editText = (EditText) findViewById(R.id.editText);
        String starName = editText.getText().toString();
        httpUrl  = URL_STAR +starName+"&type=today&key="+KEY;
        Log.d("msg2",httpUrl);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StarHttp.getStarHttp(httpUrl, new HttpCallBackListener() {
                    @Override
                    public void onFinish(String response) {
                        DateUtils.handleDataInfo(MainActiviy.this,response);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showInfo();
                            }
                        });
                    }
                    @Override
                    public void onError(Exception e) {
                        Log.d("Error:",e.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActiviy.this,"shibai",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                });
            }
        });
    }

    private void showInfo() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dateTime = preferences.getString("datetime","");
        String all = preferences.getString("all","");
        String color = preferences.getString("color","");
        String summary = preferences.getString("summary","");
        String starText = "时间："+dateTime+"\n"+"综合指数:"+all+"\n"
        +"幸运颜色:"+color+"\n"+"今日概述:"+summary+"";
        starView.setText(starText);
    }
}
