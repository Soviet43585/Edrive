package com.byegor.edrive.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.byegor.edrive.localDB.DBHelper;
import com.byegor.edrive.model.Machine;

import java.util.ArrayList;
import java.util.List;

public class MachineRepository {

    SQLiteDatabase db;
    DBHelper dbHelper;
    ContentValues cv = new ContentValues();
    Cursor cursor;

    public MachineRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        db = dbHelper.getWritableDatabase();
    }

    public void create(Machine machine) {
        cv.put("name", machine.getName());
        cv.put("fuel", machine.getFuel());
        db.insert("machine", null, cv);
        cv.clear();
    }

    public List<Machine> getAll() {
        cursor = db.query("machine", null, null, null, null, null, null);
        List<Machine> machineList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex(" _id");
            int nameColIndex = cursor.getColumnIndex("name");
            int fuelColIndex = cursor.getColumnIndex("fuel");
            do {
                machineList.add(new Machine(cursor.getInt(idColIndex), cursor.getString(nameColIndex), cursor.getInt(fuelColIndex)));
            }
            while (cursor.moveToNext());
            cursor.close();
            return machineList;
        }
        cursor.close();
        return null;
    }

    public Machine getMachineById(int id) {
        Cursor cursor = db.query("machine", new String[]{"_id = ?"}, id + "", null, null, null, null);
        int idColIndex = cursor.getColumnIndex("_id");
        int nameColIndex = cursor.getColumnIndex("name");
        int fuelColIndex = cursor.getColumnIndex("fuel");
        return new Machine(cursor.getInt(idColIndex),cursor.getString(nameColIndex),cursor.getInt(fuelColIndex));
    }

    public Cursor getCursor() {
        return db.query("machine", null, null, null, null, null, null);
    }
}
