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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangeSexActivity extends AppCompatActivity {
    String JSESSIONID;
    private static final Object TAG = new Object();
    private static final Object TAG2 = new Object();
    private static final Pair[] sexdata=new Pair[2];
    private Spinner sexspinner;
    private ArrayAdapter<Pair> sexadapter;
    private String gender;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_sex);

        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeSexActivity.this.finish();
            }
        });

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");

        Pair tmp_sex=new Pair("1","男");
        sexdata[0]=tmp_sex;
        tmp_sex=new Pair("0","女");
        sexdata[1]=tmp_sex;
        sexspinner = (Spinner) findViewById(R.id.sexspinner);
        sexadapter = new ArrayAdapter<Pair>(this,android.R.layout.simple_spinner_item,sexdata);
        //设置下拉列表的风格
        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        sexspinner.setAdapter(sexadapter);
        //添加事件Spinner事件监听
        sexspinner.setOnItemSelectedListener(new SexSpinnerSelectedListener());
        //设置默认值
        sexspinner.setVisibility(View.VISIBLE);

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
                                String city4_fid=data.getString("cityid");

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

    class SexSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            gender=sexdata[arg2].getkey();

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
