package com.example.respireapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.respireapp.Entity.MyBluetooth;
import com.example.respireapp.R;

public class DataActivity extends AppCompatActivity {
    private MyBluetooth myBluetooth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        myBluetooth=new MyBluetooth();

        findViewById(R.id.dataButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] result=new String[4];
                result=myBluetooth.getLines(3);
                TextView dataText=(TextView) findViewById(R.id.dataText);
                dataText.setText(result[0]+result[1]+result[2]+result[3]);
            }
        });

    }
}
