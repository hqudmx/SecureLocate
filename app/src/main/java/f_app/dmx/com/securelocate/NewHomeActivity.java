package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Administrator on 2016/6/4.
 */

public class NewHomeActivity extends Activity{

    public  static NewHomeActivity instance=null;
    private ImageView mTabImg;
    private ViewPager mTabPager;
    private ImageView mTab1,mTab2,mTab3,mTab4;
    private int zero=0;
    private int currIndex=0;
    private int one;
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display=false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_newhome);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//启动activity不自动弹出软键盘

        instance=this;
        mTabPager= (ViewPager) findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Animation animation=null;
            switch (position){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}