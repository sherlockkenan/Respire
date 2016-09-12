package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.respireapp.Utils.ClickUtils;
import com.example.respireapp.helper.Sortdistance;
import com.example.respireapp.helper.Sortgeneral;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.mapapi.SDKInitializer;
import com.example.respireapp.Entity.LocationUtils;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.Entity.Place;
import com.example.respireapp.Entity.Scenery;
import com.example.respireapp.R;
import com.example.respireapp.helper.Sortenvironment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piglet on 2016/7/14.
 */
public class























RecommendActivity extends Activity {
    public final static String SER_KEY = "com.andy.ser";
    protected static final int REFRESH = 0;
    private TableLayout table;
    private Handler _hRedraw;
    private String JSESSIONID;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;
    private Myapp myApp;
    private String header;
    private String searchinfo;
    private Double lat;
    private Double lon;
    private int count=0;
    private Bitmap pic;
    //private List<TableRow>row;
    private LocationClient mLocClient;
    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private InfoWindow mInfoWindow;
    private BitmapDescriptor bd=null;
    private List<Marker> mMarker=new ArrayList<>();

   private List<Place> theplaces;

    class handleData{
        public Bitmap pic;
        public int i;
        public int status;
        public handleData(Bitmap pic,int i,int status){
            this.pic=pic;
            this.i=i;
            this.status=status;
        }
    }
    private View.OnClickListener distancelistener=new View.OnClickListener(){
        public void onClick(View v){
            if (ClickUtils.isFastClick()) {
                return ;
            }
            Comparator comp = new Sortdistance();
            Collections.sort(theplaces,comp);
            _hRedraw.sendEmptyMessage(REFRESH);
        }

    };
    private View.OnClickListener environmentlistener=new View.OnClickListener(){
        public void onClick(View v){
            if (ClickUtils.isFastClick()) {
                return ;
            }
            Comparator comp = new Sortenvironment();
            Collections.sort(theplaces,comp);
            _hRedraw.sendEmptyMessage(REFRESH);
        }

    };
    private View.OnClickListener generallistener=new View.OnClickListener(){
        public void onClick(View v){
            if (ClickUtils.isFastClick()) {
                return ;
            }
            for(int i=0;i<theplaces.size();++i){
                double general=theplaces.get(i).getDistance()*0.5+theplaces.get(i).getPm25()*0.5;
                theplaces.get(i).setGeneral(general);
            }
            Comparator comp = new Sortgeneral();
            Collections.sort(theplaces,comp);
            _hRedraw.sendEmptyMessage(REFRESH);
        }

    };

    private Handler pic_hdl;
    private View.OnClickListener searchclicklistener=new View.OnClickListener(){
        public void onClick(View v){
            if (ClickUtils.isFastClick()) {
                return ;
            }
            table = (TableLayout) findViewById(R.id.recommendTable);
            table.removeAllViews();
            searchinfo=((EditText)findViewById(R.id.searchtext)).getText().toString();
            JSONObject object=new JSONObject();
            try {
                object.put("latitude",lat);
                object.put("longitude",lon);
                object.put("search",searchinfo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            theplaces=new ArrayList<Place>();
            String url=header+"/recommand/search";
            mQueue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                    object, new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject result){
                    try {
                        String flag = result.getString("return_type");

                        JSONArray places=result.getJSONArray("data");
                        for(int i=0;i<places.length();++i){
                            JSONObject tmp=(JSONObject)places.get(i);
                            Place place=new Place();
                            place.setAddress(tmp.getString("address"));
                            if(i==0){
                                place.setPm25(9);
                            }
                           else if(i==1){
                                place.setPm25(6);
                            }
                            else if(i==2){
                                place.setPm25(5);
                            }
                           else  if(i==3){
                                place.setPm25(4);
                            }
                            else if(i==4){
                                place.setPm25(3);
                            }
                            else if(i==5){
                                place.setPm25(2);
                            }
                            else if(i==6){
                                place.setPm25(1);
                            }
                            else if(i==7){
                                place.setPm25(10);
                            }
                            else{
                                place.setPm25(tmp.getInt("pm25"));
                            }
                            place.setCo2(tmp.getInt("co2"));
                            place.setSo2(tmp.getInt("so2"));

                            place.setLatitude(tmp.getDouble("latitude"));
                            place.setLongitude(tmp.getDouble("longitude"));

                            place.setDistance(tmp.getDouble("distance"));
                            place.setName(tmp.getString("name"));
                            JSONArray s=(JSONArray)tmp.getJSONArray("sceneries");
                            if(tmp.getJSONArray("sceneries").length()==0){
                                place.setStatus(-1);
                                place.setTurl("http://www.yumaoyi.com/images/defaultImg/default_productBig.jpg");
                            }
                            else{

                                place.setStatus(0);
                                JSONObject t=(JSONObject)tmp.getJSONArray("sceneries").get(0);
                                String[] tmpurl=t.getString("photo").split(";");
                                place.setTurl(tmpurl[0]);
                                List<Scenery>scenery=new ArrayList<Scenery>();
                                for(int j=0;j<s.length();++j){
                                    JSONObject json=(JSONObject) tmp.getJSONArray("sceneries").get(j);
                                    Scenery ts=new Scenery();
                                    ts.setPhoto(json.getString("photo"));
                                    ts.setDescription(json.getString("description"));
                                    ts.setCo2(json.getInt("co2"));
                                    ts.setPm25(json.getInt("so2"));
                                    ts.setSo2(json.getInt("so2"));
                                    ts.setTime(json.getString("time"));
                                    ts.setUsername(json.getString("username"));
                                    scenery.add(ts);
                                }
                                place.setSceneries(scenery);
                            }
                            theplaces.add(place);
                        }
                        pic_hdl = new PicHandler();
                        Thread t = new LoadPicThread();
                        t.start();

                        //画地图
                        addmaker();

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
           mQueue.add(request);

        }

    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//是程序不崩溃的作用
        setContentView(R.layout.activity_recommend);
        //row=new ArrayList<TableRow>();
        // 获取LocationClient
        mLocClient = new LocationClient(this);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        mLocClient.setLocOption(option);

        // 获取BaiduMap
        mMapView = (MapView) findViewById(R.id.rmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        //定位
        MyLocationListener mListener = new MyLocationListener();
        mLocClient.registerLocationListener(mListener);
        mLocClient.start();
        bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
        //设计点击事件
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            public boolean onMarkerClick(final Marker marker) {
                Button button = new Button(getApplicationContext());
                button.setBackgroundResource(R.drawable.popup);
                InfoWindow.OnInfoWindowClickListener listener = null;
                for (int j = 0; j < mMarker.size(); j++) {
                    if (marker == mMarker.get(j)) {
                        button.setText(theplaces.get(j).getName());
                        button.setTextColor(0xFF0000FF);
                    }
                }
                LatLng ll = marker.getPosition();
                mInfoWindow = new InfoWindow(button, ll, -47);
                mBaiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        } );
        Button backButton=(Button) findViewById(R.id.rbackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendActivity.this.finish();
            }
        });
        LocationUtils.initLocation(this.getApplication());
        lat=LocationUtils.latitude;
        lon=LocationUtils.longitude;
//        lat=31.024355;
//        lon=121.437866;
        myApp=(Myapp)getApplication();
        JSESSIONID=myApp.getSessionid();
        header=myApp.getUrl();
        Button searchclick=(Button)findViewById(R.id.searchclick);
        searchclick.setOnClickListener(searchclicklistener);
        _hRedraw=new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                switch (msg.what) {
                    case REFRESH:
                        redrawEverything();
                        break;
                }
            }
        };
        Button sortbydistance=(Button)findViewById(R.id.distance);
        sortbydistance.setOnClickListener(distancelistener);
        Button sortbyenvironment=(Button)findViewById(R.id.environment);
        sortbyenvironment.setOnClickListener(environmentlistener);
        Button sortbygeneral=(Button)findViewById(R.id.general);
        sortbygeneral.setOnClickListener(generallistener);

    }
    private void redrawEverything()
    {
      table.removeAllViews();
        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();
        //table.refreshDrawableState();
    }
    class LoadPicThread extends Thread{
        @Override
        public void run(){
            for (int i=0;i<theplaces.size();i++) {
                count=i;
                if (theplaces.get(i).getStatus()==0) {
                    String tmp1=myApp.getUrl() + theplaces.get(i).getTurl();
                    pic = getUrlImage(myApp.getUrl() + theplaces.get(i).getTurl());
                }
                else{
                    String tmp2=theplaces.get(i).getTurl();
                    pic=getUrlImage(theplaces.get(i).getTurl());
                }
                int status=theplaces.get(i).getStatus();

                handleData info=new handleData(pic,i,status);
                Message msg = pic_hdl.obtainMessage();
                msg.what = 0;
                msg.obj = info;
                pic_hdl.sendMessage(msg);
            }
        }
    }
    //加载图片
    public Bitmap getUrlImage(String url) {
        Bitmap img = null;
        try {
            URL picurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection)picurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            img = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
    class PicHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            handleData info=(handleData)msg.obj;

            Bitmap imgs=info.pic;
            final int i=info.i;
            final int status=info.status;

             table = (TableLayout) findViewById(R.id.recommendTable);
            TableRow tablerow = new TableRow(getBaseContext());
            ImageView img=new ImageView(getBaseContext());
            TextView information=new TextView(getBaseContext());
            //img.setImageResource(R.drawable.discoveryback1);
            img.setImageBitmap(imgs);
            img.setAdjustViewBounds(true);
            img.setMaxWidth(250);
            img.setMinimumWidth(250);
            img.setMinimumHeight(250);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(status==0) {
                        Intent logIntent = new Intent();
                        logIntent.setClass(RecommendActivity.this, SceneryInfoActivity.class);
                        logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Gson gson = new Gson();
                        String result = gson.toJson(theplaces.get(i));
                        Bundle bundle = new Bundle();
                        bundle.putString("place", result);
                        logIntent.putExtras(bundle);
                        startActivity(logIntent);
                    }
                    else{
                        Intent logIntent = new Intent();
                        logIntent.setClass(RecommendActivity.this,NoPicAcitivity.class);
                        logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Gson gson = new Gson();
                        String result = gson.toJson(theplaces.get(i));
                        Bundle bundle = new Bundle();
                        bundle.putString("place", result);
                        logIntent.putExtras(bundle);
                        startActivity(logIntent);
                    }
                }
            });
            tablerow.addView(img, 0);
            String text=theplaces.get(i).getName()+'\n'+" "+"pm25:"+theplaces.get(i).getPm25()+'\n'+"距离:"+theplaces.get(i).getDistance();
            information.setText(text);
            //information.setTextColor(0xffffff);
            information.setTextSize(20);
            tablerow.addView(information, 1);
           //row.add(tablerow);
            table.addView(tablerow);
            //ImageView image=(ImageView)findViewById(R.id.thephoto);
            //image.setImageBitmap(myimg);
        }
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
            lat = location.getLatitude();
            lon = location.getLongitude();
            if (true) {

                // 开始移动百度地图的定位地点到中心位置
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16.0f);
                mBaiduMap.animateMapStatus(u);
            }
        }
    }

    public void addmaker() {
        // add marker overlay
        mBaiduMap.clear();
        mMarker.clear();

        for(int i=0;i<theplaces.size();i++) {
            LatLng ll= new LatLng(theplaces.get(i).getLatitude(), theplaces.get(i).getLongitude());
            MarkerOptions ooA = new MarkerOptions().position(ll).icon(bd)
                    .zIndex(9).draggable(true);
            mMarker.add((Marker) (mBaiduMap.addOverlay(ooA)));
        }
    }
    @Override
    protected void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mMapView.onDestroy();
        super.onDestroy();
        // 回收 bitmap 资源
        bd.recycle();

    }
}
