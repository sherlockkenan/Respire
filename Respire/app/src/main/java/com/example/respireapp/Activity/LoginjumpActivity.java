package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.respireapp.Entity.Myapp;
import com.example.respireapp.R;

public class LoginjumpActivity extends Activity {
    private Myapp myApp;
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        Bundle bundle=this.getIntent().getExtras();
        String flag=bundle.getString("flag");
        String JSESSIONID=bundle.getString("sessionid");
        if(flag.equals("success")){
            Intent logIntent = new Intent();
            logIntent.setClass(LoginjumpActivity.this,PageActivity.class);
            logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle tbundle=new Bundle();
            tbundle.putString("sessionid",JSESSIONID);
            logIntent.putExtras(tbundle);

            myApp=(Myapp) getApplication();
            myApp.setSessionid(JSESSIONID);

            startActivity(logIntent);
            //LoginjumpActivity.this.finish();
        }
        else if(flag.equals("fail")){
//            Intent logIntent = new Intent();
//            logIntent.setClass(LoginjumpActivity.this,LoginActivity.class);
//            logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            Bundle tbundle=new Bundle();
//            tbundle.putString("result","用户名或密码错误！");
//            logIntent.putExtras(tbundle);
//            startActivity(logIntent);
//            LoginjumpActivity.this.finish();

            setContentView(R.layout.activity_login);
            TextView information=(TextView)findViewById(R.id.informationText);
            information.setText("用户名或密码错误！");
        }
    }
}
