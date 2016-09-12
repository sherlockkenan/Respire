package com.example.respireapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.respireapp.R;

public class ItineraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        Button backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItineraryActivity.this.finish();
            }
        });

        Bundle bundle=this.getIntent().getExtras();
        int nowpm25=bundle.getInt("nowpm25");

        TextView info=(TextView) findViewById(R.id.infoText);
        if (nowpm25==0){
            info.setText("当前没有数据");
        }
        if (nowpm25>500){
            info.setText("当前空气污染严重，推荐乘坐地铁出行");
        }
        else{
            info.setText("当前空气质量较好，推荐乘坐公交出行");
        }
    }
}
