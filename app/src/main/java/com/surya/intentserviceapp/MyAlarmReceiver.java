package com.surya.intentserviceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

public class MyAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "MyAlarmReceiver";
    private static long previousTime = 0;
    public MyAlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, ">>> Inside onReceive method");

        long currentTime = new Date().getTime();
        Log.d(TAG, "Current Time when my method is called = " + currentTime);
        if(previousTime == 0) {
            previousTime = currentTime;
        }

        Log.d(TAG, "Time elapsed since previous call = " + (currentTime - previousTime));
        previousTime = currentTime;

        Bundle b = intent.getExtras();
        if(b != null && b.size() > 0) {
            for(String key : b.keySet()) {
                Log.d(TAG, "Key = " + key + " ::: Value = " + b.get(key));
            }
        } else {
            Log.d(TAG, "Intent's bundle is empty!!");
        }

        Log.i(TAG, "<<< Exiting onReceive method");
    }
}
