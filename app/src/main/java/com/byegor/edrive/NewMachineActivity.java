package com.byegor.edrive;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.byegor.edrive.localDB.DBHelper;

public class NewMachineActivity extends Activity implements View.OnClickListener {

    Button btnCreateMachine;
    EditText etName, etFuelConsumption, etConventionalUnits, etStartValue;
    DBHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_machine_form);
        btnCreateMachine = (Button) findViewById(R.id.btnCreateMachine);
        btnCreateMachine.setOnClickListener(this);
        etName = (EditText) findViewById(R.id.etMachineName);
        etFuelConsumption = (EditText) findViewById(R.id.etFuelConsumption);
        etConventionalUnits = (EditText) findViewById(R.id.etConventionalUnits);
        etStartValue = (EditText) findViewById(R.id.etStartValue);
        dbHelper = new DBHelper(this);
    }


    @Override
    public void onClick(View view) {

        String name = etName.getText().toString();
        String fuelConsumption = etFuelConsumption.getText().toString();
        String conventionalUnits = etConventionalUnits.getText().toString();
        String startValue = etStartValue.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        switch (view.getId()) {
            case R.id.btnCreateMachine:
                Log.d("mLog","button pressed");
                cv.put(DBHelper.KEY_NAME, name);
                cv.put(DBHelper.KEY_FUEL_CONSUMPTION, fuelConsumption);
                cv.put(DBHelper.KEY_CONVENTIONAL_UNITS, conventionalUnits);
                cv.put(DBHelper.KEY_FUEL, startValue);
                db.insert(DBHelper.TABLE_MACHINES,null,cv);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}
