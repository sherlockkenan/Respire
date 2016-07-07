package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.respireapp.Activity.Myapp;
import com.example.respireapp.R;
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

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import com.example.respireapp.Entity.User;
public class ProfileActivity extends Activity {
    private User myuser;
    private Myapp myApp;
    private String JSESSIONID;
    private static final Object TAG = new Object();
    private void getuserprofile(){
        String url="http://192.168.16.130:8000/getprofile";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    if(flag.equals("success")){
                        JSONArray returnvalue = result.getJSONArray("data");
                        JSONObject user=(JSONObject)returnvalue.get(0);
                        String password=user.getString("password");
                        String phone=user.getString("phone");
                        String sex=user.getString("sex");
                        Integer sexid=Integer.parseInt(sex);
                        String userid=user.getString("userid");
                        String email=user.getString("email");
                        String username=user.getString("username");
                        String city1=user.getString("city1");
                        String city2=user.getString("city2");
                        String city3=user.getString("city3");
                        String city4=user.getString("city4");
                        myuser.setPassword(password);
                        myuser.setPhone(phone);
                        myuser.setEmail(email);
                        myuser.setSexid(sexid);
                        myuser.setUserid(userid);
                        myuser.setUsername(username);
                        myuser.setCity1(city1);
                        myuser.setCity2(city2);
                        myuser.setCity3(city3);
                        myuser.setCity4(city4);

                        EditText un=(EditText)findViewById(R.id.usernameText);

                        EditText pw=(EditText)findViewById(R.id.passwordText);
                        EditText ph=(EditText)findViewById(R.id.phoneText);
                        EditText em=(EditText)findViewById(R.id.emailText);
                        EditText sx=(EditText)findViewById(R.id.sexspinner);
                        EditText c1=(EditText)findViewById(R.id.spinner1);
                        EditText c2=(EditText)findViewById(R.id.spinner2);
                        EditText c3=(EditText)findViewById(R.id.spinner3);
                        EditText c4=(EditText)findViewById(R.id.spinner4);
                        un.setText(myuser.getUsername());
                        pw.setText(myuser.getPassword());
                        em.setText(myuser.getEmail());
                        sx.setText(myuser.getSexid());
                        c1.setText(myuser.getCity1());
                        c2.setText(myuser.getCity2());
                        c3.setText(myuser.getCity3());
                        c4.setText(myuser.getCity4());



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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton backButton=(ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.finish();
            }
        });
        myApp=(Myapp)getApplication();
        JSESSIONID=myApp.getSessionid();
        getuserprofile();
    }
}
