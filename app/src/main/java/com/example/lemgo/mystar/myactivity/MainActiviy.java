package com.example.lemgo.mystar.myactivity;

import android.app.Activity;
import android.os.Bundle;
import com.example.lemgo.mystar.R;
import httpUtils.HttpUtil;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class MainActiviy extends Activity{
    private static final String HTTP_URL ="http://apis.baidu.com/acman/zhaiyanapi/tcrand?fangfa=json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.sendHttpResquset(HTTP_URL);

    }
}
