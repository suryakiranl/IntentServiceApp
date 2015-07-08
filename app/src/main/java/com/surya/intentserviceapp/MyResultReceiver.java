package com.surya.intentserviceapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * Created by surya on 7/8/15.
 */
public class MyResultReceiver extends ResultReceiver {
    private static final String TAG = "MyResultReceiver";
    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
    private Receiver mReceiver;

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        Log.i(TAG, "Inside setReceiver method.");
        this.mReceiver = receiver;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Log.i(TAG, "Inside onReceiveResult method");
        if(mReceiver != null) {
            Log.d(TAG, "Processing result on to target activity ...");
            mReceiver.onReceiveResult(resultCode, resultData);
        }
        Log.i(TAG, "Exiting onReceiveResult method");
    }
}
