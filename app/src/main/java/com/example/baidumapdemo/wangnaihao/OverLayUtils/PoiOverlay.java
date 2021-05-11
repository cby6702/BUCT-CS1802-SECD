package com.example.baidumapdemo.wangnaihao.OverLayUtils;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.baidumapdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于显示poi的overly
 */
public class PoiOverlay extends OverlayManager  {

    private static final int MAX_POI_SIZE = 10;

    private PoiResult mPoiResult = null;

    private Marker marker;
    private double jingdu;
    private double weidu;

    /**
     * 构造函数
     * 
     * @param baiduMap   该 PoiOverlay 引用的 BaiduMap 对象
     */
    public PoiOverlay(BaiduMap baiduMap) {
        super(baiduMap);
    }

    /**
     * 设置POI数据
     * 
     * @param poiResult    设置POI数据
     */
    public void setData(PoiResult poiResult,double jingdu,double weidu) {
        this.mPoiResult = poiResult;
        this.jingdu = jingdu;
        this.weidu = weidu;
    }
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


    @Override
    public final List<OverlayOptions> getOverlayOptions() {
        if (mPoiResult == null || mPoiResult.getAllPoi() == null) {
            return null;
        }

        List<OverlayOptions> markerList = new ArrayList<>();
        Map<PoiInfo,Integer> poi_distance = new HashMap<>();
        int markerSize = 0;

        for (int i = 0; i < mPoiResult.getAllPoi().size() && markerSize < MAX_POI_SIZE; i++) {
            if (mPoiResult.getAllPoi().get(i).location == null) {
                continue;
            }


            //创建坐标点
            LatLng ll = new LatLng(jingdu, weidu);
            int distance = (int) DistanceUtil.getDistance(ll, mPoiResult.getAllPoi().get(i).location);
            mPoiResult.getAllPoi().get(i).setDistance(distance);
            poi_distance.put(mPoiResult.getAllPoi().get(i), distance);
            poi_distance = sortAscend(poi_distance);
        }
            for (PoiInfo poiInfo : poi_distance.keySet()) {
                markerSize++;
                //String distance = String.valueOf(DistanceUtil.getDistance(ll, mPoiResult.getAllPoi().get(i).location));
                //int distance = (int) DistanceUtil.getDistance(ll, mPoiResult.getAllPoi().get(i).location);
                Bundle bundle = new Bundle();
                //bundle.putInt("index", i);
                bundle.putString("name", poiInfo.getName());
                bundle.putString("province",poiInfo.province);
                bundle.putString("address",poiInfo.address);
                bundle.putInt("distance",poiInfo.getDistance());
                OverlayOptions options = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                        .extraInfo(bundle)
                        .position(poiInfo.location)
                        .alpha(1f)
                        .animateType(MarkerOptions.MarkerAnimateType.grow);
                mBaiduMap.addOverlay(options);
                markerList.add(options);
            }


            /*markerSize++;
            //String distance = String.valueOf(DistanceUtil.getDistance(ll, mPoiResult.getAllPoi().get(i).location));
            //int distance = (int) DistanceUtil.getDistance(ll, mPoiResult.getAllPoi().get(i).location);
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            bundle.putString("name", mPoiResult.getAllPoi().get(i).getName());
            bundle.putString("province",mPoiResult.getAllPoi().get(i).province);
            bundle.putString("address",mPoiResult.getAllPoi().get(i).address);
            bundle.putInt("distance",distance);
            OverlayOptions options = new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromAssetWithDpi("Icon_mark" + markerSize + ".png"))
                    .extraInfo(bundle)
                    .position(mPoiResult.getAllPoi().get(i).location)
                    .alpha(1f)
                    .animateType(MarkerOptions.MarkerAnimateType.grow);
            mBaiduMap.addOverlay(options);
            markerList.add(options);*/
        return markerList;
        }

    /**
     * 获取该PoiOverlay的poi数据
     * 
     * @return     POI数据
     */
    public PoiResult getPoiResult() {
        return mPoiResult;
    }

    /**
     * 覆写此方法以改变默认点击行为
     * 
     * @param i    被点击的poi在
     *             {@link com.baidu.mapapi.search.poi.PoiResult#getAllPoi()} 中的索引
     * @return     true--事件已经处理，false--事件未处理
     */
    public boolean onPoiClick(int i) {
        if (mPoiResult.getAllPoi() != null
                && mPoiResult.getAllPoi().get(i) != null) {
            Toast.makeText(BMapManager.getContext(), mPoiResult.getAllPoi().get(i).name, Toast.LENGTH_LONG)
                   .show();
        }
        return false;
    }

    @Override
    public final boolean onMarkerClick(Marker marker) {
        if (!mOverlayList.contains(marker)) {
            return false;
        }

        if (marker.getExtraInfo() != null) {
            return onPoiClick(marker.getExtraInfo().getInt("index"));
        }

        return false;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }
}
