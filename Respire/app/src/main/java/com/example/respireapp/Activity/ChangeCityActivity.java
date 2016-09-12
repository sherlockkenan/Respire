package com.example.respireapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

import java.util.HashMap;
import java.util.Map;

public class ChangeCityActivity extends AppCompatActivity {
    String JSESSIONID;
    private static final Object TAG = new Object();
    private static final Object TAG2 = new Object();
    private static final Pair[] sexdata=new Pair[2];
    private Spinner city1spinner;
    private Spinner city2spinner;
    private Spinner city3spinner;
    private Spinner city4spinner;
    private ArrayAdapter<Pair> city1adapter;
    private ArrayAdapter<Pair> city2adapter;
    private ArrayAdapter<Pair> city3adapter;
    private ArrayAdapter<Pair> city4adapter;
    private Pair[] city1data;
    private Pair[] city2data;
    private Pair[] city3data;
    private Pair[] city4data;
    private String city1_fid;
    private String city2_fid;
    private String city3_fid;
    private String city4_fid;
    private Map<String,String> citymap;
    private String[] citylist=new String[]{};

    public class Pair {
        public String key;
        public String value;
        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
        public String toString() {
            return value;
        }
        public String getkey(){
            return key;
        }
    }

    public void  sendcity3request(String fatherid){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getcitylist?fatherid="+fatherid;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    JSONArray returnvalue = result.getJSONArray("data");
                    city3data = new Pair[returnvalue.length()+1];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");
                        Pair tmp=new Pair(cityid,name);
                        city3data[i]=tmp;
                    }
                    city3adapter = new ArrayAdapter<Pair>(ChangeCityActivity.this,android.R.layout.simple_spinner_item,city3data);
                    //设置下拉列表的风格
                    city3adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //将adapter 添加到spinner中
                    city3spinner.setAdapter(city3adapter);
                    //添加事件Spinner事件监听
                    city3spinner.setOnItemSelectedListener(new City3SpinnerSelectedListener());
                    //设置默认值
                    city3spinner.setVisibility(View.VISIBLE);
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
    public void  sendcity4request(String fatherid){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getcitylist?fatherid="+fatherid;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    JSONArray returnvalue = result.getJSONArray("data");
                    city4data = new Pair[returnvalue.length()+1];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");
                        Pair tmp=new Pair(cityid,name);
                        city4data[i]=tmp;
                    }
                    city4adapter = new ArrayAdapter<Pair>(ChangeCityActivity.this,android.R.layout.simple_spinner_item,city4data);
                    //设置下拉列表的风格
                    city4adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //将adapter 添加到spinner中
                    city4spinner.setAdapter(city4adapter);
                    //添加事件Spinner事件监听
                    city4spinner.setOnItemSelectedListener(new City4SpinnerSelectedListener());
                    //设置默认值
                    city4spinner.setVisibility(View.VISIBLE);
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
    public void  sendcity2request(String fatherid){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getcitylist?fatherid="+fatherid;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    JSONArray returnvalue = result.getJSONArray("data");
                    city2data = new Pair[returnvalue.length()+1];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");
                        Pair tmp=new Pair(cityid,name);
                        city2data[i]=tmp;
                    }
                    city2adapter = new ArrayAdapter<Pair>(ChangeCityActivity.this,android.R.layout.simple_spinner_item,city2data);
                    //设置下拉列表的风格
                    city2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //将adapter 添加到spinner中
                    city2spinner.setAdapter(city2adapter);
                    //添加事件Spinner事件监听
                    city2spinner.setOnItemSelectedListener(new City2SpinnerSelectedListener());
                    //设置默认值
                    city2spinner.setVisibility(View.VISIBLE);
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
    public void  sendcity1request(String fatherid){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getcitylist?fatherid=0";
//        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("fatherid",fatherid);
//        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    JSONArray returnvalue = result.getJSONArray("data");
                    city1data = new Pair[returnvalue.length()+1];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");
                        Pair tmp=new Pair(cityid,name);
                        city1data[i]=tmp;

                    }
                    city1adapter = new ArrayAdapter<Pair>(ChangeCityActivity.this,android.R.layout.simple_spinner_item,city1data);
                    //设置下拉列表的风格
                    city1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city1spinner.setAdapter(city1adapter);
                    //添加事件Spinner事件监听
                    city1spinner.setOnItemSelectedListener(new City1SpinnerSelectedListener());
                    //设置默认值
                    city1spinner.setVisibility(View.VISIBLE);
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

    }

    class City4SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city4_fid=((Pair)city4spinner.getSelectedItem()).getkey();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    class City3SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city4data=new Pair[1];
            city3_fid=((Pair)city3spinner.getSelectedItem()).getkey();
            sendcity4request(city3_fid);


        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class City2SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city3data=new Pair[1];
            city4data=new Pair[1];
            city2_fid=((Pair)city2spinner.getSelectedItem()).getkey();
            sendcity3request(city2_fid);



        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    public class City1SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city2data=new Pair[1];
            city3data=new Pair[1];
            city4data=new Pair[1];

            city1_fid=((Pair)city1spinner.getSelectedItem()).getkey();
            sendcity2request(city1_fid);

        }
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_city);

        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeCityActivity.this.finish();
            }
        });

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        sendcity1request("0");
        city1spinner = (Spinner) findViewById(R.id.spinner1);
        city2spinner = (Spinner) findViewById(R.id.spinner2);
        city3spinner=(Spinner)findViewById(R.id.spinner3);
        city4spinner=(Spinner)findViewById(R.id.spinner4);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView infoText=(TextView) findViewById(R.id.infoText);
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
                                String username=data.getString("username");
                                String password=data.getString("password");
                                String email=data.getString("email");
                                String phone=data.getString("phone");
                                String role=data.getString("role");
                                String gender=data.getString("sex");
                                Myapp myApp=(Myapp)getApplication();
                                String header=myApp.getUrl();
                                String url2=header+"/update";
                                HashMap<String,String> map=new HashMap<String,String>();
                                map.put("username",username);
                                map.put("password",password);
                                map.put("phone",phone);
                                map.put("email",email);
                                map.put("sex",gender);
                                map.put("role",role);
                                map.put("cityid",city4_fid);
                                JSONObject object=new JSONObject(map);
                                RequestQueue requestQueue2= Volley.newRequestQueue(getApplicationContext());
                                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.POST, url2,
                                        object, new Response.Listener<JSONObject>(){
                                    public void onResponse(JSONObject result2){
                                        try {
                                            String returnvalue=result2.getString("return_type");
                                            if(returnvalue.equals("success")){
                                                infoText.setText("Change succeeded!");
                                            }
                                            else if(returnvalue.equals("fail")){
                                                infoText.setText("Change failed!");
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
                                request2.setTag(TAG2);
                                requestQueue2.add(request2);
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
        });
    }
}
