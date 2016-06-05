package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyViewPager  extends Activity{
    public boolean isFirst;
    private boolean canJump=false;
    private boolean flag;
    private int curPosition;
    private ViewPager mViewPager;
    private SharedPreferences.Editor mEditor;
    private int []ImgIds={R.drawable.coton,R.drawable.tower,R.drawable.color,R.drawable.pink};
    private List<ImageView>mImages=new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_viewpager);

        SharedPreferences pre = getSharedPreferences("State", MODE_PRIVATE);
        flag = pre.getBoolean("App_state", false);

        if(flag!=true) {
            SharedPreferences sharedPreferences = getSharedPreferences("State", Context.MODE_PRIVATE);
            mEditor = sharedPreferences.edit();
            mEditor.putBoolean("App_state", false);
            mEditor.putBoolean("isFirst", false);
            mEditor.commit();
        }


            //Toast.makeText(MyViewPager.this, "flag:"+flag, Toast.LENGTH_LONG).show();
        if (flag != true) {
            mViewPager = (ViewPager) findViewById(R.id.viewPager);
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                    if (canJump && positionOffset == 0 && positionOffsetPixels == 0) {
                        Intent intent = new Intent(MyViewPager.this, WelcomePage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        canJump = false;
                        isFirst=true;
                       // Toast.makeText(MyViewPager.this, "isFirst"+isFirst, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    curPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (curPosition == ImgIds.length - 1) {
                        canJump = true;
                    } else {
                        canJump = false;
                    }
                }
            });
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());

            mViewPager.setAdapter(new PagerAdapter() {
                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    ImageView imageView = new ImageView(MyViewPager.this);
                    imageView.setImageResource(ImgIds[position]);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    container.addView(imageView);
                    mImages.add(imageView);
                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView(mImages.get(position));
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public int getCount() {
                    return ImgIds.length;
                }
            });

        }else {
            Intent intent = new Intent(MyViewPager.this, WelcomePage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

}

