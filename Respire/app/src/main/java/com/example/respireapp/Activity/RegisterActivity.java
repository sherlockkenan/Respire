package com.example.respireapp.Activity;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.respireapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends Activity {
    private static final String[] m = {"男", "女"};

    private Spinner sexspinner;

    private Spinner city1spinner;

    private Spinner city2spinner;

    private Spinner city3spinner;

    private Spinner city4spinner;
    private ArrayAdapter<String> sexadapter;
    private ArrayAdapter<String> city1adapter;
    private ArrayAdapter<String> city2adapter;
    private String username;
    private String password;
    private String passwordagain;
    private String phone;
    private String email;
    private String gender;
    private String city1_fid;
    private String city2_fid;
    private String city3_fid;
    private String city4_fid;
    private Map<String, String> citymap;
    private String[] citylist = new String[]{};
    private static final Object TAG = new Object();

    public void sendcityrequest(String fatherid) {
        String url = "http://192.168.16.61:8000/getcitylist?fatherid=0";
//        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("fatherid",fatherid);
//        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject result) {
                try {
                    JSONArray returnvalue = result.getJSONArray("data");

                    for (int i = 0; i < returnvalue.length(); ++i) {
                        JSONObject temp = (JSONObject) returnvalue.get(i);
                        String name = temp.getString("name");
                        String cityid = temp.getString("cityid");
                        citymap.put(name, cityid);
                        citylist[i] = name;

                    }
                } catch (JSONException e) {
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
        password = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        passwordagain = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        phone = ((EditText) findViewById(R.id.phoneText)).getText().toString();
        email = ((EditText) findViewById(R.id.emailText)).getText().toString();
        //性别下拉框

        sexspinner = (Spinner) findViewById(R.id.sexspinner);
        sexadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        //设置下拉列表的风格
        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        sexspinner.setAdapter(sexadapter);
        //添加事件Spinner事件监听
        sexspinner.setOnItemSelectedListener(new SexSpinnerSelectedListener());
        //设置默认值
        sexspinner.setVisibility(View.VISIBLE);
        //city1
        city1_fid = "0";
        sendcityrequest(city1_fid);
        city1spinner = (Spinner) findViewById(R.id.spinner1);
        city1adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citylist);
        //设置下拉列表的风格
        city1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        city1spinner.setAdapter(city1adapter);
        //添加事件Spinner事件监听
        city1spinner.setOnItemSelectedListener(new City1SpinnerSelectedListener());
        //设置默认值
        city1spinner.setVisibility(View.VISIBLE);
        //city2
        citymap = new HashMap<String, String>();
        citylist = new String[]{};
        sendcityrequest(city2_fid);
        city2spinner = (Spinner) findViewById(R.id.spinner2);
        city2adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citylist);
        //设置下拉列表的风格
        city2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        city2spinner.setAdapter(city2adapter);
        //添加事件Spinner事件监听
        city2spinner.setOnItemSelectedListener(new City2SpinnerSelectedListener());
        //设置默认值
        city2spinner.setVisibility(View.VISIBLE);
        //city3
    }

    class City2SpinnerSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city3_fid = citylist[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    class City1SpinnerSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city2_fid = citylist[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    //使用数组形式操作
    class SexSpinnerSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            gender = m[arg2];

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}

