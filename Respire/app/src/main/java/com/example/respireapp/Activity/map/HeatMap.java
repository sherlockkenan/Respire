package com.example.respireapp.Activity.map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.*;
import com.baidu.mapapi.model.LatLng;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by keke on 2016/7/6.
 */
public class HeatMap extends Activity {
    private LocationClient mLocClient;
    private BaiduMap mBaiduMap;
    private Button loc;
    MapView mMapView;
    //当前位置经纬度
    private double latitude;
    private double longitude;
    //是否首次定位
    private boolean isFirstLoc = true;
    private com.baidu.mapapi.map.HeatMap heatmap;
    private Button pm25;
    private Button co2;
    private Button so2;

    //获得的数据
    List<WeightedLatLng> pm25_list = new ArrayList<WeightedLatLng>();
    List<WeightedLatLng> so2_list = new ArrayList<WeightedLatLng>();
    List<WeightedLatLng> co2_list = new ArrayList<WeightedLatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//是程序不崩溃的作用
        setContentView(R.layout.activity_heatmap);

        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeatMap.this.finish();
            }
        });

        // 获取LocationClient
        mLocClient = new LocationClient(this);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        mLocClient.setLocOption(option);

        // 获取BaiduMap
        mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();

        loc=(Button) findViewById(R.id.button);
        pm25 = (Button) findViewById(R.id.pm25);
        co2 = (Button) findViewById(R.id.co2);
        so2 = (Button) findViewById(R.id.so2);
        pm25.setEnabled(false);
        so2.setEnabled(false);
        co2.setEnabled(false);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  ToMyLocation();
            }
        });
        pm25.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                heatmap.removeHeatMap();
                addHeatMap("pm25");
                pm25.setEnabled(false);
                so2.setEnabled(true);
                co2.setEnabled(true);
            }
        });
        so2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                heatmap.removeHeatMap();
                addHeatMap("so2");
                pm25.setEnabled(true);
                so2.setEnabled(false);
                co2.setEnabled(true);
            }
        });
        co2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                heatmap.removeHeatMap();
                addHeatMap("co2");
                co2.setEnabled(false);
                so2.setEnabled(true);
                pm25.setEnabled(true);
            }
        });

        //获取数据
        Send_request();

        // 显示出当前位置的小图标
        mBaiduMap.setMyLocationEnabled(true);

        MyLocationListener mListener = new MyLocationListener();
        mLocClient.registerLocationListener(mListener);
        mLocClient.start();

    }

    private void addHeatMap(String type) {
        final Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mBaiduMap.addHeatMap(heatmap);
            }
        };
        final String name=type;
        new Thread() {
            @Override
            public void run() {
                super.run();
                if(name=="pm25")
                    heatmap = new com.baidu.mapapi.map.HeatMap.Builder().weightedData(pm25_list).build();
                if(name=="so2")
                    heatmap = new com.baidu.mapapi.map.HeatMap.Builder().weightedData(so2_list).build();
                if(name=="co2")
                    heatmap = new com.baidu.mapapi.map.HeatMap.Builder().weightedData(co2_list).build();
                h.sendEmptyMessage(0);
            }
        }.start();
    }

    //发送请求
    public void  Send_request(){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/map/getalldata";
        //String url="http://192.168.16.130:8000/map/getalldata";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    JSONArray returnvalue = result.getJSONArray("data");
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        double lat=temp.getDouble("latitude");
                        double lon=temp.getDouble("longitude");
                        double value_pm25 =temp.getDouble("pm25");
                        double value_so2 =temp.getDouble("so2");
                        double value_co2 =temp.getDouble("co2");
                        pm25_list.add(new WeightedLatLng(new LatLng(lat,lon),value_pm25));
                        co2_list.add(new WeightedLatLng(new LatLng(lat,lon),value_co2));
                        so2_list.add(new WeightedLatLng(new LatLng(lat,lon),value_so2));

                    }

                    pm25.setEnabled(true);
                    so2.setEnabled(true);
                    co2.setEnabled(true);

                    addHeatMap("pm25");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // 只是完成了定位
            MyLocationData locData = new MyLocationData.Builder().latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            //设置图标在地图上的位置
            mBaiduMap.setMyLocationData(locData);
            //获取经纬度
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            if (isFirstLoc) {
                isFirstLoc = false;
                // 开始移动百度地图的定位地点到中心位置
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16.0f);
                mBaiduMap.animateMapStatus(u);
            }
        }
    }

    /**
     * 定位并添加标注
     */
    private void ToMyLocation() {
        //定义坐标点
        LatLng point = new LatLng(latitude, longitude);
        // 开始移动百度地图的定位地点到中心位置
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(point, 16.0f);
        mBaiduMap.animateMapStatus(u);
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}
