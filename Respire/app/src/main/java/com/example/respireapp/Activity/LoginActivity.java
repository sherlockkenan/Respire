package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.respireapp.R;
import com.example.respireapp.Service.LoginService;
public class LoginActivity extends Activity {
    private OnClickListener submitlistener=new OnClickListener(){
        public void onClick(View v){
            boolean result=false;
            String username=((EditText)findViewById(R.id.textUsername)).getText().toString();
            String password=((EditText)findViewById(R.id.passwordText)).getText().toString();
            Intent logIntent = new Intent(LoginActivity.this, LoginService.class);
            logIntent.putExtra("username", username);
            logIntent.putExtra("password", password);
            startService(logIntent);
        }

    };
    private OnClickListener registerlistener=new OnClickListener(){
        public void onClick(View v){
            Intent logIntent = new Intent();
            logIntent.setClass(LoginActivity.this,RegisterActivity.class);
            logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle tbundle=new Bundle();
            startActivity(logIntent);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        ImageButton button=(ImageButton)findViewById(R.id.loginButton);
        button.setOnClickListener(submitlistener);
       Button but=(Button)findViewById(R.id.registerbutton);
        but.setOnClickListener(registerlistener);


    }
    public class MyReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
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
}
