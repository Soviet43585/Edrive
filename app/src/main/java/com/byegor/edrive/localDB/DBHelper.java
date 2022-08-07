package com.byegor.edrive.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "machines";
    public static final String TABLE_MACHINES = "machines";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_FUEL_CONSUMPTION = "fuelConsumption";
    public static final String KEY_CONVENTIONAL_UNITS = "conventionalUnits";
    public static final String KEY_FUEL = "fuel";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_MACHINES + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_FUEL_CONSUMPTION + " text," + KEY_CONVENTIONAL_UNITS + " text," + KEY_FUEL + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_MACHINES);

        onCreate(db);
    }

}
