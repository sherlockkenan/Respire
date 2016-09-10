package com.example.respireapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PhotoInfoActivity extends AppCompatActivity {
    private static final Object TAG = new Object();
    private String JSESSIONID;
    private Handler pic_hdl;
    private String[] urls;
    private Bitmap pic;
    private Bitmap pic2;
    private Myapp myApp;
    private Bitmap[] pics=new Bitmap[2];
    private int hour;
    private int start;
    private String sceneryid;
    private String head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);
        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar Cld= Calendar.getInstance();
                int end=Cld.get(Calendar.MILLISECOND);
                double length=((end-start)/100)*0.1;

                RequestQueue requestQueue= Volley.newRequestQueue(PhotoInfoActivity.this);
                String url=head+"/recommand/getclickitem";
                JSONObject object=new JSONObject();
                try {
                    object.put("recomid",sceneryid);
                    object.put("time",hour);
                    object.put("length",length);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                        object, new Response.Listener<JSONObject>(){
                    public void onResponse(JSONObject result){
                        try {
                            String returnvalue=result.getString("return_type");

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
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
                PhotoInfoActivity.this.finish();
            }
        });


        myApp=(Myapp)getApplication();
        head=myApp.getUrl();
        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");
        hour=bundle.getInt("hour");
        start=bundle.getInt("start");
        sceneryid=bundle.getString("sceneryid");
        int pm25=bundle.getInt("pm25");
        int co2=bundle.getInt("co2");
        int so2=bundle.getInt("so2");
        urls=bundle.getStringArray("url");
        String location=bundle.getString("location");
        String time=bundle.getString("time");
        String username=bundle.getString("username");
        String description=bundle.getString("description");

        TextView locationText=(TextView) findViewById(R.id.locationText);
        locationText.setText(location);
        TextView descriptionText=(TextView) findViewById(R.id.descriptionText);
        descriptionText.setText(description);
        TextView pm25Text=(TextView) findViewById(R.id.pm25Text);
        pm25Text.setText("PM2.5:"+Integer.toString(pm25));
        TextView co2Text=(TextView) findViewById(R.id.co2Text);
        co2Text.setText("CO2:"+Integer.toString(co2));
        TextView so2Text=(TextView) findViewById(R.id.so2Text);
        so2Text.setText("SO2:"+Integer.toString(so2));
        TextView timeText=(TextView) findViewById(R.id.timeText);
        timeText.setText(time);
        TextView usernameText=(TextView) findViewById(R.id.usernameText);
        usernameText.setText(username);

        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();


    }

    class LoadPicThread extends Thread{
        @Override
        public void run(){
            for (int i=0;i<urls.length;i+=2) {
                pic = getUrlImage(myApp.getUrl() + urls[i]);
                if (i+1<urls.length) {
                    pic2 = getUrlImage(myApp.getUrl() + urls[i+1]);
                } else {
                    pic2 = null;
                }
                pics[0] = pic;
                pics[1] = pic2;
                Message msg = pic_hdl.obtainMessage();
                msg.what = 0;
                msg.obj = pics;
                pic_hdl.sendMessage(msg);
            }
        }
    }

    class PicHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Bitmap[] imgs=(Bitmap[])msg.obj;

            TableLayout table = (TableLayout) findViewById(R.id.photoTable);
            TableRow tablerow = new TableRow(getBaseContext());
            ImageView img=new ImageView(getBaseContext());
            //img.setImageResource(R.drawable.discoveryback1);
            img.setImageBitmap(imgs[0]);
            img.setAdjustViewBounds(true);
            tablerow.addView(img, 0);

            if (imgs[1]!=null){
                ImageView img2 = new ImageView(getBaseContext());
                //img2.setImageResource(R.drawable.discoveryback2);
                img2.setImageBitmap(imgs[1]);
                img2.setAdjustViewBounds(true);
                img2.offsetLeftAndRight(10);
                tablerow.addView(img2, 1);
            }
            table.addView(tablerow);
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
}
