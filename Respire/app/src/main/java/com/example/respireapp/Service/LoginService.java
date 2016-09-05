package com.example.respireapp.Service;

/**
 * Created by piglet on 2016/6/29.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.Activity.LoginjumpActivity;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.Entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class LoginService extends Service{

    public static String JSESSIONID=null;
    public static User user;
    private static final String INSTAGRAM_CLIENT_ID = "<< YOUR INSTAGRAM CLIENT-ID >>";
    private static final Object TAG = new Object();
    private static final String LOG = "VOLLEY-SAMPLE";
    private RequestQueue mQueue;

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
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
    String url=header+"/login";
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

//                    Intent tintent=new Intent();
//                    tintent.putExtra("flag", flag);
//                    tintent.setAction("com.example.respireapp.Activity.LoginActivity");
//                    sendBroadcast(tintent);
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
