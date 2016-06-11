package f_app.dmx.com.securelocate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.textservice.SuggestionsInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.NaviPara;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
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
public class MyMap extends Activity implements AMap.OnMarkerClickListener, AMap.InfoWindowAdapter, TextWatcher,
        PoiSearch.OnPoiSearchListener, OnClickListener, Inputtips.InputtipsListener {

    SharedPreferences.Editor mEditor;
    private MapView mMapView;
    private Spinner mSpinner;
    private AMap amap;
    private Button mBt;
    private AMap aMap;
    private AutoCompleteTextView mEditText;// 输入搜索关键字
    private String keyWord = "";// 要输入的poi搜索关键字
    private ProgressDialog progDialog = null;// 搜索时进度条
   // private EditText mEditText;// 要输入的城市名字或者城市区号
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);

        SharedPreferences sharedPreferences = getSharedPreferences("State", Context.MODE_PRIVATE);
        mEditor = sharedPreferences.edit();
        mEditor.putBoolean("App_state", true);
      //  mEditor.putBoolean("isFirst", false);
        mEditor.commit();

        //mEditText = (AutoCompleteTextView) findViewById(R.id.id_search);
       // mBt = (Button) findViewById(R.id.bt_search);
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mSpinner = (Spinner) findViewById(R.id.spinner1);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        amap = mMapView.getMap();
        setUpMap();

      /*  MarkerOptions markerOptions=new MarkerOptions();
       // markerOptions.position(new LatLng(39.907901,116.398527));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.leadto));
        Marker marker= amap.addMarker(markerOptions);
        marker.setPositionByPixels((int)24.603767, (int)118.084561);//设置中心点*/
  /*      mBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEditText.getText().toString();
                //search(s);
            }
        });
*/
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



    /* public String getCity(String s) {
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
                if (result != null&&result.getQuery()!=null) {
                    if(result.getQuery().equals(query))
                    Toast.makeText(MyMap.this, "查询成功", Toast.LENGTH_SHORT).show();
                    ArrayList<PoiItem> items = result.getPois();


                } else {
                    Toast.makeText(MyMap.this, "查询为空", Toast.LENGTH_SHORT).show();
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
    */


    public void startActivity(Class cla) {
        Intent intent = new Intent(MyMap.this, cla);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void Click(View v) {
        switch (v.getId()) {
            case R.id.img_humiture:
                startActivity(Humiture.class);
                finish();
                break;
            case R.id.tv_humiture:
                startActivity(Humiture.class);
                finish();
                break;
            case R.id.image_location:
                startActivity(AlarmActivity.class);
                finish();
                break;
            case R.id.tv_alarm:
                startActivity(AlarmActivity.class);
                finish();
                break;
            case R.id.image_setting:
                startActivity(SettingActivity.class);
                finish();
                break;
            case R.id.tv_set:
                startActivity(SettingActivity.class);
                finish();
                break;
        }
    }
    private void setUpMap() {
        Button searButton = (Button) findViewById(R.id.bt_search);
        searButton.setOnClickListener(this);
        mEditText = (AutoCompleteTextView) findViewById(R.id.id_search);
        mEditText.addTextChangedListener(this);// 添加文本输入框监听事件
       // mEditText = (EditText) findViewById(R.id.city);
       // aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
       // aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件
    }

    /**
     * 点击搜索按钮
     */
    public void searchButton() {
        keyWord = mEditText.getText().toString();
      //  Toast.makeText(MyMap.this, "启动成功"+keyWord, Toast.LENGTH_SHORT).show();

        if ("".equals(keyWord)) {

            Toast.makeText(MyMap.this, "请输入关键字", Toast.LENGTH_SHORT).show();
            return;
        } else {
            doSearchQuery();
        }
    }


    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(false);
        progDialog.setMessage("正在搜索:\n" + keyWord);
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        showProgressDialog();// 显示进度框
        currentPage = 0;
        query = new PoiSearch.Query(keyWord, "", mEditText.getText().toString());// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());

        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        ImageButton button = (ImageButton) view
                .findViewById(R.id.start_amap_app);
        // 调起高德地图app
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAMapNavi(marker);
            }
        });
        return view;
    }

    /**
     * 调起高德地图导航功能，如果没安装高德地图，会进入异常，可以在异常中处理，调起高德地图app的下载页面
     */
    public void startAMapNavi(Marker marker) {
        // 构造导航参数
        NaviPara naviPara = new NaviPara();
        // 设置终点位置
        naviPara.setTargetPoint(marker.getPosition());
        // 设置导航策略，这里是避免拥堵
        naviPara.setNaviStyle(NaviPara.DRIVING_AVOID_CONGESTION);

        // 调起高德地图导航
        try {
            AMapUtils.openAMapNavi(naviPara, getApplicationContext());
        } catch (AMapException e) {

            // 如果没安装会进入异常，调起下载页面
            AMapUtils.getLatestAMapApp(getApplicationContext());

        }

    }

    /**
     * 判断高德地图app是否已经安装
     */
    public boolean getAppIn() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.getPackageManager().getPackageInfo(
                    "com.autonavi.minimap", 0);
        } catch (PackageManager.NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        // 本手机没有安装高德地图app
        if (packageInfo != null) {
            return true;
        }
        // 本手机成功安装有高德地图app
        else {
            return false;
        }
    }

    /**
     * 获取当前app的应用名字
     */
    public String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager
                .getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * poi没有搜索到数据，返回一些推荐城市的信息
     */
    private void showSuggestCity(List<SuggestionCity> cities) {
        String infomation = "推荐城市\n";
        for (int i = 0; i < cities.size(); i++) {
            infomation += "城市名称:" + cities.get(i).getCityName() + "城市区号:"
                    + cities.get(i).getCityCode() + "城市编码:"
                    + cities.get(i).getAdCode() + "\n";
        }
        Toast.makeText(MyMap.this, ""+infomation, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String newText = s.toString().trim();
        if (newText!=null) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, mEditText.getText().toString());
            Inputtips inputTips = new Inputtips(MyMap.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }
    }


    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dissmissProgressDialog();// 隐藏对话框
        if (rCode == 1000) {
           // Toast.makeText(MyMap.this, "查询成功"+rCode, Toast.LENGTH_SHORT).show();
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                  //  Toast.makeText(MyMap.this, "查询成功"+poiResult, Toast.LENGTH_SHORT).show();
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息

                    if (poiItems != null && poiItems.size() > 0) {
                        Toast.makeText(MyMap.this, "查询成功"+poiItems, Toast.LENGTH_LONG).show();
                        aMap.clear();// 清理之前的图标
                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);

                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                    } else if (suggestionCities != null
                            && suggestionCities.size() > 0) {
                        showSuggestCity(suggestionCities);
                    } else {
                        Toast.makeText(MyMap.this, "无返回结果", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(MyMap.this, "无返回结果", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MyMap.this, "查询错误："+rCode, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPoiItemSearched(PoiItem item, int rCode) {
        // TODO Auto-generated method stub

    }

    /**
     * Button点击事件回调方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 点击搜索按钮
             */
            case R.id.bt_search:
                searchButton();
                break;
            /**
             * 点击下一页按钮
             */

            default:
                break;
        }
    }



    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        if (rCode == 1000) {// 正确返回
         //   Toast.makeText(MyMap.this, "查询成功", Toast.LENGTH_SHORT).show();
            List<String> listString = new ArrayList<String>();
            for (int i = 0; i < tipList.size(); i++) {
                listString.add(tipList.get(i).getName());
            }
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.route_inputs, listString);
            mEditText.setAdapter(aAdapter);
            aAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(MyMap.this, "查询错误："+rCode, Toast.LENGTH_SHORT).show();
        }

    }
}
