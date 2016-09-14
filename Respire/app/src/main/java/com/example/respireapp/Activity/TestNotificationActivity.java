package com.example.respireapp.Activity;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.example.respireapp.Entity.Place;
import com.example.respireapp.Entity.Scenery;
import com.example.respireapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestNotificationActivity extends Activity {
    private static final int NOTIFICATION_FLAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_notification);
    }

    public void notificationMethod(View view) {
        // 在Android进行通知处理，首先需要重系统哪里获得通知管理器NotificationManager，它是一个系统Service。
       // NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(TestNotificationActivity.this);
        Intent intent = new Intent(this, TestShowrecommandActivity.class);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        switch (view.getId()) {
            // 默认通知


            case R.id.btn1:

                SystemClock.sleep(5000);
                //设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.drawable.ricon);
                builder.setContentTitle("Recommandation");
                String information1="Middle level pollution Take the bus to work";
                builder.setContentText(information1);


                intent.putExtra("recommand",information1);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent1);

                notificationManager.notify(0, builder.build());
                break;
            // 默认通知 API11及之后可用


            case R.id.btn2:

                SystemClock.sleep(5000);
                //设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.drawable.ricon);
                builder.setContentTitle("Recommandation");
                String information2="Remember to put on your mask";
                builder.setContentText(information2);


                intent.putExtra("recommand",information2);
                PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent2);
                notificationManager.notify(0, builder.build());
                break;

            case R.id.btn3:

                SystemClock.sleep(5000);
                //设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.drawable.ricon);
                builder.setContentTitle("Recommandation");
                String information3="Carbon dioxide is on high level.Open the window!";
                builder.setContentText(information3);


                intent.putExtra("recommand",information3);
                PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent3);
                notificationManager.notify(0, builder.build());
                break;


            case R.id.btn4:

                SystemClock.sleep(5000);
                //设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.drawable.ricon);
                builder.setContentTitle("Recommandation");
                String information4="I think you want to go there";
                builder.setContentText(information4);
                Intent newintent = new Intent(this, SceneryInfoActivity.class);

                List<Scenery> sceneries=new ArrayList<>();
                Scenery scenery=new Scenery();
                scenery.setId("4028825d571418f1015718fb466d0011");
                scenery.setDescription("good place to rest");
                scenery.setUsername("keke");
                scenery.setTime("2016-09-15 9:21:43");
                scenery.setPhoto("/image/1.jpg;/image/2.jpg;");
                scenery.setPm25(23);
                sceneries.add(scenery);

                Place place=new Place();
                place.setSceneries(sceneries);
                Gson gson = new Gson();
                String result = gson.toJson(place);
                Bundle bundle=new Bundle();
                bundle.putString("place",result);
                newintent.putExtras(bundle);

                newintent.putExtra("scenery",sceneries.toString());
                newintent.putExtra("recommand",information4);
                PendingIntent pendingIntent4 = PendingIntent.getActivity(this, 0, newintent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent4);
                notificationManager.notify(0, builder.build());
                break;


            case R.id.btn5:

                SystemClock.sleep(5000);
                //设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.drawable.ricon);
                builder.setContentTitle("Recommandation");
                String information5="The air quality of your night jogging route is poor.Execise indoor will be more healthy.";
                builder.setContentText(information5);


                intent.putExtra("recommand",information5);
                PendingIntent pendingIntent5 = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent5);

                notificationManager.notify(0, builder.build());
                break;


            default:
                break;
        }
    }
}
