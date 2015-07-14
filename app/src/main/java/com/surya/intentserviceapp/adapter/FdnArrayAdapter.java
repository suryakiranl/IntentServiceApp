package com.surya.intentserviceapp.adapter;

import android.content.Context;

import com.surya.intentserviceapp.dto.FdnDTO;

import java.util.List;

public class FdnArrayAdapter extends TwoLineArrayAdapter<FdnDTO>{
    public FdnArrayAdapter(Context context, List<FdnDTO> fdns) {
        super(context, fdns);
    }

    @Override
    public String lineOneText(FdnDTO fdn) {
        return fdn.getContentTitle();
    }

    @Override
    public String lineTwoText(FdnDTO fdn) {
        return fdn.getContentText();
    }
}
