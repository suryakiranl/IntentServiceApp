package com.surya.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public static final String ACTION_BOOTUP = "com.surya.intentserviceapp.action_bootup";
    public static final String PARAM_BOOTUP_VALUE = "com.surya.intentserviceapp.param_bootup_value";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "FDN-PM Service Started!!");
        Log.i(TAG, "Inside onHandleIntent method. Intent = " + intent);
        if (intent == null) {
            Log.e(TAG, "Whoops!! Intent passed into " + TAG + " is NULL!");
            return;
        }
        final String action = intent.getAction();
        if (ACTION_BOOTUP.equals(action)) {
            try {
                Bundle b = intent.getExtras();
                if(b == null || b.size() == 0) {
                    Log.e(TAG, "Error :: No parameter passed when invoking " + ACTION_BOOTUP);
                } else {
                    Object oParam =  b.get(PARAM_BOOTUP_VALUE);
                    if(oParam == null) {
                        Log.e(TAG, "Error :: Missing paramter : " + PARAM_BOOTUP_VALUE);
                    } else {
                        int param = (Integer) oParam;
                        handleBootup(param);
                    }
                }
            } catch(Exception e) {
                Log.e(TAG, "Error when processing request !!!", e);
            }
        } else {
            Log.e(TAG, "Error :: Invalid action value = " + action);
        }
        Log.i(TAG, "Exiting onHandleIntent method");
        Log.i(TAG, "FDN-PM Service Ended!!");
    }

    private int handleBootup(int param) {
        Log.i(TAG, "Inside handleBootup method. Param = " + param);

        int nextVal = param * param + param;

        Log.i(TAG, "Exiting handleBootup method");

        return nextVal;
    }
}
