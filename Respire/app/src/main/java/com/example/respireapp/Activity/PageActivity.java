package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.example.respireapp.R;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

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
        view2=inflater.inflate(R.layout.activity_history, null);

        layout1=(LinearLayout) view2.findViewById(R.id.chart1);
        layout2=(LinearLayout) view2.findViewById(R.id.chart2);
        layout3=(LinearLayout) view2.findViewById(R.id.chart3);
        view2.findViewById(R.id.weekButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://192.168.16.61:8000/history/getweek";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");
                            weekpm25=new double[7];
                            weekco2=new double[7];
                            weekso2=new double[7];
                            weektext=new String[7];
                            for (int i=0;i<data.length();i++){
                                JSONObject obj=data.getJSONObject(i);
                                weekpm25[6-i]=obj.getInt("pm25");
                                weekco2[6-i]=obj.getInt("co2");
                                weekso2[6-i]=obj.getInt("so2");
                                weektext[6-i]=obj.getString("time");
                            }
                            int pm25=0;
                            int co2=0;
                            int so2=0;
                            for (int i=0;i<7;i++){
                                pm25+=weekpm25[i];
                                co2+=weekco2[i];
                                so2+=weekso2[i];
                            }
                            pm25=pm25/7;
                            co2=co2/7;
                            so2=so2/7;

                            pm25average=Integer.toString(pm25);
                            co2average=Integer.toString(co2);
                            so2average=Integer.toString(so2);

                            Button weekButton=(Button) view2.findViewById(R.id.weekButton);
                            weekButton.setTextColor(Color.BLACK);
                            Button monthButton=(Button) view2.findViewById(R.id.monthButton);
                            monthButton.setTextColor(Color.WHITE);
                            Button yearButton=(Button) view2.findViewById(R.id.yearButton);
                            yearButton.setTextColor(Color.WHITE);
                            weekButton.setBackgroundResource(R.drawable.historybutton1);
                            monthButton.setBackgroundResource(R.drawable.historybutton0);
                            yearButton.setBackgroundResource(R.drawable.historybutton0);
                            TextView pm25text=(TextView)view2.findViewById(R.id.pm25Average);
                            pm25text.setText("Average:"+pm25average);
                            TextView co2text=(TextView)view2.findViewById(R.id.co2Average);
                            co2text.setText("Average:"+co2average);
                            TextView so2text=(TextView)view2.findViewById(R.id.so2Average);
                            so2text.setText("Average:"+so2average);

                            ArrayList<double[]> value=new ArrayList<double[]>();
                            value.add(weekpm25);
                            int[]colors={Color.WHITE};
                            double[] border={1,7,0,1000};
                            int width=20;
                            initView(value,colors,layout1,border,weeklabel,width,weektext);

                            ArrayList<double[]> value2=new ArrayList<double[]>();
                            value2.add(weekco2);
                            int[]colors2={Color.LTGRAY};
                            double[] border2={1,7,0,1000};
                            int width2=20;
                            initView(value2,colors2,layout2,border2,weeklabel,width2,weektext);

                            ArrayList<double[]> value3=new ArrayList<double[]>();
                            value3.add(weekpm25);
                            int[]colors3={Color.WHITE};
                            double[] border3={1,7,0,1000};
                            int width3=20;
                            initView(value3,colors3,layout3,border3,weeklabel,width3,weektext);
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
                String url="http://192.168.16.61:8000/history/getmonth";
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String flag = result.getString("return_type");
                            JSONArray data=result.getJSONArray("data");
                            weekpm25=new double[31];
                            weekco2=new double[31];
                            weekso2=new double[31];
                            weektext=new String[31];
                            int j=0;
                            for (int i=0;i<data.length();i++){
                                JSONObject obj=data.getJSONObject(i);
                                weekpm25[j]=obj.getInt("pm25");
                                weekco2[j]=obj.getInt("co2");
                                weekso2[j]=obj.getInt("so2");
                                weektext[j]=obj.getString("time");
                            }
                            int pm25=0;
                            int co2=0;
                            int so2=0;
                            for (int i=0;i<7;i++){
                                pm25+=weekpm25[i];
                                co2+=weekco2[i];
                                so2+=weekso2[i];
                            }
                            pm25=pm25/7;
                            co2=co2/7;
                            so2=so2/7;

                            pm25average=Integer.toString(pm25);
                            co2average=Integer.toString(co2);
                            so2average=Integer.toString(so2);

                            Button weekButton=(Button) view2.findViewById(R.id.weekButton);
                            weekButton.setTextColor(Color.WHITE);
                            Button monthButton=(Button) view2.findViewById(R.id.monthButton);
                            monthButton.setTextColor(Color.BLACK);
                            Button yearButton=(Button) view2.findViewById(R.id.yearButton);
                            yearButton.setTextColor(Color.WHITE);
                            weekButton.setBackgroundResource(R.drawable.historybutton0);
                            monthButton.setBackgroundResource(R.drawable.historybutton1);
                            yearButton.setBackgroundResource(R.drawable.historybutton0);
                            TextView pm25text=(TextView)view2.findViewById(R.id.pm25Average);
                            pm25text.setText("Average:"+pm25average);
                            TextView co2text=(TextView)view2.findViewById(R.id.co2Average);
                            co2text.setText("Average:"+co2average);
                            TextView so2text=(TextView)view2.findViewById(R.id.so2Average);
                            so2text.setText("Average:"+so2average);
                            ArrayList<double[]> value=new ArrayList<double[]>();
                            value.add(monthpm25);
                            int[]colors={Color.WHITE};
                            double[] border={1,31,0,500};
                            int width=8;
                            initView(value,colors,layout1,border,monthlabel,width,monthtext);

                            ArrayList<double[]> value2=new ArrayList<double[]>();
                            value2.add(monthco2);
                            int[]colors2={Color.LTGRAY};
                            double[] border2={1,31,0,500};
                            int width2=8;
                            initView(value2,colors2,layout2,border2,monthlabel,width2,monthtext);

                            ArrayList<double[]> value3=new ArrayList<double[]>();
                            value3.add(monthso2);
                            int[]colors3={Color.WHITE};
                            double[] border3={1,31,0,500};
                            int width3=8;
                            initView(value3,colors3,layout3,border3,monthlabel,width3,monthtext);
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
                Button weekButton=(Button) view2.findViewById(R.id.weekButton);
                weekButton.setTextColor(Color.WHITE);
                Button monthButton=(Button) view2.findViewById(R.id.monthButton);
                monthButton.setTextColor(Color.WHITE);
                Button yearButton=(Button) view2.findViewById(R.id.yearButton);
                yearButton.setTextColor(Color.BLACK);
                weekButton.setBackgroundResource(R.drawable.historybutton0);
                monthButton.setBackgroundResource(R.drawable.historybutton0);
                yearButton.setBackgroundResource(R.drawable.historybutton1);
                TextView pm25text=(TextView)view2.findViewById(R.id.pm25Average);
                pm25text.setText("Average:"+pm25average);
                TextView co2text=(TextView)view2.findViewById(R.id.co2Average);
                co2text.setText("Average:"+co2average);
                TextView so2text=(TextView)view2.findViewById(R.id.so2Average);
                so2text.setText("Average:"+so2average);
                ArrayList<double[]> value=new ArrayList<double[]>();
                value.add(yearpm25);
                int[]colors={Color.WHITE};
                double[] border={1,12,0,500};
                int width=20;
                initView(value,colors,layout1,border,yearlabel,width,yeartext);

                ArrayList<double[]> value2=new ArrayList<double[]>();
                value2.add(yearco2);
                int[]colors2={Color.LTGRAY};
                double[] border2={1,12,0,500};
                int width2=20;
                initView(value2,colors2,layout2,border2,yearlabel,width2,yeartext);

                ArrayList<double[]> value3=new ArrayList<double[]>();
                value3.add(yearpm25);
                int[]colors3={Color.WHITE};
                double[] border3={1,12,0,500};
                int width3=20;
                initView(value3,colors3,layout3,border3,yearlabel,width3,yeartext);
            }
        });



        view3=inflater.inflate(R.layout.activity_discovery, null);
        view4=inflater.inflate(R.layout.activity_me, null);
        view4.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        viewPager.setAdapter(new MyViewPagerAdapter(views));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());  //若不设置这个，标题下边的线不会随着页卡滑动而滑动
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
            Toast.makeText(PageActivity.this, "您选择了"+ viewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();
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
}