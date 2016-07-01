package com.example.respireapp.Service;

/**
 * Created by piglet on 2016/6/29.
 */
import com.android.volley.RequestQueue;
import android.app.Service;
import android.content.Intent;
import com.example.respireapp.Activity.LoginjumpActivity;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.android.volley.toolbox.JsonObjectRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class LoginService extends Service{

    public static String JSESSIONID = null;
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
    String url="http://192.168.16.61:8000/login";
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
                    Intent logIntent = new Intent(LoginService.this, LoginjumpActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("flag",flag);
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
return START_STICKY;
}


}
