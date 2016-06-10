package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

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

        mTab1= (ImageView) findViewById(R.id.image_map);
        mTab2= (ImageView) findViewById(R.id.image_location);
        mTab3= (ImageView) findViewById(R.id.img_humiture);
        mTab4= (ImageView) findViewById(R.id.image_setting);

        mTabImg=(ImageView)findViewById(R.id.img_tab_now);

        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));

        Display currDisplay=getWindowManager().getDefaultDisplay();
        int displayWidth=currDisplay.getWidth();
        int displayHeight=currDisplay.getHeight();
       /* one=displayWidth/4;
        two=one*2;
        three=one*3;*/
        one=0;
        two=0;
        three=0;

        LayoutInflater mLi=LayoutInflater.from(this);
        View view1=mLi.inflate(R.layout.activity_map,null);
        View view2=mLi.inflate(R.layout.activity_alarm,null);
        View view3=mLi.inflate(R.layout.activity_humiture,null);
        View view4=mLi.inflate(R.layout.activity_setting,null);
        final ArrayList<View> views= new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        PagerAdapter mPagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager)container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager)container).addView(views.get(position));
                return  views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);




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
                    if(currIndex==1){
                        animation=new TranslateAnimation(one,0,0,0);
                    }else  if(currIndex==2){
                        animation=new TranslateAnimation(two,0,0,0);
                    }else if(currIndex==3){
                        animation=new TranslateAnimation(three,0,0,0);
                    }
                    break;
                case 1:
                    if(currIndex==0){
                        animation=new TranslateAnimation(zero,one,0,0);
                    }else  if(currIndex==2){
                        animation=new TranslateAnimation(two,one,0,0);
                    }else if(currIndex==3){
                        animation=new TranslateAnimation(three,one,0,0);
                    }
                    break;
                case 2:
                    if(currIndex==0){
                        animation=new TranslateAnimation(zero,two,0,0);
                    }else  if(currIndex==1){
                        animation=new TranslateAnimation(one,two,0,0);
                    }else if(currIndex==3){
                        animation=new TranslateAnimation(three,two,0,0);
                    }
                    break;
                case 3:
                    if(currIndex==0){
                        animation=new TranslateAnimation(zero,three,0,0);
                    }else  if(currIndex==1){
                        animation=new TranslateAnimation(one,three,0,0);
                    }else if(currIndex==2){
                        animation=new TranslateAnimation(two,three,0,0);
                    }
                    break;

            }

            currIndex=position;
            animation.setFillAfter(true);//  true图片停在动画结束的位置
            animation.setDuration(150);
            mTabPager.startAnimation(animation);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 图标点击事件
     */
    private class MyOnClickListener implements View.OnClickListener {
        private int index=0;
        MyOnClickListener(int i){
            index=i;
        }
        @Override
        public void onClick(View v) {
            mTabPager.setCurrentItem(index);
        }
    }


}