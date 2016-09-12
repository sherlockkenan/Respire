package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v4.util.Pools;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.Entity.LocationUtils;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
public class PoolActivity extends Activity {
    String JSESSIONID;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;
    private Myapp myApp;
    private Bitmap pic;
    private Bitmap pic2;
    private Bitmap[] pics=new Bitmap[2];
    private String[]picurl;

    private int count=0;
    private String[][] urls;
    private String[] sceneryid;
    private int[] pm25s;
    private int[] co2s;
    private int[] so2s;
    private String[] locations;
    private String[] times;
    private String[] usernames;
    private String[] descriptions;

    class handleData{
        public Bitmap[] pics;
        public int i;
        public handleData(Bitmap[] pics,int i){
            this.pics=pics;
            this.i=i;
        }
    }



    private Handler pic_hdl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);

        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoolActivity.this.finish();
            }
        });

        Button photoButton=(Button) findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(PoolActivity.this,TestpicActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/pool/getrank";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONArray data=result.getJSONArray("data");
                    if (data!=null){
                    int j=1;
                    TableLayout table = (TableLayout) findViewById(R.id.Table);
                    for (int i=data.length()-1;i>=0;i--) {
                        JSONObject obj = data.getJSONObject(i);
                        TableRow tablerow = new TableRow(getBaseContext());
                        TextView text = new TextView(getBaseContext());
                        text.setPadding(1, 1, 1, 1);
                        text.setText(Integer.toString(j));
                        text.setTextSize(20);
                        text.setGravity(Gravity.CENTER_HORIZONTAL);
                        tablerow.addView(text, 0);

                        TextView text2 = new TextView(getBaseContext());
                        text2.setPadding(1, 1, 1, 1);
                        text2.setText(obj.getString("userid"));
                        text2.setTextSize(20);
                        text2.setGravity(Gravity.CENTER_HORIZONTAL);
                        tablerow.addView(text2, 1);

                        TextView text3 = new TextView(getBaseContext());
                        text3.setPadding(1, 1, 1, 1);
                        text3.setText(obj.getString("pm25"));
                        text3.setTextSize(20);
                        text3.setGravity(Gravity.CENTER_HORIZONTAL);
                        tablerow.addView(text3, 2);
                        table.addView(tablerow);
                        j++;
                    }
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

        myApp=(Myapp)getApplication();
        header=myApp.getUrl();
        url=header+"/getprofile";
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONObject data=result.getJSONObject("data");
                    if (flag.equals("success")&&data!=null){
                        String poolname=data.getString("city4");
                        TextView pool=(TextView) findViewById(R.id.PoolName);
                        pool.setText(poolname+"鱼塘");
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


        myApp=(Myapp)getApplication();
        header=myApp.getUrl();
        url=header+"/scenery/getimage";
        LocationUtils.initLocation(this.getApplication());
        Double lat=LocationUtils.latitude;
        Double lon=LocationUtils.longitude;
        JSONObject object=new JSONObject();
        try {
            object.put("latitude",lat);
            object.put("longitude",lon);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        request = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONArray data=result.getJSONArray("data");
                    if (flag.equals("success")&&data!=null){
                        urls=new String[data.length()][];
                        pm25s=new int[data.length()];
                        co2s=new int[data.length()];
                        so2s=new int[data.length()];
                        locations=new String[data.length()];
                        times=new String[data.length()];
                        usernames=new String[data.length()];
                        descriptions=new String[data.length()];
                        sceneryid=new String[data.length()];
                        for(int i=0;i<data.length();i++) {
                            JSONObject tmp=(JSONObject)data.get(i);
                            //picurl=new String[data.length()];
                            //pics=new Bitmap[data.length()];
                            picurl=tmp.getString("photo").split(";");
                            String sid=tmp.getString("uid");
                            int pm25=tmp.getInt("pm25");
                            int co2=tmp.getInt("co2");
                            int so2=tmp.getInt("so2");
                            String location=tmp.getString("location");
                            String time=tmp.getString("time");
                            String username=tmp.getString("username");
                            String description=tmp.getString("description");
                            sceneryid[i]=sid;
                            pm25s[i]=pm25;
                            urls[i]=picurl;
                            co2s[i]=co2;
                            so2s[i]=so2;
                            locations[i]=location;
                            times[i]=time;
                            usernames[i]=username;
                            descriptions[i]=description;
                        }
                    }
                    pic_hdl = new PicHandler();
                    Thread t = new LoadPicThread();
                    t.start();
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






//        TableLayout table = (TableLayout) findViewById(R.id.photoTable);
//
//        count2=0;
//        while (count2<count) {
//            TableRow tablerow = new TableRow(getBaseContext());
//            ImageView img=new ImageView(getBaseContext());
//            //img.setImageResource(R.drawable.discoveryback1);
//            img.setImageBitmap(url0[count2]);
//            img.setAdjustViewBounds(true);
//            img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder builder=new AlertDialog.Builder(PoolActivity.this);
//                    builder.setMessage("text "+Integer.toString(count2)+" + 0")
//                            .setTitle("Info");
//                    AlertDialog dialog=builder.create();
//                    dialog.show();
//                }
//            });
//            tablerow.addView(img, 0);
//            count2++;
//
//            if (count2<count) {
//                ImageView img2 = new ImageView(getBaseContext());
//                //img2.setImageResource(R.drawable.discoveryback2);
//                img2.setImageBitmap(url0[count2]);
//                img2.setAdjustViewBounds(true);
//                img2.offsetLeftAndRight(10);
//                img2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(PoolActivity.this);
//                        builder.setMessage("text " + Integer.toString(count2) + " + 1")
//                                .setTitle("Info");
//                        AlertDialog dialog = builder.create();
//                        dialog.show();
//                    }
//                });
//                tablerow.addView(img2, 1);
//                count2++;
//            }
//            table.addView(tablerow);
//        }

    }
    class LoadPicThread extends Thread{
        @Override
        public void run(){
            for (int i=0;i<urls.length;i+=2) {
                count=i;
                pic = getUrlImage(myApp.getUrl() + urls[i][0]);
                if (i+1<urls.length) {
                    pic2 = getUrlImage(myApp.getUrl() + urls[i+1][0]);
                } else {
                    pic2 = null;
                }
                pics[0] = pic;
                pics[1] = pic2;
                handleData info=new handleData(pics,i);
                Message msg = pic_hdl.obtainMessage();
                msg.what = 0;
                msg.obj = info;
                pic_hdl.sendMessage(msg);
            }
        }
    }

    class PicHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            handleData info=(handleData)msg.obj;

            Bitmap[] imgs=info.pics;
            final int i=info.i;

            TableLayout table = (TableLayout) findViewById(R.id.photoTable);
            TableRow tablerow = new TableRow(getBaseContext());
            ImageView img=new ImageView(getBaseContext());
            //img.setImageResource(R.drawable.discoveryback1);
            img.setImageBitmap(imgs[0]);
            img.setAdjustViewBounds(true);
            img.setMaxWidth(250);
            img.setMinimumWidth(250);
            img.setMinimumHeight(250);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SimpleDateFormat formatter = new SimpleDateFormat ("HH");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    Calendar Cld= Calendar.getInstance();
                    int start=Cld.get(Calendar.MILLISECOND);
                    int hour=Integer.parseInt(str);
                    Intent logIntent = new Intent();
                    logIntent.setClass(PoolActivity.this,PhotoInfoActivity.class);
                    logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle tbundle=new Bundle();
                    tbundle.putInt("start",start);
                    tbundle.putInt("hour",hour);
                    tbundle.putString("sessionid",JSESSIONID);
                    tbundle.putString("sceneryid",sceneryid[i]);
                    tbundle.putInt("pm25",pm25s[i]);
                    tbundle.putInt("co2",co2s[i]);
                    tbundle.putInt("so2",so2s[i]);
                    tbundle.putStringArray("url",urls[i]);
                    tbundle.putString("location",locations[i]);
                    tbundle.putString("time",times[i]);
                    tbundle.putString("username",usernames[i]);
                    tbundle.putString("description",descriptions[i]);
                    logIntent.putExtras(tbundle);
                    startActivity(logIntent);
                }
            });
            tablerow.addView(img, 0);

            if (imgs[1]!=null){
                ImageView img2 = new ImageView(getBaseContext());
                //img2.setImageResource(R.drawable.discoveryback2);
                img2.setImageBitmap(imgs[1]);
                img2.setAdjustViewBounds(true);
                img2.setMaxWidth(250);
                img2.setMinimumWidth(250);
                img2.setMinimumHeight(250);
                img2.offsetLeftAndRight(10);
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent logIntent = new Intent();
                        logIntent.setClass(PoolActivity.this,PhotoInfoActivity.class);
                        logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle tbundle=new Bundle();
                        tbundle.putString("sessionid",JSESSIONID);
                        tbundle.putInt("pm25",pm25s[i+1]);
                        tbundle.putInt("co2",co2s[i+1]);
                        tbundle.putInt("so2",so2s[i+1]);
                        tbundle.putStringArray("url",urls[i+1]);
                        tbundle.putString("location",locations[i+1]);
                        tbundle.putString("time",times[i+1]);
                        tbundle.putString("username",usernames[i+1]);
                        tbundle.putString("description",descriptions[i+1]);
                        logIntent.putExtras(tbundle);
                        startActivity(logIntent);
                    }
                });
                tablerow.addView(img2, 1);
            }
            table.addView(tablerow);
            //ImageView image=(ImageView)findViewById(R.id.thephoto);
            //image.setImageBitmap(myimg);
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
