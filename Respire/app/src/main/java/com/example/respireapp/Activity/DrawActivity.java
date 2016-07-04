package com.example.respireapp.Activity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
<<<<<<< HEAD
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
=======

>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
<<<<<<< HEAD
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
=======
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
<<<<<<< HEAD
import android.widget.RelativeLayout;
=======
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8
import android.widget.TextView;
import android.widget.Toast;

import com.example.respireapp.Entity.ClassMessage;
<<<<<<< HEAD
//import com.example.respireapp.Entity.GraphUtils;
=======
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8
import com.example.respireapp.Entity.GraphUtils;
import com.example.respireapp.Entity.Pie;

import com.example.respireapp.Entity.StudentGradeMessage;
import com.example.respireapp.R;

public class DrawActivity extends Activity implements OnClickListener {

    private StudentGradeMessage sgm ;
    private Map<String, StudentGradeMessage> stuGradeMap ;
    private ArrayList<Pie> pie = new ArrayList<Pie>();
    private ArrayList<ClassMessage> cml = new ArrayList<ClassMessage>();
    private ClassMessage cm ;
    private Button btn1;
    private Button btn4;
    private GraphicalView charView;
    private CalendarView cv;
<<<<<<< HEAD
    public LinearLayout layout;
=======
    private LinearLayout layout;
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8
    private FrameLayout fl;
    private Pie p;
    private Intent intent;
    private List<HashMap<String, StudentGradeMessage>> studentGradeList = new ArrayList<HashMap<String,StudentGradeMessage>>();
    private CategorySeries mSeries;
    private DefaultRenderer mRenderer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        btn1 = (Button) findViewById(R.id.button1);
        btn4 = (Button) findViewById(R.id.button4);
        layout = (LinearLayout) findViewById(R.id.chart);
        fl = (FrameLayout) findViewById(R.id.fl);
        btn1.setOnClickListener(this);
        btn4.setOnClickListener(this);
        initData();


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        mSeries = (CategorySeries) savedState.getSerializable("current_series");
        mRenderer = (DefaultRenderer) savedState.getSerializable("current_renderer");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("current_series", mSeries);
        outState.putSerializable("current_renderer", mRenderer);
    }
    private void initData() {
        // TODO Auto-generated method stub
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.1");
        sgm.setMath(80);
        sgm.setChinese(87);
        sgm.setEnglish(78);
        sgm.setTotal(248);
        sgm.setNumChinese(10);
        sgm.setNumEnglish(25);
        sgm.setNumMath(9);
        sgm.setNumTotal(12);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.2");
        sgm.setMath(67);
        sgm.setChinese(89);
        sgm.setEnglish(80);
        sgm.setTotal(236);
        sgm.setNumChinese(5);
        sgm.setNumEnglish(21);
        sgm.setNumMath(23);
        sgm.setNumTotal(16);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.3");
        sgm.setMath(50);
        sgm.setChinese(80);
        sgm.setEnglish(70);
        sgm.setTotal(200);
        sgm.setNumChinese(10);
        sgm.setNumEnglish(35);
        sgm.setNumMath(39);
        sgm.setNumTotal(29);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.4");
        sgm.setMath(60);
        sgm.setChinese(67);
        sgm.setEnglish(60);
        sgm.setTotal(187);
        sgm.setNumChinese(40);
        sgm.setNumEnglish(30);
        sgm.setNumMath(30);
        sgm.setNumTotal(40);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.5");
        sgm.setMath(80);
        sgm.setChinese(87);
        sgm.setEnglish(88);
        sgm.setTotal(258);
        sgm.setNumChinese(9);
        sgm.setNumEnglish(7);
        sgm.setNumMath(13);
        sgm.setNumTotal(14);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);
        stuGradeMap = new HashMap<String, StudentGradeMessage>();
        sgm = new StudentGradeMessage();
        sgm.setName("1.6");
        sgm.setMath(90);
        sgm.setChinese(80);
        sgm.setEnglish(50);
        sgm.setTotal(220);
        sgm.setNumChinese(10);
        sgm.setNumEnglish(35);
        sgm.setNumMath(2);
        sgm.setNumTotal(21);
        stuGradeMap.put("name",sgm );
        studentGradeList.add((HashMap<String, StudentGradeMessage>) stuGradeMap);

        p = new Pie();
        p.setName("1");
        p.setValue(15);
        pie.add(p);
        p = new Pie();
        p.setName("2");
        p.setValue(20);
        pie.add(p);
        p = new Pie();
        p.setName("3");
        p.setValue(3);
        pie.add(p);
        p = new Pie();
        p.setName("4");
        p.setValue(5);
        pie.add(p);
        cm = new ClassMessage();
        cm.setClassName("CLASS ONE");
        cm.setExcellent(8.695);
        cm.setPass(84.78);
        cm.setUnpass(15.22);
        cml.add(cm);
        cm = new ClassMessage();
        cm.setClassName("CLASS TWO");
        cm.setExcellent(18.18);
        cm.setPass(97.72);
        cm.setUnpass(2.28);
        cml.add(cm);
        cm = new ClassMessage();
        cm.setClassName("CLASS THREE");
        cm.setExcellent(27.9);
        cm.setPass(88.37);
        cm.setUnpass(11.63);
        cml.add(cm);
        cm = new ClassMessage();
        cm.setClassName("CLASS FOUR");
        cm.setExcellent(17.08);
        cm.setPass(97.82);
        cm.setUnpass(2.18);
        cml.add(cm);
        cm = new ClassMessage();
        cm.setClassName("CLASS FIVE");
        cm.setExcellent(11.11);
        cm.setPass(88.88);
        cm.setUnpass(12.12);
        cml.add(cm);

    }

