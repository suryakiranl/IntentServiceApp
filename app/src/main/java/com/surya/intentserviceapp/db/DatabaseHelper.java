package com.surya.intentserviceapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by suryak on 7/10/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TBL_INCOMING_FDN = " pm_incoming_fdn ";
    public static final String COL_ID = " _id ";
    public static final String COL_FDN_ID = " fdn_id ";
    public static final String COL_ICON = " icon ";
    public static final String COL_CONTENT_TITLE = " content_title ";
    public static final String COL_CONTENT_TEXT = " content_text ";
    public static final String COL_ANDROID_PRIORITY = " android_priority ";
    public static final String COL_IS_DISPLAYED = " is_displayed ";
    public static final String COL_DISPLAY_COUNT = " display_count ";
    public static final String COL_IS_CONSUMED_BY_USER = " is_consumed_by_user ";
    public static final String COL_USER_ACTION = " user_action ";
    public static final String COL_USER_ACTION_TIMESTAMP = " user_action_timestamp ";
    public static final String COL_CREATED_TIMESTAMP = " created_timestamp ";
    public static final String COL_MODIFIED_TIMESTAMP = " modified_timestamp ";
    public static final String COL_LAST_DISPLAYED_TIMESTAMP = " last_displayed_timestamp ";
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "com.surya.intentserviceapp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Inside onCreate method");
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE ").append(TBL_INCOMING_FDN).append("( ")
                .append(COL_ID).append("integer primary key autoincrement,")
                .append(COL_FDN_ID).append("text not null,")
                .append(COL_ICON).append("blob,")
                .append(COL_CONTENT_TITLE).append("text not null,")
                .append(COL_CONTENT_TEXT).append("text,")
                .append(COL_ANDROID_PRIORITY).append("integer not null,")
                .append(COL_IS_DISPLAYED).append("integer not null,")
                .append(COL_DISPLAY_COUNT).append("integer not null,")
                .append(COL_IS_CONSUMED_BY_USER).append("integer not null,")
                .append(COL_USER_ACTION).append("text,")
                .append(COL_USER_ACTION_TIMESTAMP).append("integer,")
                .append(COL_CREATED_TIMESTAMP).append("integer not null,")
                .append(COL_MODIFIED_TIMESTAMP).append("integer,")
                .append(COL_LAST_DISPLAYED_TIMESTAMP).append("integer")
                .append(" );");

        final String TBL_INCOMING_FDN_CREATE = sb.toString();
        Log.d(TAG, "Creating table " + TBL_INCOMING_FDN);
        Log.d(TAG, "Using Create SQL == " + TBL_INCOMING_FDN_CREATE);

        db.execSQL(TBL_INCOMING_FDN_CREATE);

        Log.i(TAG, "Exiting onCreate method");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Inside onUpgrade method");
        Log.d(TAG, "Parameters :: oldVersion = " + oldVersion + ", newVersion = " + newVersion);
        Log.i(TAG, "!!! Doing nothing as part of DB UPGRADE !!!");
        Log.i(TAG, "Exiting onUpgrade method");
    }
}
