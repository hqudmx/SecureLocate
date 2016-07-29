package f_app.dmx.com.securelocate;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class SettingActivity  extends Activity{
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableLayout =(TableLayout)findViewById(R.id.MorePageTableLayout_Follow);
        TableRow tableRow=new TableRow(getBaseContext());
      /*  tableRow.getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Mao_Activity.class);
            }
        });*/
        setContentView(R.layout.activity_setting);


    }
    public void startActivity(Class<?> cls){
        Intent intent=new Intent(SettingActivity.this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void Click(View v){
        switch (v.getId()){
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
            case R.id.img_humiture:
                startActivity(Humiture.class);
                finish();
                break;
            case R.id.tv_humiture:
                startActivity(Humiture.class);
                finish();
                break;

        }
    }
    public void Login(View v){
        startActivity(LoginActivity.class);
    }

    public void Mao_click(View view){
        startActivity(Mao_Activity.class);
    }

    public void Alarm_click(View view){
        startActivity(Alarm_setting.class);
        }
    public void Feedback_click(View view){
        startActivity(FeedBackActivity.class);
    }
    public void About_click(View view){
        startActivity(AboutActivity.class);
    }



}
