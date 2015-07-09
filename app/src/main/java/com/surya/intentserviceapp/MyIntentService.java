package com.surya.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public static final String RESULT_STRING_KEY = "com.surya.intentserviceapp.result_string_key";
    public static final String RESULT_VALUE_KEY = "com.surya.intentserviceapp.result_value_key";
    public static final String RECEIVER_CLASS = "com.surya.intentserviceapp.receiver_class";
    public static final String ACTION_BOOTUP = "com.surya.intentserviceapp.action_bootup";
    public static final String PARAM_BOOTUP_VALUE = "com.surya.intentserviceapp.param_bootup_value";

    public static final int STATUS_RECEIVED = 0;
    public static final int STATUS_PROCESSING = 1;
    public static final int STATUS_COMPLETED = 2;

    public MyIntentService() {
        super("MyIntentService");
    }
    private ResultReceiver mResultReceiver;

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "FDN-PM Service Started!!");
        Log.i(TAG, "Inside onHandleIntent method. Intent = " + intent);
        if (intent == null) {
            Log.e(TAG, "Whoops!! Intent passed into " + TAG + " is NULL!");
            return;
        }

        mResultReceiver = intent.getParcelableExtra(RECEIVER_CLASS);
        if(mResultReceiver == null) {
            Log.e(TAG, "Error :: Missing result receiver variable");
        }
        sendUpdateToReceiver(STATUS_RECEIVED, "Request received");
        sleep(2000);

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
                        sendUpdateToReceiver(STATUS_PROCESSING, "Processing your request has begun");
                        int returnValue = handleBootup(param);
                        sendUpdateToReceiver(STATUS_COMPLETED, "Calculated response sent", returnValue);
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

    /**
     * This function handles actions that happen during boot up.
     *
     * @param param - Parameter from caller
     * @return - Calculated value
     */
    private int handleBootup(int param) {
        Log.i(TAG, "Inside handleBootup method. Param = " + param);

        int nextVal = -1;

        sleep(5000);
        nextVal = param * param + param;

        Log.i(TAG, "Exiting handleBootup method");

        return nextVal;
    }

    /**
     * Utility method to send status updates to the registered receiver.
     *
     * @param code - Integer Code defining status
     * @param message - Actual text message
     * @param value - Extra Integer value to be sent if any
     */
    private void sendUpdateToReceiver(int code, String message, int... value) {
        Log.i(TAG, "Inside sendUpdateToReceiver method");
        Log.d(TAG, "Params: code = " + code + ", message = " + message + ", value = " + value);

        if(mResultReceiver == null) {
            Log.e(TAG, "Cannot send response as receiver is not initialized.");
            return;
        }

        Bundle mBundle = new Bundle();
        mBundle.putString(RESULT_STRING_KEY, message);
        if(value.length > 0) {
            mBundle.putInt(RESULT_VALUE_KEY, value[0]);
        }

        mResultReceiver.send(code, mBundle);
        Log.i(TAG, "Exiting sendUpdateToReceiver method");
    }

    /**
     * Utility method to sleep the current Thread
     *
     * @param millis - Number in milli seconds to sleep the thread for.
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Log.e(TAG, "Error when waiting for thread to sleep", e);
        }
    }


}
