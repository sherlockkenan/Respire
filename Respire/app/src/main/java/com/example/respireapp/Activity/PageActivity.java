package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.avoscloud.leanchatlib_demo.ConversationListActivity;
import com.example.respireapp.Activity.map.HeatMap;
import com.example.respireapp.Entity.LocationUtils;
import com.example.respireapp.Entity.MyBluetooth;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;
import com.example.respireapp.Service.RecommandService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;

public class PageActivity extends Activity {
    private ViewPager viewPager;//页卡内容
    private ImageView imageView;// 动画图片
    private TextView textView1,textView2,textView3,textView4;
    private List<View> views;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private View view1,view2,view3,view4;//各个页卡

    public LinearLayout layout1;
    public LinearLayout layout2;
    public LinearLayout layout3;
    double[] weekpm25=new double[]{100,200,300,250,150,400,350};
    double[] weekco2=new double[]{100,200,300,250,150,400,350};
    double[] weekso2=new double[]{100,200,300,250,150,400,350};
    int[]weeklabel={1,2,3,4,5,6,7};
    String[]weektext={"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
    double[]monthpm25=new double[]{100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,250,350,150};
    double[]monthco2=new double[]{100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,250,350,150};
    double[]monthso2=new double[]{100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,100,200,300,250,150,400,350,250,350,150};
    int[]monthlabel={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    String[]monthtext={"1","","","","5","","","","","10","","","","","15","","","","","20","","","","","25","","","","","30"};
    double[]yearpm25=new double[]{100,200,300,250,150,400,350,100,200,300,250,150};
    double[]yearco2=new double[]{100,200,300,250,150,400,350,100,200,300,250,150};
    double[]yearso2=new double[]{100,200,300,250,150,400,350,100,200,300,250,150};
    int[]yearlabel={1,2,3,4,5,6,7,8,9,10,11,12};
    String[]yeartext={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String pm25average="300";
    String co2average="100";
    String so2average="50";

    String JSESSIONID;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;

    private MyBluetooth myBluetooth;
    int nowpm25=0;
    int nowco2=0;
    int nowso2=0;

    private Boolean blueStatus=false;
    private DataHandler dataHandler;
    TextView dataText;
    TextView co2Text;
    TextView so2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        myBluetooth=new MyBluetooth();

        InitImageView();
        InitTextView();
        InitViewPager();
    }

    /**
     *  初始化头标
     */

    private void InitTextView() {
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        textView3 = (TextView) findViewById(R.id.text3);
        textView4 = (TextView) findViewById(R.id.text4);

        textView1.setOnClickListener(new MyOnClickListener(0));
        textView2.setOnClickListener(new MyOnClickListener(1));
        textView3.setOnClickListener(new MyOnClickListener(2));
        textView4.setOnClickListener(new MyOnClickListener(3));
    }

    /**
     2      * 初始化动画，这个就是页卡滑动时，下面的横线也滑动的效果，在这里需要计算一些数据
     3 */

    private void InitImageView() {
        imageView= (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.active).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 4 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
    }

    private void InitViewPager() {
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        views=new ArrayList<View>();
        LayoutInflater inflater=getLayoutInflater();
        view1=inflater.inflate(R.layout.activity_home, null);
        dataText=(TextView) view1.findViewById(R.id.pm25Text);
        co2Text=(TextView) view1.findViewById(R.id.co2Text);
        so2Text=(TextView) view1.findViewById(R.id.so2Text);
        view1.findViewById(R.id.todayButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,TodayActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Switch blue=(Switch) view1.findViewById(R.id.bluetoothSwitch);
        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    String res=myBluetooth.connect();
                    Toast.makeText(PageActivity.this, res, Toast.LENGTH_SHORT).show();
                    if (res.equals("Success")){
                        blueStatus=true;
                    }
                } else {
                    String res=myBluetooth.disconnect();
                    Toast.makeText(PageActivity.this, res, Toast.LENGTH_SHORT).show();
                    if (res.equals("Success")){
                        blueStatus=false;
                    }
                }
            }
        });

