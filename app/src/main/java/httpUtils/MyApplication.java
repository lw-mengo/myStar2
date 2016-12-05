package httpUtils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
