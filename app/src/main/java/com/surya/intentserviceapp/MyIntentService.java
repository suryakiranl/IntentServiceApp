package com.surya.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;
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
        Log.i(TAG, "Inside onHandleIntent method. Intent = " + intent);
        if (intent == null) {
            Log.e(TAG, "Whoops!! Intent passed into " + TAG + " is NULL!");
        }
        final String action = intent.getAction();
        if (ACTION_BOOTUP.equals(action)) {
            String param = intent.getStringExtra(PARAM_BOOTUP_VALUE);
            if(param == null) {
                Log.e(TAG, "Error :: No parameter passed when invoking " + ACTION_BOOTUP);
            }
            handleBootup(param);
        } else {
            Log.e(TAG, "Error :: Invalid action value = " + action);
        }
        Log.i(TAG, "Exiting onHandleIntent method");
    }

    private void handleBootup(String param) {
        Log.i(TAG, "Inside handleBootup method. Param = " + param);


        Log.i(TAG, "Exiting handleBootup method");
    }
}
