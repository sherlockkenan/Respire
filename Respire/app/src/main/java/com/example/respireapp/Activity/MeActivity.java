package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;
import com.example.respireapp.Service.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

public class MeActivity extends Activity {
    private String JSESSIONID;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;
    private Myapp myApp;
    private String header;
    private View.OnClickListener logoutlistener=new View.OnClickListener(){
        public void onClick(View v) {
            myApp=(Myapp)getApplication();
            JSESSIONID=myApp.getSessionid();
            header=myApp.getUrl();
            RequestQueue requestQueue= Volley.newRequestQueue(MeActivity.this);
            String url=header+"/logout";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                    null, new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject result){
                    try {
                        String returnvalue=result.getString("return_type");
                        if(returnvalue.equals("success")){
                            Intent logIntent = new Intent();
                            logIntent.setClass(MeActivity.this,LoginActivity.class);
                            logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(logIntent);
                        }
                        else if(returnvalue.equals("fail")){
                            Toast.makeText(getApplicationContext(), "登出失败！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {

                }
            });
            request.setTag(TAG);
            requestQueue.add(request);

        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
//        ImageButton button=(ImageButton)findViewById(R.id.logoutButton);
//        button.setOnClickListener(logoutlistener);
    }
}
