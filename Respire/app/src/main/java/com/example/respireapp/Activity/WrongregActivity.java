package com.example.respireapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.respireapp.R;

/**
 * Created by piglet on 2016/7/2.
 */
public class WrongregActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle bundle=this.getIntent().getExtras();
        String wmessage=bundle.getString("wmessage");
        TextView information=(TextView)findViewById(R.id.informationText);
        information.setText(wmessage);

    }
}
