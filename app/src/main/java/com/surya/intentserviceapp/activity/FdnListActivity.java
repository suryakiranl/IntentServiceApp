package com.surya.intentserviceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.surya.intentserviceapp.R;
import com.surya.intentserviceapp.adapter.FdnArrayAdapter;
import com.surya.intentserviceapp.db.dao.DAOFactory;
import com.surya.intentserviceapp.db.dao.IFdnDAO;
import com.surya.intentserviceapp.dto.FdnDTO;

import java.util.List;


public class FdnListActivity extends Activity {
    public static final String LOAD_FDN_LIST = "com.surya.intentserviceapp.activity.FdnListActivity-LOAD_FDN_LIST";
    private ListView fdnListView;
    private List<FdnDTO> fdns;
    private static final String TAG = "FdnListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdn_list);
        Log.i(TAG, "Inside onCreate method");

        String action = getIntent().getAction();
        Log.d(TAG, "Action param = " + action);
        if(action != null && LOAD_FDN_LIST.equals(action)) {
            IFdnDAO fdnDao = DAOFactory.getInstance().getFdnDAO(getApplicationContext());
            fdnDao.open();
            fdns = fdnDao.loadAll();
            fdnDao.close();
        }

        fdnListView = (ListView) findViewById(R.id.fdnList);
        if(fdns == null) {
            Log.i(TAG, "No FDNs available to populate list view.");
            return;
        }

        fdnListView.setAdapter(new FdnArrayAdapter(this, fdns));
        fdnListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FdnDTO fdn = (FdnDTO) fdnListView.getItemAtPosition(position);
                Log.i(TAG, "Item Clicked == " + fdn);

                Intent detailedView = new Intent(getApplicationContext(), SecondPageActivity.class);

            }
        });

        Log.i(TAG, "Exiting onCreate method");
    }



}
