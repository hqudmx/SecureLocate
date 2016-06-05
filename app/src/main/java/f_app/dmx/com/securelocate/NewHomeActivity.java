package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Administrator on 2016/6/4.
 */

public class NewHomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_newhome);
    }
}