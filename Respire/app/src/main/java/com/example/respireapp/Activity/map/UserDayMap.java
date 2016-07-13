package com.example.respireapp.Activity.map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayOptions;

import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
//import com.example.respireapp.Activity.map;
import com.example.respireapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class UserDayMap extends Activity {

    // 地图相关
    MapView mMapView;
    BaiduMap mBaiduMap;
    List<LatLng> points = new ArrayList<LatLng>();
    // UI相关

    // 普通折线,点击放大
    Polyline mPolyline;

    //发送请求的参数
    //private Myapp myApp;
    //private String JSESSIONID;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//是程序不崩溃的作用
        setContentView(R.layout.activity_userdaymap);
        // 初始化地图
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        //初始化参数
//        myApp=(Myapp)getApplication();
//        JSESSIONID=myApp.getSessionid();

        // 界面加载时添加绘制图层
        drawline();

        // 点击polyline的事件响应
        mBaiduMap.setOnPolylineClickListener(new BaiduMap.OnPolylineClickListener() {
            @Override
            public boolean onPolylineClick(Polyline polyline) {
                if (polyline == mPolyline) {
                    polyline.setWidth(20);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 线
     */
    public void drawline() {
        getdata();
//        OverlayOptions ooPolyline = new PolylineOptions().width(10)
//                .color(0xAAFF0000).points(points);
//        mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
    }

    public void  getdata(){
        String url="http://192.168.16.130:8000/map/getalldata";
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
                        points.add(new LatLng(lat,lon));
                    }
                    OverlayOptions ooPolyline = new PolylineOptions().width(10)
                            .color(0xAAFF0000).points(points);
                    mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        });
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                super.getHeaders();
//                Map<String,String> headers=new HashMap<String,String>();
//                headers.put("Cookie","JSESSIONID="+JSESSIONID);
//                return headers;
//            }
//        };
        requestQueue.add(request);
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
        mMapView.onDestroy();
        super.onDestroy();
    }

}
