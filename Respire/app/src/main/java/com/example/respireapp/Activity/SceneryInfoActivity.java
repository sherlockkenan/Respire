package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.respireapp.Entity.Place;
import com.example.respireapp.Entity.Scenery;
import com.example.respireapp.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by piglet on 2016/7/19.
 */
public class SceneryInfoActivity extends Activity {
    protected static final int REFRESH = 0;

    private Myapp myApp;
    private int count=0;
    private Bitmap pic;
    private Place theplace;
    private static final Object TAG = new Object();
    class handleData{
        public Bitmap pic;
        public int i;
        public handleData(Bitmap pic,int i){
            this.pic=pic;
            this.i=i;
        }
    }
    private Handler pic_hdl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sceneryinfo);
        Button backButton=(Button) findViewById(R.id.sbackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SceneryInfoActivity.this.finish();
            }
        });
       Intent intent=getIntent();
        Bundle bundle=this.getIntent().getExtras();
        String tmp=bundle.getString("place");
        Gson gson = new Gson();
        theplace = gson.fromJson(tmp, Place.class);
        myApp=(Myapp)getApplication();
        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();


    }
    class LoadPicThread extends Thread{
        @Override
        public void run(){
            List<Scenery> t=theplace.getSceneries();

            for (int i=0;i<theplace.getSceneries().size();i++) {
                count=i;
                String[] photo=theplace.getSceneries().get(i).getPhoto().split(";");
                pic = getUrlImage(myApp.getUrl() + photo[0]);

                handleData info=new handleData(pic,i);
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
            TableLayout table = (TableLayout) findViewById(R.id.sceneryTable);
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

                    Intent logIntent = new Intent();
                    logIntent.setClass(SceneryInfoActivity.this,PhotoInfoActivity.class);
                    logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle tbundle=new Bundle();
                    String[] urls=theplace.getSceneries().get(i).getPhoto().split(";");
                    tbundle.putInt("pm25",theplace.getSceneries().get(i).getPm25());
                    tbundle.putInt("co2",theplace.getSceneries().get(i).getCo2());
                    tbundle.putInt("so2",theplace.getSceneries().get(i).getSo2());
                    tbundle.putStringArray("url",urls);
                    tbundle.putString("location",theplace.getSceneries().get(i).getLocation());
                    tbundle.putString("time",theplace.getSceneries().get(i).getTime());
                    tbundle.putString("username",theplace.getSceneries().get(i).getUsername());
                    tbundle.putString("description",theplace.getSceneries().get(i).getDescription());
                    logIntent.putExtras(tbundle);
                    startActivity(logIntent);
                }
            });
            tablerow.addView(img, 0);
            String text="user:"+theplace.getSceneries().get(i).getUsername()+'\n'+"time:"+theplace.getSceneries().get(i).getTime();
            information.setText(text);
            information.setTextSize(20);
            tablerow.addView(information, 1);
            table.addView(tablerow);
        }
    }

}
