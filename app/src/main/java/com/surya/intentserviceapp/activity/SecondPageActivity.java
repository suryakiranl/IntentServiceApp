package com.surya.intentserviceapp.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.surya.intentserviceapp.R;


public class SecondPageActivity extends Activity {
    private static final String TAG = "SecondPageActivity";
    public static final String ACTION_NAVIGATE = "com.surya-SecondPageActivity.ACTION_NAVIGATE";
    private static final String ACTION_CLICK = "com.surya-SecondPageActivity.ACTION_CLICK";
    private static final String ACTION_DISMISS = "com.surya-SecondPageActivity.ACTION_DISMISS";
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Inside onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        if(getIntent() != null) {
            Intent intent = getIntent();
            String action = intent.getAction();

            Log.d(TAG, "Intent Action = " + action);
            Log.d(TAG, "Intent's Extra = " + intent.getStringExtra(action));

            TextView statusText = (TextView) findViewById(R.id.statusText);
            String text = "";

            switch(action) {
                case ACTION_CLICK: text = "Clicked it!!"; break;
                case ACTION_DISMISS: text = "Dismissed :("; break;
                case  ACTION_NAVIGATE: text = "Navigation - Hi There!!"; break;
                default: text = "NO-ACTION"; break;
            }
            statusText.setText(text);
        }

        Log.i(TAG, "Exiting onCreate method");
    }

    public void onPostNotificationClick(View view) {
        Log.i(TAG, "Inside onPostNotificationClick method");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_template_icon_bg)
                .setContentTitle("Blah Blah Title")
                .setContentText("Blah Blah Blah - This is my text");

        Intent onClickIntent = new Intent(this, SecondPageActivity.class);
        onClickIntent.setAction(ACTION_CLICK);
        onClickIntent.putExtra(ACTION_CLICK, "Surya");
        PendingIntent onClickPendingIntent = PendingIntent.getActivity(getApplicationContext(), 12345, onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent onClearIntent = new Intent(this, SecondPageActivity.class);
        onClearIntent.setAction(ACTION_DISMISS);
        onClearIntent.putExtra(ACTION_DISMISS, "Kiran");
        PendingIntent onClearPendingIntent = PendingIntent.getActivity(getApplicationContext(), 54321, onClearIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(onClickPendingIntent);
        builder.setDeleteIntent(onClearPendingIntent);
        builder.setAutoCancel(true);

        NotificationManager notifMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifMgr.notify(notificationId, builder.build());

        Log.i(TAG, "Exiting onPostNotificationClick method");
    }
}
