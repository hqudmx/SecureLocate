package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;

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
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        amap = mMapView.getMap();
        CircleOptions circleOptions=new CircleOptions();
        circleOptions.center(new LatLng(116.570718,39.804313));
        circleOptions.radius(500);
        circleOptions.fillColor(Color.GREEN);
        amap.addCircle(circleOptions);

        double radius=circleOptions.getRadius();
        LatLng Position=circleOptions.getCenter();
        double x=Position.latitude;
        double y=Position.longitude;
        Toast.makeText(MyMap.this, ""+radius+","+x+","+y, Toast.LENGTH_LONG).show();//返回经度90.0？？？？？？？？？

        setadapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void setadapter() {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinnerMode);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        mSpinner.setAdapter(_Adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
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

    }
}
