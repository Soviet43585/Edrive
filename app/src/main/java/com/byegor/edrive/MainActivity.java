package com.byegor.edrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.DriverManager;


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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addNewMachine:
                Intent intent = new Intent(this, AddDataForm.class);
                startActivity(intent);
            break;
            default:
            break;
        }
    }
}