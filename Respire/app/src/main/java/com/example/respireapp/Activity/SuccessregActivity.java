package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.respireapp.R;
import com.example.respireapp.Service.LoginService;

/**
 * Created by piglet on 2016/7/2.
 */
public class SuccessregActivity extends Activity {
    private View.OnClickListener listener=new View.OnClickListener(){
        public void onClick(View v){
            boolean result=false;
            String username=((EditText)findViewById(R.id.textUsername)).getText().toString();
            String password=((EditText)findViewById(R.id.passwordText)).getText().toString();
            Intent logIntent = new Intent(SuccessregActivity.this, LoginService.class);
            logIntent.putExtra("username", username);
            logIntent.putExtra("password", password);
            startService(logIntent);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton button=(ImageButton)findViewById(R.id.loginButton);
        button.setOnClickListener(listener);
        Bundle bundle=this.getIntent().getExtras();
        String message=bundle.getString("message");
//        TextView information=(TextView)findViewById(R.id.informationText);
//        information.setText(message);
        Toast.makeText(getApplicationContext(), "注册成功请登录",
                Toast.LENGTH_SHORT).show();

    }
}
