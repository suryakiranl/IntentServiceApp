package com.surya.intentserviceapp;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

import javax.xml.transform.Result;


public class LauncherActivity extends ActionBarActivity implements MyResultReceiver.Receiver {
    private static final String TAG = "LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickKickOffNowBtn(View view) {
        Log.i(TAG, "Inside onClickKickOffNowBtn method");

        Random r = new Random(new Date().getTime());
        int randomValue = r.nextInt(30);

        Toast t = Toast.makeText(getApplicationContext(), "Kicking off Intent Service with param: " + randomValue, Toast.LENGTH_SHORT);
        t.show();

        Intent intentService = new Intent(Intent.ACTION_SYNC, null, this, MyIntentService.class);
        MyResultReceiver resultReceiver = new MyResultReceiver(new Handler());
        resultReceiver.setReceiver(this);

        intentService.putExtra(MyIntentService.RECEIVER_CLASS, resultReceiver);
        intentService.putExtra(MyIntentService.PARAM_BOOTUP_VALUE, randomValue);
        intentService.setAction(MyIntentService.ACTION_BOOTUP);

        TextView resultValue = (TextView) findViewById(R.id.resultValue);
        resultValue.setText("Request Sent ...");

        startService(intentService);

        Log.i(TAG, "Exiting onClickKickOffNowBtn method");
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Log.i(TAG, "Inside onReceiveResult method");
        Log.d(TAG, "Input params : resultCode = " + resultCode + ", resultData = " + resultData);

        TextView resultValue = (TextView) findViewById(R.id.resultValue);
        String text = "";
        switch(resultCode) {
            case MyIntentService.STATUS_RECEIVED:
                text = "Yay!! I am accepted."; break;
            case MyIntentService.STATUS_PROCESSING:
                text = "Fingers crossed. I am curious about the output."; break;
            case MyIntentService.STATUS_COMPLETED:
                text = "Hurray!! I am done."; break;
            default:
                text = "Invalid result code obtained : " + resultCode;
        }
        resultValue.setText(text);

        Log.i(TAG, "Exiting onReceiveResult method");
    }
}
