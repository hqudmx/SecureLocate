package f_app.dmx.com.securelocate;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingActivity  extends Activity{
    private ImageButton ima_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        ima_set=(ImageButton)findViewById(R.id.Setting_backup);
        ima_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHomePage.class);
            }
        });
    }
    public void startActivity(Class<?> cls){
        Intent intent=new Intent(SettingActivity.this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}
