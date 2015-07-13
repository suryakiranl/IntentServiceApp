package com.surya.intentserviceapp.db.dao;

import android.content.Context;

import com.surya.intentserviceapp.db.dao.impl.FdnDAOImpl;

/**
 * Created by suryak on 7/11/15.
 */
public class DAOFactory {
    private static final DAOFactory factory = new DAOFactory();

    public static DAOFactory getInstance() {
        return factory;
    }

    public IFdnDAO getFdnDAO(Context context) {
        return new FdnDAOImpl(context);
    }
}
