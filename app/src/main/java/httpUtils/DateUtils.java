package httpUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.lemgo.mystar.myactivity.MainActiviy;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class DateUtils {
    public static void  handleDataInfo(Context context,String  response){
        try{
            Log.d("msg6",response);
            JSONObject jsonObject =new JSONObject(response);
            if(jsonObject.getInt("error_code")==205801){
              Log.d("msg","chucuole");
            }else {
                String dateTime = jsonObject.getString("datetime");
                Log.d("msg", dateTime);
                String all = jsonObject.getString("all");
                String color = jsonObject.getString("color");
                String summary = jsonObject.getString("summary");
                saveDateInfo(context, dateTime, all, color, summary);
            }

        }catch (JSONException e){
            e.printStackTrace();

        }
    }

    private static void saveDateInfo(Context context,String dateTime, String all, String color, String summary) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("datetime",dateTime);
        editor.putString("all",all);
        editor.putString("color",color);
        editor.putString("summary",summary);
        Log.d("msg1",dateTime);
        editor.commit();
    }
}
