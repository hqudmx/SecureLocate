package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Switch;

/**
 * Created by Administrator on 2016/6/1.
 */
public class Humiture extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_humiture);
    }

    public void startActivity(Class cla){
        Intent intent=new Intent(Humiture.this,cla);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void Click(View v ){
        switch(v.getId()){
            case R.id.image_map:
                startActivity(MyMap.class);
                finish();
                break;
            case R.id.tv_map:
                startActivity(MyMap.class);
                finish();
                break;
            case R.id.image_location:
                startActivity(AlarmActivity.class);
                finish();
                break;
            case R.id.tv_alarm:
                startActivity(AlarmActivity.class);
                finish();
                break;
            case R.id.image_setting:
                startActivity(SettingActivity.class);
                finish();
                break;
            case R.id.tv_set:
                startActivity(SettingActivity.class);
                finish();
                break;
        }
    }
}
