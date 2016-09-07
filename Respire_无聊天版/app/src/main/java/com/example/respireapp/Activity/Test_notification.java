package com.example.respireapp.Activity;

/**
 * Created by keke on 2016/7/18.
 */
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.example.respireapp.R;
import com.example.respireapp.Service.RecommandService;

/**
 * Notification
 * @author Administrator
 *
 */
public class Test_notification extends Activity {

    //BaseNotification
    private Button bt01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            /*加载页面*/
        setContentView(R.layout.test_notification);

        init();
    }

    private void init() {
        bt01 = (Button)findViewById(R.id.le10bt01);


        bt01.setOnClickListener(onclick);


        //Intent intent = new Intent(this,Test_notification.class);

        //pd = PendingIntent.getActivity(Test_notification.this, 0, intent, 0);
    }

    OnClickListener onclick = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(Test_notification.this, RecommandService.class);
            startService(intent);

        }
    };


}