package com.byegor.edrive.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.byegor.edrive.localDB.DBHelper;
import com.byegor.edrive.model.Consumption;
import com.byegor.edrive.model.Machine;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionRepository {

    SQLiteDatabase db;
    DBHelper dbHelper;
    ContentValues cv = new ContentValues();

    public ConsumptionRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
        db = dbHelper.getWritableDatabase();
    }

    public void create(Consumption consumption) {
        cv.put("name", consumption.getName());
        cv.put("fuel_consumption", consumption.getFuelConsumption());
        cv.put("conventional_unit", consumption.getConventionalUnits());
        cv.put("machine_id", consumption.getMachineId());
        db.insert("consumption", null, cv);
        cv.clear();
    }

    public List<Consumption> getAll() {
        Cursor cursor = db.query("consumption", null, null, null, null, null, null);
        List<Consumption> consumptionList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("id");
            int nameColIndex = cursor.getColumnIndex("name");
            int fuelConsumptionColIndex = cursor.getColumnIndex("fuel_consumption");
            int conventionalUnitColIndex = cursor.getColumnIndex("conventional_unit");
            int machineIdColIndex = cursor.getColumnIndex("machine_id");
            do {
                consumptionList.add(new Consumption(cursor.getInt(idColIndex), cursor.getString(nameColIndex), cursor.getFloat(fuelConsumptionColIndex), cursor.getFloat(conventionalUnitColIndex),cursor.getInt(machineIdColIndex)));
            }
            while (cursor.moveToNext());
            cursor.close();
            return consumptionList;
        }
        cursor.close();
        return null;
    }
}
