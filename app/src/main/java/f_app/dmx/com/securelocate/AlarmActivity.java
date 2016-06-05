package f_app.dmx.com.securelocate;


import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.location.Location;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import cn.bmob.v3.listener.GetListener;


public class AlarmActivity  extends Activity {
   private String  MyNodeName="1";
    private Vibrator myVibrator;
    private MediaPlayer mediaPlayer;
    private Context mContext;
    long[] pattern = {1000, 2000, 1000, 2000};//等待一秒  震动两秒   等待一秒  震动两秒
    private ImageButton ima_back;
    private float a1, b1, a2, b2,a3,b3;
    private final static float r = 1;
    public static final int NOTIFICATION = 1;
    private String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        //初始化后端云Bmob
        Bmob.initialize(this,"b68331b1b8050e913db6cec454b5e495");
        //获得系统的Vibrator实例:
        myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
        myVibrator.vibrate(pattern, -1);
       // queryData();
        select();
        Alarm(a1, b1, a2, b2);
        SetVoice();

        mContext = AlarmActivity.this;
        ima_back = (ImageButton) findViewById(R.id.Alarm_backup);
        ima_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHomePage.class);
            }
        });
    }

    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(AlarmActivity.this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void Alarm(float x1, float y1, float x2, float y2) {

        if (Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) < r) {
            myVibrator.vibrate(pattern, 0);
              //Toast.makeText(getApplicationContext(),"这里危险，请小心绕行",Toast.LENGTH_SHORT).show();
        } else {
            myVibrator.cancel();
        }
    }

    public void SetVoice() {
        //=new MediaPlayer();
        mediaPlayer = mediaPlayer.create(this, R.raw.alarm);
        mediaPlayer.start();
        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面
        new AlertDialog.Builder(AlarmActivity.this).setTitle("警报").setMessage("危险！危险！请绕行！")
                .setPositiveButton("关闭警报", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        myVibrator.cancel();
                        // AlarmActivity.this.finish();
                    }
                }).show();
    }

//    /**
//     * 查询数据
//     */
//    public void queryData(){     //Object[]
//        BmobQuery query = new BmobQuery("location");
//        query.findObjects(this, new FindCallback() {
//            @Override
//            public void onSuccess(JSONArray arg0) {
//                //注意：查询的结果是JSONArray,需要自行解析
//                //showToast("查询成功:"+arg0.length());
//                Toast.makeText(AlarmActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
//                location lo=new location();
//
//            }
//
//            @Override
//            public void onFailure(int arg0, String arg1) {
//                //showToast("查询失败:"+arg1);
//                Toast.makeText(AlarmActivity.this,"更新失败:"+arg1, Toast.LENGTH_SHORT).show();
//            }
//        });
////        return arg0.toArray();
//    }



    public interface UpdatePoint{


    }
    /**
     * 查询数据
     */

    private  void select(){
        BmobQuery<LocationData> qu=new BmobQuery<LocationData>();
        qu.getObject(this, "WTMb333d", new GetListener<LocationData>() {
            @Override
            public void onSuccess(LocationData object) {
                MyNodeName=object.getNODE();
                float x1=object.getX();
                float x2=object.getY();
                //String Date=obj.getUpdatedAt().toString();

                Toast.makeText(AlarmActivity.this, "更新成功:"+"节点名称"+MyNodeName+"坐标("+x1+","+x2+")", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(AlarmActivity.this, "更新失败"+s+"", Toast.LENGTH_SHORT).show();
            }
        });

    }



}









