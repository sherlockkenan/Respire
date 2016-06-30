package com.example.respireapp.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.respireapp.Service.LoginService;
import com.example.respireapp.R;
import android.content.ServiceConnection;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
public class LoginActivity extends Activity {
    ServiceConnection mSc;
    private OnClickListener listener=new OnClickListener(){
        public void onClick(View v){
            boolean result=false;
            String username=((EditText)findViewById(R.id.textUsername)).getText().toString();
            String password=((EditText)findViewById(R.id.passwordText)).getText().toString();
            Intent logIntent = new Intent(LoginActivity.this, LoginService.class);
            logIntent.putExtra("username", username);
            logIntent.putExtra("password", password);
            startService(logIntent);
//            MyReceiver receiver=new MyReceiver();
//            IntentFilter filter=new IntentFilter();
//            filter.addAction("com.ljq.activity.CountService");
//            LoginActivity.this.registerReceiver(receiver,filter);
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageButton button=(ImageButton)findViewById(R.id.loginButton);
        button.setOnClickListener(listener);

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
