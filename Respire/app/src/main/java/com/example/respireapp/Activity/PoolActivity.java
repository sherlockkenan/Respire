package com.example.respireapp.Activity;

import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.respireapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PoolActivity extends AppCompatActivity {
    String JSESSIONID;
    private static final Object TAG = new Object();
    private RequestQueue mQueue;

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

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        String url="http://192.168.16.61:8000/pool/getrank";
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

        url="http://192.168.16.61:8000/getprofile";
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
    }
}
