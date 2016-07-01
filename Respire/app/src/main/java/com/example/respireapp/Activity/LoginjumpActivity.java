package com.example.respireapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.respireapp.R;

public class LoginjumpActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        Bundle bundle=this.getIntent().getExtras();
        String flag=bundle.getString("flag");
        if(flag=="success"){
            setContentView(R.layout.activity_home);
        }
        else{
            TextView information=(TextView)findViewById(R.id.informationText);
            information.setText("用户名或密码错误！");
        }
    }
}
