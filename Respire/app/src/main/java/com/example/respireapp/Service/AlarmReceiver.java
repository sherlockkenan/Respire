package com.example.respireapp.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by keke on 2016/7/19.
 */
public class AlarmReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        Intent i=new Intent(context,RecommandService.class);
        context.startService(i);
    }
}
