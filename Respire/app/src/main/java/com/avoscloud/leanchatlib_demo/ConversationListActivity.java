package com.avoscloud.leanchatlib_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.Conversation;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.activity.AVBaseActivity;
import com.avoscloud.leanchatlib.activity.AVChatActivity;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;
import com.leancloud.im.guide.AVImClientManager;
import com.leancloud.im.guide.Constants;
import com.leancloud.im.guide.activity.AVSquareActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vito on 7/19/16.
 */
public class ConversationListActivity extends AVBaseActivity {
    public ConversationFragment conversationFragment;
    public Toolbar toolbar;
    String JSESSIONID;
    String username;
    private static final Object TAG = new Object();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_list);

        Bundle bundle=this.getIntent().getExtras();
        JSESSIONID=bundle.getString("sessionid");
        username=bundle.getString("username");

        AVImClientManager.getInstance().open(username, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {

            }
        });

        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConversationListActivity.this.finish();
            }
        });

        conversationFragment = (ConversationFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_c);

        ChatManager.getInstance().openClient(this, username, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
                    //TODO
                } else {
                    //TODO
                }
            }
        });

        Myapp myApp=(Myapp)getApplication();
        String header=myApp.getUrl();
        String url=header+"/getchatroom";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>(){
            public void onResponse(JSONObject result){
                try {
                    String flag = result.getString("return_type");
                    JSONObject data=result.getJSONObject("data");
                    if (flag.equals("success")&&data!=null){
                        final String roomid=data.getString("roomid");
                        final String title=data.getString("title");

                        Button square=(Button) findViewById(R.id.SquareEntry);
                        square.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AVImClientManager.getInstance().open(username, new AVIMClientCallback() {
                                    @Override
                                    public void done(AVIMClient avimClient, AVIMException e) {
                                        if (filterException(e)) {
                                            Intent logIntent = new Intent();
                                            logIntent.setClass(ConversationListActivity.this,AVSquareActivity.class);
                                            logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            Bundle tbundle=new Bundle();
                                            tbundle.putString("CONVERSATION_ID",roomid);
                                            tbundle.putString("ACTIVITY_TITLE",title);
                                            logIntent.putExtras(tbundle);
                                            startActivity(logIntent);
                                        }
                                    }
                                });


//                                Intent logIntent = new Intent();
//                                logIntent.setClass(ConversationListActivity.this,AVSquareActivity.class);
//                                logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                Bundle tbundle=new Bundle();
//                                tbundle.putString("CONVERSATION_ID",roomid);
//                                tbundle.putString("ACTIVITY_TITLE",title);
//                                logIntent.putExtras(tbundle);
//                                startActivity(logIntent);

//                                Intent intent=new Intent(getApplicationContext(), AVChatActivity.class);
//                                intent.putExtra(Constants.CONVERSATION_ID,roomid);
//                                startActivity(intent);
                            }
                        });
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


//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setTitle("respire");
//        setSupportActionBar(toolbar);

    }

}
