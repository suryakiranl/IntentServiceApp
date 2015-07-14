package com.surya.intentserviceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.surya.intentserviceapp.R;
import com.surya.intentserviceapp.dto.FdnDTO;

public class FdnDetailsActivity extends Activity {
    private static final String TAG = "FdnDetailsActivity";
    public static final String PARAM_FDN = "com.surya.intentserviceapp.activity.FdnDetailsActivity-PARAM_FDN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdn_details);

        Log.i(TAG, "Inside create method");
        FdnDTO fdn = (FdnDTO) getIntent().getSerializableExtra(PARAM_FDN);
        if(fdn == null) {
            Log.i(TAG, "No FDN passed to display details page.");
            return;
        }

        Log.d(TAG, "Populating details screen for FDN = " + fdn);
        ((TextView)findViewById(R.id.idValue)).setText(fdn.getId() + "");
        ((TextView)findViewById(R.id.fdnId)).setText(fdn.getFdnId());
        ((TextView)findViewById(R.id.contentTitle)).setText(fdn.getContentTitle());
        ((TextView)findViewById(R.id.contentText)).setText(fdn.getContentText());
        ((TextView)findViewById(R.id.andrPriority)).setText(fdn.getAndroidPriority() + "");
        ((TextView)findViewById(R.id.isDisplayed)).setText(fdn.getIsDisplayed() + "");
        ((TextView)findViewById(R.id.displayCount)).setText(fdn.getDisplayCount() + "");
        ((TextView)findViewById(R.id.isConsumedByUser)).setText(fdn.getIsConsumedByUser() + "");
        ((TextView)findViewById(R.id.userAction)).setText(fdn.getUserAction());
        ((TextView)findViewById(R.id.userActionTimestamp)).setText(fdn.getUserActionTimestamp() + "");
        ((TextView)findViewById(R.id.createdTimestamp)).setText(fdn.getCreatedTimestamp() + "");
        ((TextView)findViewById(R.id.modifiedTimestamp)).setText(fdn.getModifiedTimestamp() + "");
        ((TextView)findViewById(R.id.lastDisplayedTimestamp)).setText(fdn.getLastDisplayedTimestamp() + "");

        Log.i(TAG, "Exiting create method");
    }
}
