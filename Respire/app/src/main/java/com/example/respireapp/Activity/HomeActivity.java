package com.example.respireapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private static final String INSTAGRAM_CLIENT_ID = "<< YOUR INSTAGRAM CLIENT-ID >>";
    private static final Object TAG = new Object();
    private static final String LOG = "VOLLEY-SAMPLE";
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle=this.getIntent().getExtras();
       final String JSESSIONID=bundle.getString("sessionid");
        //final String JSESSIONID="123456";

        String url="http://192.168.16.130:8000/login";
        HashMap<String,String> map=new HashMap<String,String>();
        String username="kek";
        map.put("username",username);
        map.put("password",username);

        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");

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
}
