package com.surya.intentserviceapp.db.dao;

import android.database.sqlite.SQLiteException;

import com.surya.intentserviceapp.dto.FdnDTO;

/**
 * Created by suryak on 7/11/15.
 */
public interface IFdnDAO {
    void open() throws SQLiteException;

    void close();

    FdnDTO save(FdnDTO fdn);

    FdnDTO create(FdnDTO fdn);

    FdnDTO update(FdnDTO fdn);
}
