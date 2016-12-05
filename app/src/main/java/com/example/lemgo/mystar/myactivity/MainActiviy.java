package com.example.lemgo.mystar.myactivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lemgo.mystar.R;
import httpUtils.HttpUtil;
import httpUtils.MyApplication;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class MainActiviy extends Activity{
    private static final String HTTP_URL ="http://apis.baidu.com/acman/zhaiyanapi/tcrand?fangfa=json";

    private Button btn ;
    private TextView textView;
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


    }
}
