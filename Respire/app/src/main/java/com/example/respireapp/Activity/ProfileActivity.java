package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends Activity {
    String JSESSIONID;
    private static final Object TAG = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.finish();
            }
        });

        Button usernameButton=(Button) findViewById(R.id.usernameButton);
        usernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        Button passwordButton=(Button) findViewById(R.id.passwordButton);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(ProfileActivity.this,ChangePasswordActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Button phoneButton=(Button) findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(ProfileActivity.this,ChangePhoneActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Button emailButton=(Button) findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(ProfileActivity.this,ChangeEmailActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Button sexButton=(Button) findViewById(R.id.sexButton);
        sexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(ProfileActivity.this,ChangeSexActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        Button cityButton=(Button) findViewById(R.id.cityButton);
        cityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIntent = new Intent();
                logIntent.setClass(ProfileActivity.this,ChangeCityActivity.class);
                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle tbundle=new Bundle();
                tbundle.putString("sessionid",JSESSIONID);
                logIntent.putExtras(tbundle);
                startActivity(logIntent);
            }
        });

        final TextView usernameText=(TextView) findViewById(R.id.usernameText);
        final TextView phoneText=(TextView) findViewById(R.id.phoneText);
        final TextView emailText=(TextView) findViewById(R.id.emailText);
        final TextView sexText=(TextView) findViewById(R.id.sexText);
        final TextView city1Text=(TextView) findViewById(R.id.city1Text);
        final TextView city2Text=(TextView) findViewById(R.id.city2Text);
        final TextView city3Text=(TextView) findViewById(R.id.city3Text);
        final TextView city4Text=(TextView) findViewById(R.id.city4Text);

        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getprofile";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONObject data=result.getJSONObject("data");
                    if (flag.equals("success")&&data!=null){
                        usernameText.setText(data.getString("username"));
                        phoneText.setText(data.getString("phone"));
                        emailText.setText(data.getString("email"));
                        String sex=data.getString("sex");
                        if (sex.equals("0")){
                            sexText.setText("Female");
                        }
                        else{
                            sexText.setText("Male");
                        }
                        city1Text.setText(data.getString("city1"));
                        city2Text.setText(data.getString("city2"));
                        city3Text.setText(data.getString("city3"));
                        city4Text.setText(data.getString("city4"));
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
    }
}
