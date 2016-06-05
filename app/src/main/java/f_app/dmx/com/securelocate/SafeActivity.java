package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;


public class SafeActivity extends Activity {
    private GridView gridView_safe;
    private ImageButton ima_safe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_safe);
        ima_safe=(ImageButton)findViewById(R.id.Safe_backup);
        gridView_safe=(GridView)findViewById(R.id.gv_safe);

        gridView_safe.setAdapter(new SafeAdapter(SafeActivity.this));

        ima_safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyHomePage.class);
            }
        });


        gridView_safe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://mall.cnki.net/magazine/magalist/JZAQ.htm"));
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.docin.com/search.do?searchcat=2&searchType_banner=p&nkey=%E5%BB%BA%E7%AD%91%E5%B7%A5%E5%9C%B0%E7%AE%A1%E7%90%86%E5%88%B6%E5%BA%A6&qq-pf-to=pcqq.c2c"));
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lawtime.cn/zt/list_laodong.html"));
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3= new Intent(Intent.ACTION_VIEW, Uri.parse("http://baike.sogou.com/v66048418.htm?fromTitle=%E5%BB%BA%E7%AD%91%E5%B7%A5%E7%A8%8B%E6%96%BD%E5%B7%A5%E8%B4%A8%E9%87%8F%E9%AA%8C%E6%94%B6%E7%BB%9F%E4%B8%80%E6%A0%87%E5%87%86"));
                        startActivity(intent3);
                        break;

                }
            }
        });
    }
        public void startActivity(Class<?> cls){
            Intent intent=new Intent(SafeActivity.this,cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

    }
}
