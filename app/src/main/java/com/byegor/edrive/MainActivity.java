package com.byegor.edrive;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;

import com.byegor.edrive.adapter.RecyclerMachineAdapter;
import com.byegor.edrive.dao.ConsumptionDAO;
import com.byegor.edrive.dao.MachineDAO;
import com.byegor.edrive.listener.CustomOnClickListener;
import com.byegor.edrive.localDB.DBHelper;

import com.byegor.edrive.localDB.HelperFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;

    private RecyclerMachineAdapter adapter;
    private RecyclerView recycler;

    private MachineDAO machineDAO = null;
    private ConsumptionDAO consumptionDAO = null;

    private CustomOnClickListener onClickListener;

    public MainActivity() throws SQLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HelperFactory.setHelper(this);
        onClickListener = new CustomOnClickListener(this);
        btnAdd = (FloatingActionButton) findViewById(R.id.btnAddNewMachine);
        btnAdd.setOnClickListener(onClickListener);
        try {
            machineDAO = HelperFactory.getDbHelper().getMachineDAO();
            consumptionDAO = HelperFactory.getDbHelper().getConsumptionDAO();
            System.out.println(consumptionDAO.getConsumptionByMachineId(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            recycler = (RecyclerView) findViewById(R.id.rvMachineList);
            adapter = new RecyclerMachineAdapter(machineDAO.getAllMachines(), this);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
            System.out.println(machineDAO.getAllMachines());
            System.out.println(machineDAO);
        } catch (NullPointerException | SQLException e) {
            System.out.println("DB is empty");
        }
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.btnAddNewMachine) {
//            Intent intent = new Intent(this, NewMachineActivity.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HelperFactory.releaseHelper();
    }
}