<<<<<<< HEAD
    private void initView() {
        //柱状图的两个序列的名字
        String[] titles = new String[] { "净利润" };
        //存放柱状图两个序列的值
        ArrayList<double[]> value = new ArrayList<double[]>();
        double[] d1 = new double[] { 0.1, 0.3, 0.7, 0.8, 0.5 };
        value.add(d1);
        //两个状的颜色
        int[] colors = { Color.BLUE};
        //为li1添加柱状图
        layout.addView(
                xychar(titles, value, colors, 6, 1, new double[] { 2007,
                        2012.5, 0, 1 }, new int[] { 2008, 2009, 2010, 2011,
                        2012 }, "利润总额与净利润(财务)", true),
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT));
    }

    public GraphicalView xychar(String[] titles, ArrayList<double[]> value,
                                int[] colors, int x, int y,double[] range, int []xLable ,String xtitle, boolean f) {
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
            // 设置点的样式 //
            xyRenderer.setPointStyle(PointStyle.SQUARE);
            // 将要绘制的点添加到坐标绘制中

            renderer.addSeriesRenderer(xyRenderer);
        }
        //设置x轴标签数
        renderer.setXLabels(x);
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
        renderer.setShowGrid(true);

        renderer.setShowAxes(true);
        // 设置条形图之间的距离
        renderer.setBarSpacing(0.2);
        renderer.setBarWidth(50);
        renderer.setInScroll(false);
        renderer.setPanEnabled(false, false);
        renderer.setClickEnabled(false);
        //设置x轴和y轴标签的颜色
        renderer.setXLabelsColor(Color.RED);
        renderer.setYLabelsColor(0,Color.RED);

        int length = renderer.getSeriesRendererCount();
        //设置图标的标题
        renderer.setChartTitle(xtitle);
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

=======

    public XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        renderer.setLabelsTextSize(5);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setBarWidth(59f);
        renderer.setBarSpacing(20);
        renderer.setShowAxes(false);
        renderer.setShowLegend(false);
        renderer.setBackgroundColor(Color.parseColor("#efefef"));
        renderer.setApplyBackgroundColor(true);
        renderer.setPanEnabled(false, false);
        renderer.setZoomEnabled(false, false);
        renderer.setLegendTextSize(5);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a bar multiple series dataset using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<Double> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double v = values.get(i);

            series.add(v);

            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    /**
     * Sets a few of the series renderer settings.
     *
     * @param renderer the renderer to set the properties to
     * @param title the chart title
     * @param xTitle the title for the X axis
     * @param yTitle the title for the Y axis
     * @param xMin the minimum value on the X axis
     * @param xMax the maximum value on the X axis
     * @param yMin the minimum value on the Y axis
     * @param yMax the maximum value on the Y axis
     * @param axesColor the axes color
     * @param labelsColor the labels color
     */
    protected static void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                           String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
                                           int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.button1:
<<<<<<< HEAD
                //charView = (GraphicalView) GraphUtils.getInstance().getLineChartView(DrawActivity.this, studentGradeList , "B");
=======
                charView = (GraphicalView) GraphUtils.getInstance().getLineChartView(DrawActivity.this, studentGradeList , "B");
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8
                layout.removeAllViews();
                layout.addView(charView);
                break;
            case R.id.button4:
<<<<<<< HEAD
//                String[] titles = new String[] { "1","2"};
//                List<Double> values = new ArrayList<Double>();
//                values.add(50.00);
//                values.add(10.00);
//                values.add(100.00);
//                values.add(60.00);
//
//
//
//                charView = (GraphicalView) GraphUtils.getInstance().getBarHorView(getBaseContext(),titles,values);
//                layout.removeAllViews();
//                layout.addView(charView);
                initView();
=======

                String[] titles = new String[] { "2008", "2007" };//图例
                List<Double> values = new ArrayList<Double>();
                values.add(14230.00);
                values.add(12300.00);
                values.add(14240.00);
                values.add(15244.00);
                values.add(15900.00);
                values.add(19200.00);
                values.add(22030.00);
                values.add(21200.00);
                values.add(19500.00);
                values.add(15500.00);
                values.add(12600.00);
                values.add(14000.00);
                int[] colors = new int[] { Color.BLUE, Color.CYAN };//两种柱子的颜色
                XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);//调用AbstractDemoChart中的方法构建renderer.
                setChartSettings(renderer, "Monthly sales in the last 2 years", "Month", "Units sold", 0.5,
                        12.5, 0, 24000, Color.GRAY, Color.LTGRAY);//调用AbstractDemoChart中的方法设置renderer的一些属性.
                renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值
                renderer.getSeriesRendererAt(1).setDisplayChartValues(true);//设置柱子上是否显示数量值
                renderer.setXLabels(12);//X轴的近似坐标数
                renderer.setYLabels(5);//Y轴的近似坐标数
                renderer.setXLabelsAlign(Paint.Align.LEFT);//刻度线与X轴坐标文字左侧对齐
                renderer.setYLabelsAlign(Paint.Align.LEFT);//Y轴与Y轴坐标文字左对齐
                renderer.setPanEnabled(true, false);//允许左右拖动,但不允许上下拖动.
                // renderer.setZoomEnabled(false);
                renderer.setZoomRate(1.1f);//放大的倍率
                renderer.setBarSpacing(0.5f);//柱子间宽度
               charView= ChartFactory.getBarChartView(getBaseContext(), buildBarDataset(titles, values), renderer,
                        BarChart.Type.STACKED);


               // charView = (GraphicalView) GraphUtils.getInstance().getBarHorView(getBaseContext(),titles,values);
                layout.removeAllViews();
                layout.addView(charView);
>>>>>>> 9058575421d75c46d2d5a55d54cddc76770cdfe8
                break;
            default:
                break;
        }
    }

}