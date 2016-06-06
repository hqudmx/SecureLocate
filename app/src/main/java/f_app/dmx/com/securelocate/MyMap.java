package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.poisearch.PoiSearch;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyMap extends Activity {
    private MapView mMapView;
    private Spinner mSpinner;
    private AMap amap;
    private AutoCompleteTextView mEditText;
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        mEditText= (AutoCompleteTextView) findViewById(R.id.id_search);
        mBt= (Button) findViewById(R.id.bt_search);
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        amap = mMapView.getMap();

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=mEditText.getText().toString();
                search(s);
            }
        });

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

    public String getCity(String s){
        PoiSearch.Query  query = new PoiSearch.Query(s,"");
        String str=query.getCity();
        return str;
    }

    public void search(String s){
       String s1= getCity(s);
        // keyWord表示搜索字符串，
        //cityCode表示POI搜索区域的编码，是必须设置参数
        PoiSearch.Query  query = new PoiSearch.Query(s, s1);
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        PoiSearch poiSearch = new PoiSearch(this,query);//初始化poiSearch对象
       // poiSearch.setOnPoiSearchListener(this);//设置回调数据的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

}
