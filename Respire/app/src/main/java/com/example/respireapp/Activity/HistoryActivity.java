package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.respireapp.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

public class HistoryActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        layout1=(LinearLayout) findViewById(R.id.chart1);
        layout2=(LinearLayout) findViewById(R.id.chart2);
        layout3=(LinearLayout) findViewById(R.id.chart3);
        findViewById(R.id.weekButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button weekButton=(Button) findViewById(R.id.weekButton);
                weekButton.setTextColor(Color.BLACK);
                Button monthButton=(Button) findViewById(R.id.monthButton);
                monthButton.setTextColor(Color.WHITE);
                Button yearButton=(Button) findViewById(R.id.yearButton);
                yearButton.setTextColor(Color.WHITE);
                weekButton.setBackgroundResource(R.drawable.historybutton1);
                monthButton.setBackgroundResource(R.drawable.historybutton0);
                yearButton.setBackgroundResource(R.drawable.historybutton0);
                TextView pm25text=(TextView)findViewById(R.id.pm25Average);
                pm25text.setText("Average:"+pm25average);
                TextView co2text=(TextView)findViewById(R.id.co2Average);
                co2text.setText("Average:"+co2average);
                TextView so2text=(TextView)findViewById(R.id.so2Average);
                so2text.setText("Average:"+so2average);

                ArrayList<double[]> value=new ArrayList<double[]>();
                value.add(weekpm25);
                int[]colors={Color.WHITE};
                double[] border={1,7,0,500};
                int width=20;
                initView(value,colors,layout1,border,weeklabel,width,weektext);

                ArrayList<double[]> value2=new ArrayList<double[]>();
                value2.add(weekco2);
                int[]colors2={Color.LTGRAY};
                double[] border2={1,7,0,500};
                int width2=20;
                initView(value2,colors2,layout2,border2,weeklabel,width2,weektext);

                ArrayList<double[]> value3=new ArrayList<double[]>();
                value3.add(weekpm25);
                int[]colors3={Color.WHITE};
                double[] border3={1,7,0,500};
                int width3=20;
                initView(value3,colors3,layout3,border3,weeklabel,width3,weektext);
            }
        });

        findViewById(R.id.monthButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button weekButton=(Button) findViewById(R.id.weekButton);
                weekButton.setTextColor(Color.WHITE);
                Button monthButton=(Button) findViewById(R.id.monthButton);
                monthButton.setTextColor(Color.BLACK);
                Button yearButton=(Button) findViewById(R.id.yearButton);
                yearButton.setTextColor(Color.WHITE);
                weekButton.setBackgroundResource(R.drawable.historybutton0);
                monthButton.setBackgroundResource(R.drawable.historybutton1);
                yearButton.setBackgroundResource(R.drawable.historybutton0);
                TextView pm25text=(TextView)findViewById(R.id.pm25Average);
                pm25text.setText("Average:"+pm25average);
                TextView co2text=(TextView)findViewById(R.id.co2Average);
                co2text.setText("Average:"+co2average);
                TextView so2text=(TextView)findViewById(R.id.so2Average);
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
        });

        findViewById(R.id.yearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button weekButton=(Button) findViewById(R.id.weekButton);
                weekButton.setTextColor(Color.WHITE);
                Button monthButton=(Button) findViewById(R.id.monthButton);
                monthButton.setTextColor(Color.WHITE);
                Button yearButton=(Button) findViewById(R.id.yearButton);
                yearButton.setTextColor(Color.BLACK);
                weekButton.setBackgroundResource(R.drawable.historybutton0);
                monthButton.setBackgroundResource(R.drawable.historybutton0);
                yearButton.setBackgroundResource(R.drawable.historybutton1);
                TextView pm25text=(TextView)findViewById(R.id.pm25Average);
                pm25text.setText("Average:"+pm25average);
                TextView co2text=(TextView)findViewById(R.id.co2Average);
                co2text.setText("Average:"+co2average);
                TextView so2text=(TextView)findViewById(R.id.so2Average);
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
    }

    private void initView(ArrayList<double[]>value,int[]colors,LinearLayout layout,double[]border,int[]xlabel,int width,String[]textLabel) {
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
                                int[] colors, int x, int y,double[] range, int []xLable ,String xtitle, boolean f,int width,String[]textLabel) {
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
