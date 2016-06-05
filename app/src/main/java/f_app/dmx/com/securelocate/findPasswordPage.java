package f_app.dmx.com.securelocate;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import f_app.dmx.com.securelocate.R;

public class findPasswordPage extends Activity {
    private ImageButton  ima_find;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpassword);


        ima_find = (ImageButton) findViewById(R.id.Find_backup);
        ima_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(LoginActivity.class);
            }
        });
    }
    public void startActivity(Class<?> cls){
        Intent intent=new Intent(findPasswordPage.this,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}