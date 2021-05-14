package com.example.baidumapdemo.wangnaihao.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiFilter;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.baidumapdemo.R;
import com.example.baidumapdemo.wangnaihao.DeitalActivity.Detail_activity;
import com.example.baidumapdemo.wangnaihao.OverLayUtils.OverlayManager;
import com.example.baidumapdemo.wangnaihao.OverLayUtils.PoiOverlay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import static android.os.Parcel.obtain;
import static java.io.FileDescriptor.in;

public class MainActivity extends AppCompatActivity implements OnGetPoiSearchResultListener{

    //注册EditText组件
    private EditText keyword;
    private EditText distance;
    private EditText lat;
    private EditText lon;
    //注册button
    private Button b1;
    private Button b2;
    //EditText 的内容：
    //百度地图组件
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //经纬度
    private double jingdu;
    private double weidu;
    // 定位相关:
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private boolean isFirstLoc = true;
    private LocationClient mLocationClient;
    //Marker覆盖物:
    private Marker marker;
    private Marker mMarkerA;
    private InfoWindow mInfoWindow;
    //POI:
    private PoiSearch mPoiSearch = null;
    //当前位置坐标
    private  static  double Latitude;
    private  static  double Longitude;
    //适配器相关
    private List<Map<String,Object>> infos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须放在所有代码执行前
        //SDKInitializer.initialize(getApplicationContext());
        //init_chuxingchangjing();
        setContentView(R.layout.activity_main);
        //初始化按钮和搜索框
        init_ButtonandEditText();
        //初始化地图
        init_map();
        //定位到我
        locate_me();
        //使用标记
        use_marker();
        //poi
        use_poi();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();

    }

    /*
    以下是poi重写方法
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
    /*
    poi重写方法结束
    */

    //自定义类:

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            Log.e("看看精度", String.valueOf(location.getLatitude()));
            jingdu = location.getLatitude();
            Log.e("看看维度", String.valueOf(location.getLongitude()));
            weidu = location.getLongitude();
            mBaiduMap.setMyLocationData(locData);


            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
    /*
    Mark函数
     */
    public void initOverlay() {
        // add marker overlay
        //经纬度坐标
        LatLng llA = new LatLng(40.25995352497919,116.15726048919863);
        // 初始化  bitmap 信息，不用时及时 recycle
        BitmapDescriptor bda = BitmapDescriptorFactory
                .fromResource(R.drawable.zuobiao);

        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bda)
                .zIndex(9).draggable(true);

        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);

        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
            }

            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(
                        MainActivity.this,
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", "
                                + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();

                lat.setText(marker.getPosition().latitude+"");
                lon.setText(marker.getPosition().longitude+"");
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }

    //初始化button以及edittext
    public void init_ButtonandEditText(){
         /*
            ----------------------------------------------------------------------
            搜索组件：
            注：两个组件的具体健壮性逻辑暂时没有完成
            ----------------------------------------------------------------------
         */
        keyword =findViewById(R.id.Edit1);
        distance = findViewById(R.id.Edit2);
        lat =findViewById(R.id.Edit3);
        lon =findViewById(R.id.Edit4);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword.equals("")||distance.equals("")){
                    Toast.makeText(MainActivity.this,"不可以为空",Toast.LENGTH_SHORT);

                }else{
                    BDLocation location = new BDLocation();
                    /**
                     *  PoiCiySearchOption 设置检索属性
                     *  city 检索城市
                     *  keyword 检索内容关键字
                     *  pageNum 分页页码
                     */
       /* mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city("北京") //必填
                .keyword("宿舍") //必填
                .pageNum(10));*/

                    /**
                     * 以dingwei 为中心，搜索半径**米以内的**
                     */

                    mPoiSearch.searchNearby(new PoiNearbySearchOption()
                            .location(new LatLng(Double.parseDouble(lat.getText().toString()), Double.parseDouble(lon.getText().toString())))
                            .radius(Integer.parseInt(MainActivity.this.distance.getText().toString()))
                            //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
                            .keyword(keyword.getText().toString().trim())
                            .scope(2)
                            .pageNum(0));
                    Log.e("看看", String.valueOf(location.getLatitude()));
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaiduMap.clear();
                initOverlay();
            }
        });

         /*
        ----------------------------------------------------
            本部分结束
        -----------------------------------------------------
         */
    }

    //初始化地图组件
    public void init_map(){
                /*
        -----------------------------------------------------
        地图的显示：
            1.这部分是地图的显示
            2.到时可能要动态申请权限，现默认为手动打开使用
            3.判断用户登录状态暂时没有处理
        -----------------------------------------------------
        */
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();


         /*
        ----------------------------------------------------
            本部分结束
        -----------------------------------------------------
         */
    }

    //定位信息
    public void locate_me(){
        /*
        -----------------------------------------------------
         定位信息：
            1.现在实现的功能是定位到用户信息
        -----------------------------------------------------
        */
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(mCurrentMode, true, null));
        mLocationClient = new LocationClient(this);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);


        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListenner myLocationListener = new MyLocationListenner();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
        /*
        ----------------------------------------------------
            本部分结束
        -----------------------------------------------------
         */
    }

    //marker覆盖物的使用
    public void use_marker(){
        /**
         -----------------------------------------------------
         Marker覆盖物的使用：
         1.
         -----------------------------------------------------
         */

        initOverlay();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
                final Bundle extraInfo = marker.getExtraInfo();
                //Toast.makeText(MainActivity.this,
                //               extraInfo.getString("province")+":"+extraInfo.getString("name"),
                //               Toast.LENGTH_SHORT).show();
                //用来构造InfoWindow
                //用来构造InfoWindow的view
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.detail, null);
                TextView text = (TextView)view.findViewById(R.id.detail_text1);
                text.setText(extraInfo.getString("province")
                            +":"+extraInfo.getString("name")
                            +"\r\n位置:"+extraInfo.getString("address")
                            +"\r\n距离当前位置："
                            +extraInfo.getInt("distance")+"m");
                text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"nihao",Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("name",extraInfo.getString("name"));
                        bundle.putString("distance",extraInfo.getInt("distance")+"m");
                        Intent intent = new Intent(getApplicationContext(),Detail_activity.class);
                        mBaiduMap.hideInfoWindow();
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                final InfoWindow infoWindow = new InfoWindow(view, marker.getPosition(), -47);
                mBaiduMap.showInfoWindow(infoWindow);
                return true;
            }
        });
        //poi回调
        /*mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                                            //地图单击事件回调方法
                                            @Override
                                            public void onMapClick(LatLng latLng) {
                                                Toast.makeText(MainActivity.this,"维度"+latLng.latitude+"精度"+latLng.longitude,Toast.LENGTH_SHORT).show();
                                                Log.e("TAG", "点击到地图上了！纬度" + latLng.latitude + "经度" + latLng.longitude);
                                            }

                                            //Poi 单击事件回调方法，比如点击到地图上面的商店，公交车站，地铁站等等公共场所
                                            @Override
                                            public void onMapPoiClick(MapPoi mapPoi) {

                                                Toast.makeText(MainActivity.this,"点击到poi"+mapPoi.getName(),Toast.LENGTH_SHORT).show();

                                                Log.e("TAG", "点击到地图上的POI物体了！名称：" + mapPoi.getName() + ",Uid:" + mapPoi.getUid());

                                            }
                                        }

        );*/
        /*
        ----------------------------------------------------
            本部分结束
        -----------------------------------------------------
         */
    }

    //poi
    public void use_poi(){
        /**
         -----------------------------------------------------
         附近POI的搜索：
         1.
         -----------------------------------------------------
         */
        // 创建poi检索实例，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        //创建POI检索监听器
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult result) {
                if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {

                    //mBaiduMap.clear();

                    Toast.makeText(MainActivity.this, "未找到结果", Toast.LENGTH_LONG).show();
                    return;
                }
                List<PoiInfo> PoiInfo = result.getAllPoi();
                for (com.baidu.mapapi.search.core.PoiInfo poiInfo : PoiInfo) {
                    Log.e("poi",poiInfo.name);
                }//打印结果 看看问题
                //创建List对象
                infos = addtoList(PoiInfo);
                use_adapter();

                //创建PoiOverlay对象
                PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);

                //设置Poi检索数据
                poiOverlay.setData(result,jingdu,weidu);

                //将poiOverlay添加至地图并缩放至合适级别
                poiOverlay.addToMap();
                poiOverlay.zoomToSpan();

            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        //设置检索监听器
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        /*
        ----------------------------------------------------
            本部分结束
        -----------------------------------------------------
         */
    }

    //出行模式 高强度定位
    public void init_chuxingchangjing(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setLocationNotify(true);
        option.setScanSpan(3000);
        option.setNeedNewVersionRgc(true);

        option.setIsNeedAddress(true);
        option.setIsNeedLocationPoiList(false);
        option.setIsNeedAltitude(true);
        option.setIsNeedLocationDescribe(false);
        option.setWifiCacheTimeOut(1000);
    }
    //添加List数据
    public List<Map<String,Object>> addtoList(List<PoiInfo> PoiInfo){
        List<Map<String,Object>> info = new ArrayList<>();//创建适配器数据List
        Map<PoiInfo,Integer> map = new HashMap<>();
        LatLng latLng = new LatLng(jingdu,weidu);
        for (PoiInfo poiInfo:PoiInfo) {
            int distance = (int) DistanceUtil.getDistance(latLng, poiInfo.location);
            map.put(poiInfo,distance);
            poiInfo.setDistance(distance);
        }
        Map<com.baidu.mapapi.search.core.PoiInfo, Integer> Aftersort_map = sortAscend(map);
        for (com.baidu.mapapi.search.core.PoiInfo poiInfo : Aftersort_map.keySet()) {
            Map<String,Object> mapinfo = new HashMap<>();
            mapinfo.put("name","名称:"+poiInfo.name);
            mapinfo.put("address","地址:"+poiInfo.address);
            mapinfo.put("distance","距离:"+poiInfo.getDistance()+"m");
            info.add(mapinfo);
        }


        return info;
    }

    //设置adapter
    public void use_adapter(){
        SimpleAdapter adapter=new SimpleAdapter
                (this,infos,R.layout.mapinfos_list,
                        new String[]{"name","address","distance"},new int[]{R.id.name,R.id.address,R.id.distance});
        // 创建SimpleAdapter
        ListView listView = findViewById(R.id.maplist);
        listView.setAdapter(adapter); // 将适配器与ListView关联
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = ( Map<String, Object> )parent.getItemAtPosition(position);//获取选择项的值
                Toast.makeText(getApplicationContext(),map.get("distance").toString(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("name",map.get("name").toString());
                bundle.putString("distance", map.get("distance").toString());
                Intent intent = new Intent(getApplicationContext(),Detail_activity.class);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    //排序map
    // Map的value值升序排序
    public static <K, V extends Comparable<? super V>> Map<K, V> sortAscend(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<K, V> returnMap = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            returnMap.put(entry.getKey(), entry.getValue());
        }
        return returnMap;
    }

}
