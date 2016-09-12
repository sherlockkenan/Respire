package com.example.respireapp.Service;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.Activity.SceneryInfoActivity;
import com.example.respireapp.Entity.LocationUtils;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.Entity.Place;
import com.example.respireapp.Entity.Scenery;
import com.example.respireapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Created by keke on 2016/7/19.
 */
public class RecommandService extends Service {
    private  Myapp myApp;

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongrunningService","excuted at "+new Date().toString());
                Send_request();
            }
        }).start();

        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anhour=1000*60;
        long triggerAttime= SystemClock.elapsedRealtime()+anhour;
        Intent i=new Intent(this,AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAttime,pi);
        return super.onStartCommand(intent,flags,startId);


    }

    private void setNotification(List<Scenery> sceneries){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(RecommandService.this);

        //设置通知的基本信息：icon、标题、内容
        builder.setSmallIcon(R.drawable.loc);
        builder.setContentTitle("Recommandation");
        String information="";
        for(int i=0;i<sceneries.size();i++){
            information+=sceneries.get(i).getLocation();
        }
        builder.setContentText(information);

        // 设置通知的点击行为：这里启动一个 Activity
        Intent intent = new Intent(this, SceneryInfoActivity.class);
        Gson gson = new Gson();
        Place place=new Place();
        place.setSceneries(sceneries);
        String result = gson.toJson(place);
        Bundle bundle=new Bundle();
        bundle.putString("place",result);
        intent.putExtras(bundle);

        intent.putExtra("scenery",sceneries.toString());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        // 发送通知 id 需要在应用内唯一
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }

    public void  Send_request(){
        myApp=(Myapp)getApplication();
        String url= myApp.getUrl()+"/recommand/getrecommand";
        Log.d("request",url);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JSONObject object=new JSONObject();
        LocationUtils.initLocation(this.getApplication());
        Double lat=LocationUtils.latitude;
        Double lon=LocationUtils.longitude;
        try {
            object.put("latitude", lat);
            object.put("longitude", lon);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                object,new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String data = result.getJSONArray("data").toString();
                    Gson gson = new Gson();
                    List<Scenery> sceneries = gson.fromJson(data,new TypeToken<List<Scenery>>(){}.getType());
                    setNotification(sceneries);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);
    }
}
