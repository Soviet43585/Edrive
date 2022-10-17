package com.byegor.edrive.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "machines";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE machine (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "fuel INTEGER NOT NULL); ");
        db.execSQL("CREATE TABLE consumption (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "name TEXT NOT NULL, " +
                "fuel_consumption FLOAT NOT NULL, " +
                "conventional_unit FLOAT NOT NULL, " +
                "machine_id INTEGER NOT NULL, " +
                "FOREIGN KEY(machine_id) REFERENCES machine(id));");
        db.execSQL("CREATE TABLE job ( " +
                "machine_id INTEGER NOT NULL, " +
                "consumption_id INTEGER NOT NULL, " +
                "date DATE NOT NULL, " +
                "job INTEGER NOT NULL, " +
                "FOREIGN KEY(machine_id) REFERENCES machine(id), " +
                "FOREIGN KEY(consumption_id) REFERENCES consumption(id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS machine; ");
        db.execSQL("DROP TABLE IF EXISTS consumption;");
        db.execSQL("DROP TABLE IF EXISTS job; ");
        onCreate(db);
    }

}
