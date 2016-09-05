package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodayActivity extends Activity {
    MapView mMapView;
    BaiduMap mBaiduMap;
    List<LatLng> points = new ArrayList<LatLng>();
    Polyline mPolyline;

    String JSESSIONID;
    private static final Object TAG = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//是程序不崩溃的作用
        setContentView(R.layout.activity_today);
        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        // 初始化地图
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodayActivity.this.finish();
            }
        });

        final Button timeButton=(Button) findViewById(R.id.timeButton);
        final Button mapButton=(Button) findViewById(R.id.mapButton);

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.pm25Text).setVisibility(View.VISIBLE);
                findViewById(R.id.co2Text).setVisibility(View.VISIBLE);
                findViewById(R.id.so2Text).setVisibility(View.VISIBLE);
                findViewById(R.id.chart1).setVisibility(View.VISIBLE);
                findViewById(R.id.chart2).setVisibility(View.VISIBLE);
                findViewById(R.id.chart3).setVisibility(View.VISIBLE);
                findViewById(R.id.divider1).setVisibility(View.VISIBLE);
                findViewById(R.id.divider2).setVisibility(View.VISIBLE);
                timeButton.setBackgroundResource(R.drawable.todaybutton1);
                mapButton.setBackgroundResource(R.drawable.todaybutton0);
                findViewById(R.id.bmapView).setVisibility(View.GONE);

//                int[] daypm25={200,350,100,400,600,700,800,560,150,366,364,235,643,668,345,357,476,256,473,854,356,357,356,754};
//                int[] dayco2={366,364,235,643,668,345,357,476,256,473,854,356,357,356,754,200,350,100,400,600,700,800,560,150};
//                int[] dayso2={345,357,476,256,473,854,356,357,356,754,200,350,100,400,600,700,800,560,150,366,364,235,643,668};

                Myapp myApp=(Myapp)getApplication();
                String header=myApp.getUrl();
                String url=header+"/history/getday";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");

                            int[] daypm25 = new int[24];
                            int[] dayco2=new int[24];
                            int[] dayso2=new int[24];
                            for (int i=0;i<24;i++){
                                daypm25[i]=0;
                                dayco2[i]=0;
                                dayso2[i]=0;
                            }
                            int j = 0;
                            for (int i = data.length() - 1; i >= 0; i--) {
                                JSONObject obj = data.getJSONObject(i);
                                j=obj.getInt("time");
                                daypm25[j] = obj.getInt("pm25");
                                dayco2[j] = obj.getInt("co2");
                                dayso2[j] = obj.getInt("so2");
                            }
                            LinearLayout layout1=(LinearLayout) findViewById(R.id.chart1);
                            layout1.removeAllViews();
                            layout1.addView(getLineChartView(getApplicationContext(),daypm25,Color.parseColor("#8F676390"),1500),
                                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT));

                            LinearLayout layout2=(LinearLayout) findViewById(R.id.chart2);
                            layout2.removeAllViews();
                            layout2.addView(getLineChartView(getApplicationContext(),dayco2,Color.parseColor("#8F477A7B"),1500),
                                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT));

                            LinearLayout layout3=(LinearLayout) findViewById(R.id.chart3);
                            layout3.removeAllViews();
                            layout3.addView(getLineChartView(getApplicationContext(),dayso2,Color.parseColor("#8F45426D"),1000),
                                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT));
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                    }
                })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        super.getHeaders();
                        Map<String,String> headers=new HashMap<String,String>();
                        headers.put("Cookie","JSESSIONID="+JSESSIONID);
                        return headers;
                    }
                };
                request.setTag(TAG);
                requestQueue.add(request);
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.pm25Text).setVisibility(View.GONE);
                findViewById(R.id.co2Text).setVisibility(View.GONE);
                findViewById(R.id.so2Text).setVisibility(View.GONE);
                findViewById(R.id.chart1).setVisibility(View.GONE);
                findViewById(R.id.chart2).setVisibility(View.GONE);
                findViewById(R.id.chart3).setVisibility(View.GONE);
                findViewById(R.id.divider1).setVisibility(View.GONE);
                findViewById(R.id.divider2).setVisibility(View.GONE);
                timeButton.setBackgroundResource(R.drawable.todaybutton0);
                mapButton.setBackgroundResource(R.drawable.todaybutton1);
                findViewById(R.id.bmapView).setVisibility(View.VISIBLE);

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
        });

        timeButton.performClick();
    }

    public View getLineChartView(Context context, int[] temperatureLists,int color,int high) {
        // 构造显示用渲染图
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        // 曲线图的格式，包括颜色，值的范围，点和线的形状等等
        renderer.setBackgroundColor(Color.parseColor("#00ffffff"));// 背景色，灰白色
        renderer.setApplyBackgroundColor(false);// 设置背景颜色生效

        renderer.setMarginsColor(Color.parseColor("#00ffffff"));// // 边框外侧颜色
        renderer.setPanEnabled(false, false);// 设置X轴和Y轴是否可以滑动
        renderer.setXAxisMin(0);// 设置X轴起点
        renderer.setXAxisMax(24);// 设置X轴最大点
        renderer.setYAxisMin(0);// 设置Y轴起点
        renderer.setXLabels(0);// 设置X轴不显示数字（改用我们手动添加的文字标签）
        renderer.setZoomEnabled(false, false);// 设置不可放大缩小

        renderer.setShowGrid(true); // 设置网格显示
        renderer.setGridColor(Color.parseColor("#eeeeee"));
        renderer.setPointSize(5f);// 每个坐标点的大小
        Paint.Align align = renderer.getYAxisAlign(0);
        renderer.setYLabelsAlign(align);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setXLabelsColor(Color.GRAY);// 设置轴标签颜色
        renderer.setYLabels(0);
        renderer.setShowAxes(false);
        renderer.setYAxisMax(high);// 设置Y轴最大点
        renderer.setAxesColor(Color.BLACK);// 设置XY轴颜色
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        int j = 0;
        for (int i = 0; i < 24; i++) {
            renderer.addTextLabel(j, j + "");
            j++;
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        // 保存点集数据 ，包括每条曲线的X，Y坐标
        XYSeries series = new XYSeries("温度");
        for (int i = 0; i < temperatureLists.length; i++) {
            series.add(Double.valueOf(i + ""), Double.valueOf(temperatureLists[i]));
        }
        dataset.addSeries(series);

        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
        xyRenderer.setColor(Color.GRAY);// 深蓝色
        xyRenderer.setLineWidth(2f);// 设置线的宽度
        xyRenderer.setDisplayChartValues(true);// 在柱子顶端显示数值
        xyRenderer.setChartValuesTextSize(18f);
        xyRenderer.setDisplayChartValuesDistance(30);
        xyRenderer.setPointStyle(PointStyle.POINT);// 设置点的样式
        xyRenderer.setFillBelowLine(true);
        xyRenderer.setFillBelowLineColor(color);
        xyRenderer.setFillPoints(true);

        renderer.setShowLegend(false);
        renderer.addSeriesRenderer(xyRenderer);

        return ChartFactory.getCubeLineChartView(context,dataset,renderer,0.33f);
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
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/map/getalldata";
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
