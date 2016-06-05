package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyMap extends Activity {
    private MapView mMapView;
    private Spinner mSpinner;
    private AMap amap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_map);
        mSpinner=(Spinner) findViewById(R.id.spinner1);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinnerMode);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        mSpinner.setAdapter(_Adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                switch (position){
                                                    case 0:
                                                        amap.setMapType(AMap.MAP_TYPE_NORMAL);
                                                        break;
                                                    case 1:
                                                        amap.setMapType(AMap.MAP_TYPE_NIGHT);
                                                        break;
                                                    case 2:
                                                        amap.setMapType(AMap.MAP_TYPE_SATELLITE);
                                                        break;
                                                }
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });
        mMapView=(MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        amap=mMapView.getMap();
        //amap.setMapType();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    /*@Override*/
   /* protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }*/
}