        view1.findViewById(R.id.refreshButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int times=3;
                String[] result=new String[times+1];
                result=myBluetooth.getLines(times);
                double sum1 = 0;
                double sum2=0;
                double sum3=0;
                for (int i = 1; i <= times; i++) {
                    String[] now=result[i].split("\t");
                    double num1=Double.parseDouble(now[0]);
                    int pos= now[1].indexOf("p");
                    String str2=now[1].substring(0,pos);
                    double num2=Double.parseDouble(str2);
                    int pos2=now[2].indexOf("p");
                    String str3=now[2].substring(0,pos2);
                    double num3=Double.parseDouble(str3);
                    sum1 += num1;
                    sum2+=num2;
                    sum3+=num3;
                }
                sum1 = sum1 / times;
                sum2=sum2/times;
                sum3=sum3/times;
                nowpm25 = (int) sum1;
                nowco2=(int) sum2;
                nowso2=(int) sum3;

                dataText.setText(Integer.toString(nowpm25));
                co2Text.setText(Integer.toString(nowco2));
                so2Text.setText(Integer.toString(nowso2));

                if (nowpm25>500){
                    Toast.makeText(PageActivity.this, "空气污染十分严重！", Toast.LENGTH_SHORT).show();
                }

                if (nowpm25>0){
                    senddata(nowpm25,nowco2,nowso2);
                }
//                Intent logIntent1 = new Intent();
//                logIntent1.setClass(PageActivity.this,TestNotificationActivity.class);
//                startActivity(logIntent1);
            }
        });

        view2=inflater.inflate(R.layout.activity_history, null);

        layout1=(LinearLayout) view2.findViewById(R.id.chart1);
        layout2=(LinearLayout) view2.findViewById(R.id.chart2);
        layout3=(LinearLayout) view2.findViewById(R.id.chart3);
        view2.findViewById(R.id.weekButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapp myApp=(Myapp)getApplication();
                String header=myApp.getUrl();
                String url=header+"/history/getweek";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");

                                weekpm25 = new double[7];
                                weekco2 = new double[7];
                                weekso2 = new double[7];
                                weektext = new String[7];
                                int j = 0;
                                for (int i = data.length() - 1; i >= 0; i--) {
                                    JSONObject obj = data.getJSONObject(i);
                                    weekpm25[j] = obj.getInt("pm25");
                                    weekco2[j] = obj.getInt("co2");
                                    weekso2[j] = obj.getInt("so2");
                                    weektext[j] = obj.getString("time");
                                    j++;
                                }
                                int pm25 = 0;
                                int co2 = 0;
                                int so2 = 0;
                                for (int i = 0; i < data.length(); i++) {
                                    pm25 += weekpm25[i];
                                    co2 += weekco2[i];
                                    so2 += weekso2[i];
                                }
                                if (data.length()>0) {
                                    pm25 = pm25 / data.length();
                                    co2 = co2 / data.length();
                                    so2 = so2 / data.length();
                                }
                                pm25average = Integer.toString(pm25);
                                co2average = Integer.toString(co2);
                                so2average = Integer.toString(so2);

                                Button weekButton = (Button) view2.findViewById(R.id.weekButton);
                                weekButton.setTextColor(Color.BLACK);
                                Button monthButton = (Button) view2.findViewById(R.id.monthButton);
                                monthButton.setTextColor(Color.WHITE);
                                Button yearButton = (Button) view2.findViewById(R.id.yearButton);
                                yearButton.setTextColor(Color.WHITE);
                                weekButton.setBackgroundResource(R.drawable.historybutton1);
                                monthButton.setBackgroundResource(R.drawable.historybutton0);
                                yearButton.setBackgroundResource(R.drawable.historybutton0);
                                TextView pm25text = (TextView) view2.findViewById(R.id.pm25Average);
                                pm25text.setText("Average:" + pm25average);
                                TextView co2text = (TextView) view2.findViewById(R.id.co2Average);
                                co2text.setText("Average:" + co2average);
                                TextView so2text = (TextView) view2.findViewById(R.id.so2Average);
                                so2text.setText("Average:" + so2average);

                                ArrayList<double[]> value = new ArrayList<double[]>();
                                value.add(weekpm25);
                                int[] colors = {Color.WHITE};
                                double[] border = {1, 7, 0, 1000};
                                int width = 20;
                                initView(value, colors, layout1, border, weeklabel, width, weektext);

                                ArrayList<double[]> value2 = new ArrayList<double[]>();
                                value2.add(weekco2);
                                int[] colors2 = {Color.LTGRAY};
                                double[] border2 = {1, 7, 0, 1000};
                                int width2 = 20;
                                initView(value2, colors2, layout2, border2, weeklabel, width2, weektext);

                                ArrayList<double[]> value3 = new ArrayList<double[]>();
                                value3.add(weekso2);
                                int[] colors3 = {Color.WHITE};
                                double[] border3 = {1, 7, 0, 1000};
                                int width3 = 20;
                                initView(value3, colors3, layout3, border3, weeklabel, width3, weektext);

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

        view2.findViewById(R.id.monthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapp myApp=(Myapp)getApplication();
                String header=myApp.getUrl();
                String url=header+"/history/getmonth";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");
                            if (data!=null) {
                                monthpm25 = new double[31];
                                monthco2 = new double[31];
                                monthso2 = new double[31];
                                int j = 0;
                                for (int i = data.length() - 1; i >= 0; i--) {
                                    JSONObject obj = data.getJSONObject(i);
                                    monthpm25[j] = obj.getInt("pm25");
                                    monthco2[j] = obj.getInt("co2");
                                    monthso2[j] = obj.getInt("so2");
                                    j++;
                                }
                                int pm25 = 0;
                                int co2 = 0;
                                int so2 = 0;
                                for (int i = 0; i < data.length(); i++) {
                                    pm25 += monthpm25[i];
                                    co2 += monthco2[i];
                                    so2 += monthso2[i];
                                }
                                if (data.length()>0) {
                                    pm25 = pm25 / data.length();
                                    co2 = co2 / data.length();
                                    so2 = so2 / data.length();
                                }
                                pm25average = Integer.toString(pm25);
                                co2average = Integer.toString(co2);
                                so2average = Integer.toString(so2);

                                Button weekButton = (Button) view2.findViewById(R.id.weekButton);
                                weekButton.setTextColor(Color.WHITE);
                                Button monthButton = (Button) view2.findViewById(R.id.monthButton);
                                monthButton.setTextColor(Color.BLACK);
                                Button yearButton = (Button) view2.findViewById(R.id.yearButton);
                                yearButton.setTextColor(Color.WHITE);
                                weekButton.setBackgroundResource(R.drawable.historybutton0);
                                monthButton.setBackgroundResource(R.drawable.historybutton1);
                                yearButton.setBackgroundResource(R.drawable.historybutton0);
                                TextView pm25text = (TextView) view2.findViewById(R.id.pm25Average);
                                pm25text.setText("Average:" + pm25average);
                                TextView co2text = (TextView) view2.findViewById(R.id.co2Average);
                                co2text.setText("Average:" + co2average);
                                TextView so2text = (TextView) view2.findViewById(R.id.so2Average);
                                so2text.setText("Average:" + so2average);
                                ArrayList<double[]> value = new ArrayList<double[]>();
                                value.add(monthpm25);
                                int[] colors = {Color.WHITE};
                                double[] border = {1, 31, 0, 1000};
                                int width = 8;
                                initView(value, colors, layout1, border, monthlabel, width, monthtext);

                                ArrayList<double[]> value2 = new ArrayList<double[]>();
                                value2.add(monthco2);
                                int[] colors2 = {Color.LTGRAY};
                                double[] border2 = {1, 31, 0, 1000};
                                int width2 = 8;
                                initView(value2, colors2, layout2, border2, monthlabel, width2, monthtext);

                                ArrayList<double[]> value3 = new ArrayList<double[]>();
                                value3.add(monthso2);
                                int[] colors3 = {Color.WHITE};
                                double[] border3 = {1, 31, 0, 1000};
                                int width3 = 8;
                                initView(value3, colors3, layout3, border3, monthlabel, width3, monthtext);
                            }
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

        view2.findViewById(R.id.yearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Myapp myApp=(Myapp)getApplication();
                String header=myApp.getUrl();
                String url=header+"/history/getyear";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");
                            if (data!=null) {
                                yearpm25 = new double[12];
                                yearco2 = new double[12];
                                yearso2 = new double[12];
                                int j = 0;
                                for (int i = data.length() - 1; i >= 0; i--) {
                                    JSONObject obj = data.getJSONObject(i);
                                    yearpm25[j] = obj.getInt("pm25");
                                    yearco2[j] = obj.getInt("co2");
                                    yearso2[j] = obj.getInt("so2");
                                    j++;
                                }
                                int pm25 = 0;
                                int co2 = 0;
                                int so2 = 0;
                                for (int i = 0; i < data.length(); i++) {
                                    pm25 += yearpm25[i];
                                    co2 += yearco2[i];
                                    so2 += yearso2[i];
                                }
                                if (data.length()>0) {
                                    pm25 = pm25 / data.length();
                                    co2 = co2 / data.length();
                                    so2 = so2 / data.length();
                                }
                                pm25average = Integer.toString(pm25);
                                co2average = Integer.toString(co2);
                                so2average = Integer.toString(so2);

                                Button weekButton = (Button) view2.findViewById(R.id.weekButton);
                                weekButton.setTextColor(Color.WHITE);
                                Button monthButton = (Button) view2.findViewById(R.id.monthButton);
                                monthButton.setTextColor(Color.WHITE);
                                Button yearButton = (Button) view2.findViewById(R.id.yearButton);
                                yearButton.setTextColor(Color.BLACK);
                                weekButton.setBackgroundResource(R.drawable.historybutton0);
                                monthButton.setBackgroundResource(R.drawable.historybutton0);
                                yearButton.setBackgroundResource(R.drawable.historybutton1);
                                TextView pm25text = (TextView) view2.findViewById(R.id.pm25Average);
                                pm25text.setText("Average:" + pm25average);
                                TextView co2text = (TextView) view2.findViewById(R.id.co2Average);
                                co2text.setText("Average:" + co2average);
                                TextView so2text = (TextView) view2.findViewById(R.id.so2Average);
                                so2text.setText("Average:" + so2average);
                                ArrayList<double[]> value = new ArrayList<double[]>();
                                value.add(yearpm25);
                                int[] colors = {Color.WHITE};
                                double[] border = {1, 12, 0, 1000};
                                int width = 20;
                                initView(value, colors, layout1, border, yearlabel, width, yeartext);

                                ArrayList<double[]> value2 = new ArrayList<double[]>();
                                value2.add(yearco2);
                                int[] colors2 = {Color.LTGRAY};
                                double[] border2 = {1, 12, 0, 1000};
                                int width2 = 20;
                                initView(value2, colors2, layout2, border2, yearlabel, width2, yeartext);

                                ArrayList<double[]> value3 = new ArrayList<double[]>();
                                value3.add(yearso2);
                                int[] colors3 = {Color.WHITE};
                                double[] border3 = {1, 12, 0, 1000};
                                int width3 = 20;
                                initView(value3, colors3, layout3, border3, yearlabel, width3, yeartext);
                            }
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

        view2.findViewById(R.id.weekButton).performClick();

        view3=inflater.inflate(R.layout.activity_discovery, null);

        view3.findViewById(R.id.poundButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,PoolActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });
        view3.findViewById(R.id.diagramButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,HeatMap.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });
        view3.findViewById(R.id.routeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,ItineraryActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putInt("nowpm25",nowpm25);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });
        view3.findViewById(R.id.fitnessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,RecommendActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putInt("nowpm25",nowpm25);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        view4=inflater.inflate(R.layout.activity_me, null);
        view4.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PageActivity.this,ProfileActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getprofile";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONObject data=result.getJSONObject("data");
                    if (flag.equals("success")&&data!=null){
                        final String name=data.getString("username");
                        TextView username=(TextView)view4.findViewById(R.id.nameText);
                        username.setText(name);

                        view4.findViewById(R.id.newsButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent logIntent = new Intent();
                                logIntent.setClass(PageActivity.this,ConversationListActivity.class);
                                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                Bundle tbundle=new Bundle();
                                tbundle.putString("sessionid",JSESSIONID);
                                tbundle.putString("username",name);
                                logIntent.putExtras(tbundle);
                                startActivity(logIntent);
                            }
                        });


                    }
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

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  //若不设置这个，标题下边的线不会随着页卡滑动而滑动

        dataHandler=new DataHandler();
        Thread timeThread = new TimeThread();
        timeThread.start();

        Intent intent=new Intent(PageActivity.this, RecommandService.class);
        startService(intent);
    }

    class TimeThread extends Thread{
        @Override
        public void run(){
            while (true) {
                //if (blueStatus) {
                int times = 3;
                String[] result = new String[times + 1];
                result = myBluetooth.getLines(times);
                if (result[0].equals("Success")){
                    double sum1 = 0;
                    double sum2=0;
                    double sum3=0;
                    for (int i = 1; i <= times; i++) {
                        String[] now=result[i].split("\t");
                        double num1=Double.parseDouble(now[0]);
                        int pos= now[1].indexOf("p");
                        String str2=now[1].substring(0,pos);
                        double num2=Double.parseDouble(str2);
                        int pos2=now[2].indexOf("p");
                        String str3=now[2].substring(0,pos2);
                        double num3=Double.parseDouble(str3);
                        sum1 += num1;
                        sum2+=num2;
                        sum3+=num3;
                    }
                    sum1 = sum1 / times;
                    sum2=sum2/times;
                    sum3=sum3/times;
                    nowpm25 = (int) sum1;
                    nowco2=(int) sum2;
                    nowso2=(int) sum3;

                    int[] data={nowpm25,nowco2,nowso2};

                    Message msg = dataHandler.obtainMessage();
                    msg.what = 0;
                    msg.obj = data;
                    dataHandler.sendMessage(msg);
                    if (nowpm25 > 0) {
                        senddata(nowpm25,nowco2,nowso2);
                    }
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class DataHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int[] data=(int[]) msg.obj;
            int pm25=data[0];
            int co2=data[1];
            int so2=data[2];
            dataText.setText(Integer.toString(pm25));
            co2Text.setText(Integer.toString(co2));
            so2Text.setText(Integer.toString(so2));
        }
    }

    /**
     *
     * 头标点击监听 3 */
    private class MyOnClickListener implements OnClickListener{
        private int index=0;
        public MyOnClickListener(int i){
            index=i;
        }
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }

    }

    public class MyViewPagerAdapter extends PagerAdapter{
        private List<View> mListViews;
        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(mListViews.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }
        @Override
        public int getCount() {
            return  mListViews.size();
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

    public class MyOnPageChangeListener implements OnPageChangeListener{
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量
        public void onPageScrollStateChanged(int arg0) {  //arg0 ==1的时辰默示正在滑动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做，就是停在那。
        }
        public void onPageScrolled(int arg0, float arg1, int arg2) {  //默示在前一个页面滑动到后一个页面的时辰，在前一个页面滑动前调用的办法。
        }
        public void onPageSelected(int arg0) {  //arg0是默示你当前选中的页面，这事务是在你页面跳转完毕的时辰调用的。
            Animation animation = new TranslateAnimation(one*currIndex, one*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imageView.startAnimation(animation);
            //Toast.makeText(PageActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
        }
    }



    private void initView(ArrayList<double[]>value, int[]colors, LinearLayout layout, double[]border, int[]xlabel, int width, String[]textLabel) {
        //柱状图的两个序列的名字
        String[] titles = new String[] { "1" };
        //为li1添加柱状图
        layout.removeAllViews();
        layout.addView(
                xychar(titles, value, colors,xlabel.length , 0, border, xlabel, "", true,width,textLabel),
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public GraphicalView xychar(String[] titles, ArrayList<double[]> value,
                                int[] colors, int x, int y, double[] range, int []xLable , String xtitle, boolean f, int width, String[]textLabel) {
        //多个渲染
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        //多个序列的数据集
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        //构建数据集以及渲染
        for (int i = 0; i < titles.length; i++) {
            XYSeries series = new XYSeries(titles[i]);
            double [] yLable= value.get(i);
            for (int j=0;j<yLable.length;j++) {
                series.add(xLable[j],yLable[j]);
            }
            dataset.addSeries(series);
            XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
            // 设置颜色
            xyRenderer.setColor(colors[i]);
            renderer.addSeriesRenderer(xyRenderer);
        }
        //设置x轴标签数
        renderer.setXLabels(0);
        for (int i=0;i<textLabel.length;i++){
            renderer.addXTextLabel(xLable[i],textLabel[i]);
        }
        //设置Y轴标签数
        renderer.setYLabels(y);
        //设置x轴的最大值
        renderer.setXAxisMax(x - 0.5);
        //设置轴的颜色
        renderer.setAxesColor(Color.BLACK);
        //设置x轴和y轴的标签对齐方式
        renderer.setXLabelsAlign(Paint.Align.CENTER);
        renderer.setYLabelsAlign(Paint.Align.CENTER);
        // 设置现实网格
        renderer.setShowGrid(false);
        renderer.setShowLegend(false);
        renderer.setShowAxes(false);
        // 设置条形图之间的距离
        renderer.setBarSpacing(0.2);
        renderer.setBarWidth(width);
        renderer.setInScroll(false);
        renderer.setPanEnabled(false, false);
        renderer.setClickEnabled(false);
        //设置x轴和y轴标签的颜色
        renderer.setXLabelsColor(Color.GRAY);
        renderer.setYLabelsColor(0,Color.GRAY);
        int length = renderer.getSeriesRendererCount();
        //设置图标的标题
        //renderer.setChartTitle(xtitle);
        renderer.setLabelsColor(Color.RED);
        renderer.setLabelsTextSize(20);
        //设置图例的字体大小
        renderer.setLegendTextSize(18);
        //设置x轴和y轴的最大最小值
        renderer.setRange(range);
        renderer.setMarginsColor(0x00888888);
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer ssr = renderer.getSeriesRendererAt(i);
            ssr.setChartValuesTextAlign(Paint.Align.CENTER);
            ssr.setChartValuesTextSize(12);
            ssr.setDisplayChartValues(f);
        }
        GraphicalView mChartView = ChartFactory.getBarChartView(getApplicationContext(),
                dataset, renderer, BarChart.Type.DEFAULT);
        return mChartView;
    }

    public void senddata(int data1,int data2,int data3){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url2=header+"/postdata";
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("pm25",Integer.toString(data1));
        map.put("co2",Integer.toString(data2));
        map.put("so2",Integer.toString(data3));
        LocationUtils.initLocation(this.getApplication());
        double lat=LocationUtils.latitude;
        double lon= LocationUtils.longitude;
        map.put("latitude",Double.toString(lat));
        map.put("longitude",Double.toString(lon));
        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue2= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2,
                object, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result2){
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
        request2.setTag(TAG);
        requestQueue2.add(request2);
    }


}