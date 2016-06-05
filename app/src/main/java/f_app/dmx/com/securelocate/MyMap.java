package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyMap extends Activity {
    private MapView mMapView;

    private AMap amap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_map);

        mMapView=(MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        //amap=mMapView.getMap();
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
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }
}
