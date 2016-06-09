package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.textservice.SuggestionsInfo;
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
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/6/5.
 */
public class MyMap extends Activity implements PoiSearch.OnPoiSearchListener {
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

        mEditText = (AutoCompleteTextView) findViewById(R.id.id_search);
        mBt = (Button) findViewById(R.id.bt_search);
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        amap = mMapView.getMap();

      /*  MarkerOptions markerOptions=new MarkerOptions();
       // markerOptions.position(new LatLng(39.907901,116.398527));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.leadto));
        Marker marker= amap.addMarker(markerOptions);
        marker.setPositionByPixels((int)24.603767, (int)118.084561);//设置中心点*/
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEditText.getText().toString();
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

    public String getCity(String s) {
        PoiSearch.Query query = new PoiSearch.Query(s, "");
        String str = query.getCity();
        return str;
    }

    public void search(String s) {
        String s1 = getCity(s);
        // keyWord表示搜索字符串，
        //cityCode表示POI搜索区域的编码，是必须设置参数
        PoiSearch.Query query = new PoiSearch.Query(s, s1);
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        PoiSearch poiSearch = new PoiSearch(this, query);//初始化poiSearch对象
        poiSearch.setOnPoiSearchListener(this);//设置回调数据的监听器
        poiSearch.searchPOIAsyn();//开始搜索
    }

    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null) {
                Toast.makeText(MyMap.this, "查询成功" , Toast.LENGTH_SHORT).show();
                ArrayList<PoiItem> items = result.getPois();


            } else {
                Toast.makeText(MyMap.this, "查询为空" , Toast.LENGTH_SHORT).show();
                List<String> keywords = result.getSearchSuggestionKeywords();
                List<SuggestionCity> suggsetionCities = result.getSearchSuggestionCitys();
            }
        } else {
            Toast.makeText(MyMap.this, "查询失败" + rCode, Toast.LENGTH_SHORT).show();
        }
        //可以在回调中解析result，获取POI信息
        //result.getPois()可以获取到PoiItem列表，Poi详细信息可参考PoiItem类
        //若当前城市查询不到所需Poi信息，可以通过result.getSearchSuggestionCitys()获取当前Poi搜索的建议城市
        //如果搜索关键字明显为误输入，则可通过result.getSearchSuggestionKeywords()方法得到搜索关键词建议
        //返回结果成功或者失败的响应码。1000为成功，其他为失败（详细信息参见网站开发指南-错误码对照表）
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


}
