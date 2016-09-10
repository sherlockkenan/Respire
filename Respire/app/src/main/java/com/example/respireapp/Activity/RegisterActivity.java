package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class RegisterActivity extends Activity {
    private static final Pair[] sexdata=new Pair[2];

    private Spinner sexspinner;

    private Spinner city1spinner;

    private Spinner city2spinner;

    private Spinner city3spinner;

    private Spinner city4spinner;
    private ArrayAdapter<Pair> sexadapter;
    private ArrayAdapter<Pair> city1adapter;
    private ArrayAdapter<Pair> city2adapter;
    private ArrayAdapter<Pair> city3adapter;
    private ArrayAdapter<Pair> city4adapter;
    private String username;
    private String password;
    private String passwordagain;
    private String phone;
    private String email;
    private String gender;
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
    private static final Object TAG = new Object();
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
    //判断手机格式是否正确
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
    public boolean isPassword(String password){
        //String str = "^(?=.*?[A-Z])[a-zA-Z0-9]{6,}$";
        String str = "^(?=.*?)[a-zA-Z0-9]{6,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);

        return m.matches();
    }
    private View.OnClickListener listener=new View.OnClickListener(){
        public void onClick(View v){
            username=((EditText)findViewById(R.id.usernameText)).getText().toString();
            password=((EditText)findViewById(R.id.passwordText)).getText().toString();
            passwordagain=((EditText)findViewById(R.id.passwordText2)).getText().toString();
            phone=((EditText)findViewById(R.id.phoneText)).getText().toString();
            email=((EditText)findViewById(R.id.emailText)).getText().toString();
            //判断是不是非空
            String wmessage="";
            boolean wrong=false;
            if(username.equals("")){
                wrong=true;
                wmessage="用户名为必填项！";
            }
            else if(password.equals("")){
                wrong=true;
                wmessage="密码为必填项！";
            }
            else if(passwordagain.equals("")){
                wrong=true;
                wmessage="确认密码为必填项！";
            }
            else if(phone.equals("")){
                wrong=true;
                wmessage="电话号码为必填项！";
            }
            else if(email.equals("")){
                wrong=true;
                wmessage="邮箱为必填项！";
            }
            else if(gender.equals("")){
                wrong=true;
                wmessage="性别为必填项！";
            }
            else if(!password.equals(passwordagain)){
                wrong=true;
                wmessage="确认密码不正确!";
            }
            if(!isEmail(email)){
                wrong=true;
                wmessage="邮箱格式不正确!";
            }
            if(!isMobileNO(phone)){
                wrong=true;
                wmessage="手机格式不正确!";
            }
            if(!isPassword(password)){
                wrong=true;
                wmessage="密码格式不正确!";
            }
            if(wrong==true){
//                TextView information=(TextView)findViewById(R.id.informationText);
//                information.setText(wmessage);
                Toast.makeText(getApplicationContext(), wmessage,
                        Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(getApplicationContext(), "注册成功请登录",
                        Toast.LENGTH_SHORT).show();
                sendregisterrequest();
            }
        }

    };
    public void sendregisterrequest(){
        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/register";
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("username",username);
        map.put("password",password);
        map.put("phone",phone);
        map.put("email",email);
        map.put("sex",gender);
        String role="1";
        map.put("role",role);
        map.put("cityid",city4_fid);
        JSONObject object=new JSONObject(map);
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,
                object, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String returnvalue=result.getString("return_type");
                    if(returnvalue.equals("success")){
                        String message="注册成功请登录！";
                        Intent logIntent = new Intent();
                        logIntent.setClass(RegisterActivity.this,SuccessregActivity.class);
                        logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle=new Bundle();
                        bundle.putString("message",message);
                        logIntent.putExtras(bundle);
                        startActivity(logIntent);
                    }
                    else if(returnvalue.equals("fail")){
                        String wmessage=result.getString("data");
                        TextView information=(TextView)findViewById(R.id.informationText);
                        information.setText(wmessage);
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
                    city3data = new Pair[returnvalue.length()];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");

                        Pair tmp=new Pair(cityid,name);
                        city3data[i]=tmp;
                    }
                    city3adapter = new ArrayAdapter<Pair>(RegisterActivity.this,android.R.layout.simple_spinner_item,city3data);
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
                    city4data = new Pair[returnvalue.length()];
                    if(returnvalue.length()>0) {
                        for (int i = 0; i < returnvalue.length(); ++i) {
                            JSONObject temp = (JSONObject) returnvalue.get(i);
                            String name = temp.getString("name");
                            String cityid = temp.getString("cityid");
                            Pair tmp = new Pair(cityid, name);
                            city4data[i] = tmp;
                        }
                        city4adapter = new ArrayAdapter<Pair>(RegisterActivity.this, android.R.layout.simple_spinner_item, city4data);
                        //设置下拉列表的风格
                        city4adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //将adapter 添加到spinner中
                        city4spinner.setAdapter(city4adapter);
                        //添加事件Spinner事件监听
                        city4spinner.setOnItemSelectedListener(new City4SpinnerSelectedListener());
                        //设置默认值
                        city4spinner.setVisibility(View.VISIBLE);
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
                    city2data = new Pair[returnvalue.length()];
                    if(returnvalue.length()>0) {
                        for (int i = 0; i < returnvalue.length(); ++i) {
                            JSONObject temp = (JSONObject) returnvalue.get(i);
                            String name = temp.getString("name");
                            String cityid = temp.getString("cityid");
                            Pair tmp = new Pair(cityid, name);
                            city2data[i] = tmp;
                        }
                        city2adapter = new ArrayAdapter<Pair>(RegisterActivity.this, android.R.layout.simple_spinner_item, city2data);
                        //设置下拉列表的风格
                        city2adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //将adapter 添加到spinner中
                        city2spinner.setAdapter(city2adapter);
                        //添加事件Spinner事件监听
                        city2spinner.setOnItemSelectedListener(new City2SpinnerSelectedListener());
                        //设置默认值
                        city2spinner.setVisibility(View.VISIBLE);
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
                    city1data = new Pair[returnvalue.length()];
                    for(int i=0;i<returnvalue.length();++i){
                        JSONObject temp= (JSONObject)returnvalue.get(i);
                        String name=temp.getString("name");
                        String cityid=temp.getString("cityid");
                        Pair tmp=new Pair(cityid,name);
                        city1data[i]=tmp;

                    }
                    city1adapter = new ArrayAdapter<Pair>(RegisterActivity.this,android.R.layout.simple_spinner_item,city1data);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //性别下拉框
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
        //city1
        sendcity1request("0");
        city1spinner = (Spinner) findViewById(R.id.spinner1);
        city2spinner = (Spinner) findViewById(R.id.spinner2);
        city3spinner=(Spinner)findViewById(R.id.spinner3);
        city4spinner=(Spinner)findViewById(R.id.spinner4);
        //传回登录数据
        Button button=(Button)findViewById(R.id.submitButton);
        button.setOnClickListener(listener);

        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }
    class City4SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            city4_fid=((Pair)city4spinner.getSelectedItem()).getkey();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            System.out.println("4");
        }
    }
    class City3SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            //city4data=new Pair[1];
            city3_fid=((Pair)city3spinner.getSelectedItem()).getkey();
            sendcity4request(city3_fid);


        }

        public void onNothingSelected(AdapterView<?> arg0) {
            System.out.println("3");
        }
    }

    class City2SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
           // city3data=new Pair[1];
            //city4data=new Pair[1];
            city2_fid=((Pair)city2spinner.getSelectedItem()).getkey();
            sendcity3request(city2_fid);



        }

        public void onNothingSelected(AdapterView<?> arg0) {
            System.out.println("2");
        }
    }
    public class City1SpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            //city2data=new Pair[1];
            //city3data=new Pair[1];
            //city4data=new Pair[1];

            city1_fid=((Pair)city1spinner.getSelectedItem()).getkey();
            sendcity2request(city1_fid);

        }
        public void onNothingSelected(AdapterView<?> arg0) {
            System.out.println("1");
        }
    }
    //使用数组形式操作
    class SexSpinnerSelectedListener implements OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            gender=sexdata[arg2].getkey();

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

}
