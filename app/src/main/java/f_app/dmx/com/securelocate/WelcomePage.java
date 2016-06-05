package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import f_app.dmx.com.securelocate.R;


public class WelcomePage extends Activity {

    private TextView mVersionTV;
    private String mVersion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_layout);
        Handler handler = new Handler();


        Runnable updateThread = new Runnable(){

            public void run(){
                Intent intent = new Intent();
                intent.setClass(WelcomePage.this,MyHomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                WelcomePage.this.startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(updateThread, 1000);


    }
}