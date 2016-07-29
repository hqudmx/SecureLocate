package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/7/29.
 */
public class Alarm_setting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_setting_activity);
    }
    public void startActivity(Class<?> cls) {
        Intent intent = new Intent(Alarm_setting.this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public  void Cancle_click(View view){
        startActivity(SettingActivity.class);
    }
}
