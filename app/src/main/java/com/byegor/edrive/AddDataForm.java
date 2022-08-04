package com.byegor.edrive;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class AddDataForm extends Activity implements View.OnClickListener {

    Button submit;
    EditText etName, etFuelConsumption, etConventionalUnits;
    DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_form);
        submit = (Button) findViewById(R.id.submit);
        etName = (EditText) findViewById(R.id.machineName);
        etFuelConsumption = (EditText) findViewById(R.id.fuelConsumption);
        etConventionalUnits = (EditText) findViewById(R.id.сonventionalUnits);
        dbHelper = new DBHelper(this);
    }


    @Override
    public void onClick(View view) {

        String name = etName.getText().toString();
        String fuelConsumption = etFuelConsumption.getText().toString();
        String conventionalUnits = etConventionalUnits.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        switch (view.getId()) {
            case R.id.submit:
                cv.put(DBHelper.KEY_NAME, name);
                cv.put(DBHelper.KEY_FUEL_CONSUMPTION, fuelConsumption);
                cv.put(DBHelper.KEY_CONVENTIONAL_UNITS, conventionalUnits);
                db.insert(DBHelper.TABLE_MACHINES,null,cv);
                break;
            default:
                break;
        }
    }
}
