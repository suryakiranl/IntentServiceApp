package com.surya.intentserviceapp.db.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.surya.intentserviceapp.db.DatabaseHelper;
import com.surya.intentserviceapp.db.dao.IFdnDAO;
import com.surya.intentserviceapp.dto.FdnDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by suryak on 7/10/15.
 */
public class FdnDAOImpl implements IFdnDAO {
    private static final String TAG = "FdnDAOImpl";
    private final String[] ALL_COLUMNS = {
        DatabaseHelper.COL_ID, DatabaseHelper.COL_FDN_ID, DatabaseHelper.COL_ICON,
        DatabaseHelper.COL_CONTENT_TITLE, DatabaseHelper.COL_CONTENT_TEXT,
        DatabaseHelper.COL_ANDROID_PRIORITY, DatabaseHelper.COL_IS_DISPLAYED,
        DatabaseHelper.COL_DISPLAY_COUNT, DatabaseHelper.COL_IS_CONSUMED_BY_USER,
        DatabaseHelper.COL_USER_ACTION, DatabaseHelper.COL_USER_ACTION_TIMESTAMP,
        DatabaseHelper.COL_CREATED_TIMESTAMP, DatabaseHelper.COL_MODIFIED_TIMESTAMP,
        DatabaseHelper.COL_LAST_DISPLAYED_TIMESTAMP
    };
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public FdnDAOImpl(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        db.close();
    }

    @Override
    public FdnDTO save(FdnDTO fdn) {
        Log.i(TAG, "Inside save method");

        if(fdn == null) { return null; }

        if(fdn.getId() == 0) {
            fdn = create(fdn);
        } else {
            fdn = update(fdn);
        }

        Log.i(TAG, "Exiting save method");
        return fdn;
    }


    @Override
    public FdnDTO create(FdnDTO fdn) {
        Log.i(TAG, "Inside create method");
        Log.d(TAG, "Input params = " + fdn.toString());

        ContentValues params = new ContentValues();
        params.put(DatabaseHelper.COL_ANDROID_PRIORITY, fdn.getAndroidPriority());
        params.put(DatabaseHelper.COL_CONTENT_TEXT, fdn.getContentText());
        params.put(DatabaseHelper.COL_CONTENT_TITLE, fdn.getContentTitle());
        params.put(DatabaseHelper.COL_CREATED_TIMESTAMP, fdn.getCreatedTimeAsLong());
        params.put(DatabaseHelper.COL_DISPLAY_COUNT, fdn.getDisplayCount());
        params.put(DatabaseHelper.COL_FDN_ID, fdn.getFdnId());
        params.put(DatabaseHelper.COL_ICON, fdn.getIcon());
        params.put(DatabaseHelper.COL_IS_CONSUMED_BY_USER, fdn.getIsConsumedByUser());
        params.put(DatabaseHelper.COL_IS_DISPLAYED, fdn.getIsDisplayed());
        params.put(DatabaseHelper.COL_LAST_DISPLAYED_TIMESTAMP, fdn.getLastDisplayedTimeAsLong());
        params.put(DatabaseHelper.COL_MODIFIED_TIMESTAMP, fdn.getModifiedTimeAsLong());
        params.put(DatabaseHelper.COL_USER_ACTION, fdn.getUserAction());
        params.put(DatabaseHelper.COL_USER_ACTION_TIMESTAMP, fdn.getUserActionTimeAsLong());

        boolean didWeOpenDBNow = false;
        if(db == null) {
            open();
            didWeOpenDBNow = true;
        }

        long insertedRowId = db.insert(DatabaseHelper.TBL_INCOMING_FDN, null, params);
        String[] selectionArgs = { insertedRowId + "" };
        Cursor results = db.query(DatabaseHelper.TBL_INCOMING_FDN, // Table Name
                ALL_COLUMNS, // Selection Columns
                DatabaseHelper.COL_ID + " = ?", // Selection Criterion
                selectionArgs, // Selection Arguments
                null, // Group by
                null, // Having clause
                null); // Order by
        if(results == null || results.getCount() == 0) {
            Log.e(TAG, "Something went wrong with either insertion of selection of FDN. " +
                    "Unsuccessful to fetch newly inserted record with ID = " + insertedRowId);
        }

        Log.d(TAG, "Count of records fetched = " + results.getCount());
        results.moveToFirst();
        List<FdnDTO> fdns = convert(results);
        fdn = fdns.get(0);

        results.close();
        if(didWeOpenDBNow) {
            close();
        }

        Log.d(TAG, "Return value = " + fdn.toString());
        Log.i(TAG, "Inside create method");

        return fdn;
    }

    @Override
    public FdnDTO update(FdnDTO fdn) {
        Log.i(TAG, "Inside update method");


        Log.i(TAG, "Inside update method");
        return fdn;
    }

    private List<FdnDTO> convert(Cursor results) {
        Log.i(TAG, "Inside convert method");
        List<FdnDTO> fdns = new ArrayList<FdnDTO>();

        if(results != null) {
            FdnDTO fdn = new FdnDTO();
            fdn.setId(results.getLong(0));
            fdn.setFdnId(results.getString(1));
            fdn.setIcon(results.getString(2));
            fdn.setContentTitle(results.getString(3));
            fdn.setContentText(results.getString(4));
            fdn.setAndroidPriority(results.getInt(5));
            fdn.setIsDisplayed(results.getInt(6));
            fdn.setDisplayCount(results.getInt(7));
            fdn.setIsConsumedByUser(results.getInt(8));
            fdn.setUserAction(results.getString(9));
            fdn.setUserActionTimestamp(new Date(results.getLong(10)));
            fdn.setCreatedTimestamp(new Date(results.getLong(11)));
            fdn.setModifiedTimestamp(new Date(results.getLong(12)));
            fdn.setLastDisplayedTimestamp(new Date(results.getLong(13)));

            if(!results.isAfterLast()) {
                results.moveToNext();
            }

            fdns.add(fdn);
        }

        Log.i(TAG, "Inside convert method");
        return fdns;
    }
}
