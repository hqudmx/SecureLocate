package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import f_app.dmx.com.securelocate.R;

public class RegistePage extends Activity {
    private EditText emial_regist;
    private EditText userName_regist;
    private  EditText confirm_regist;
    private EditText passWord_regist;
    private Button bt_regist;
    private ImageButton ima_Regist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_regist);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);

        emial_regist = (EditText) findViewById(R.id.regist_email);
        userName_regist = (EditText) findViewById(R.id.regist_username_editText);
        passWord_regist = (EditText) findViewById(R.id.password_editText);
        confirm_regist = (EditText) findViewById(R.id.confirm_editText);
        bt_regist = (Button) findViewById(R.id.regist_button);

        ima_Regist = (ImageButton) findViewById(R.id.Regist_backup);
        ima_Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(LoginActivity.class);
            }
        });
    }
        public void startActivity(Class<?> cls){
            Intent intent=new Intent(RegistePage.this,cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }



    }

