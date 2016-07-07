package com.example.respireapp.Service;

/**
 * Created by piglet on 2016/6/29.
 */
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import android.app.Service;
import android.content.Intent;
import android.app.Activity;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.example.respireapp.Activity.HomeActivity;
import com.example.respireapp.Activity.LoginjumpActivity;

import java.net.CookieStore;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.android.volley.toolbox.JsonObjectRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.respireapp.Entity.User;
import android.os.IBinder;
import android.os.Binder;
import android.content.Context;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.respireapp.Activity.Myapp;
import android.os.Bundle;
public class LoginService extends Service{

    public static String JSESSIONID=null;
    public static User user;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;
    private Myapp myapp;

    public class LocalBinder extends Binder {
        LoginService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LoginService.this;
        }
    }
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId){
    String username=intent.getStringExtra("username");
    String password=intent.getStringExtra("password");
    String url="http://192.168.16.130:8000/login";
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("username",username);
        map.put("password",password);
        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Method.POST, url,
                object, new Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSESSIONID=result.getString("data");
                    Intent logIntent = new Intent();
                    logIntent.setClass(LoginService.this,LoginjumpActivity.class);
                    logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle=new Bundle();
                    bundle.putString("flag",flag);
                    bundle.putString("sessionid",JSESSIONID);
                    logIntent.putExtras(bundle);
                    startActivity(logIntent);
                }
                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag(TAG);
        requestQueue.add(request);
return START_NOT_STICKY;
}


}
