package com.byegor.edrive.localDB;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class HelperFactory {

    private static DBHelper dbHelper;

    public static DBHelper getDbHelper() {
        return dbHelper;
    }

    public static void setHelper(Context context) {
        dbHelper = OpenHelperManager.getHelper(context, DBHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        dbHelper = null;
    }

}
