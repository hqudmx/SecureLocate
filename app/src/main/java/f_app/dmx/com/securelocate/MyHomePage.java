package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import f_app.dmx.com.securelocate.R;

public class MyHomePage extends Activity{
    private boolean flag;
    public boolean isFirst;
    private GridView gv_home;
    private  long mExitTime;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("State", Context.MODE_PRIVATE);
        mEditor=sharedPreferences.edit();
        mEditor.putBoolean("App_state",true);
        mEditor.putBoolean("isFirst",true);
        mEditor.commit();
        SharedPreferences pre = getSharedPreferences("State", MODE_PRIVATE);
        flag = pre.getBoolean("App_state", false);
        //Toast.makeText(MyHomePage.this, "flag:"+flag, Toast.LENGTH_LONG).show();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_myhome);

        gv_home = (GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(MyHomePage.this));

        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(AlarmActivity.class);
                        break;
                    case 1:
                        startActivity(SafeActivity.class);
                        break;
                    case 2:
                        startActivity(LoginActivity.class);
                        break;
                    case 3:
                        startActivity(SettingActivity.class);
                        break;
                }
            }
        });
    }

    public void startActivity(Class<?> cls){
        Intent intent=new Intent(MyHomePage.this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if((System.currentTimeMillis() - mExitTime )< 2000){

                System.exit(0);

            }else {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                mExitTime= System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}

