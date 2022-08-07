package com.byegor.edrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.byegor.edrive.localDB.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton btnAdd;
    SQLiteDatabase database;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (FloatingActionButton) findViewById(R.id.addNewMachine);
        btnAdd.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_MACHINES, null, null, null,null,null,null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int fuelConsumptionIndex = cursor.getColumnIndex(DBHelper.KEY_FUEL_CONSUMPTION);
            int conventionalUnitsIndex = cursor.getColumnIndex(DBHelper.KEY_CONVENTIONAL_UNITS);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex) +
                        ", fuel consumption = " + cursor.getString(fuelConsumptionIndex) +
                        ", conventional units = " + cursor.getString(conventionalUnitsIndex));
            } while (cursor.moveToNext());
        } else
            Log.d("mLog","0 rows");

        cursor.close();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNewMachine:
                Intent intent = new Intent(this, NewMachineActivity.class);
                startActivity(intent);
            break;
            default:
            break;
        }
    }
}