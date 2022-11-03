package com.byegor.edrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.byegor.edrive.adapter.RecyclerConsumptionAdapter;
import com.byegor.edrive.dao.ConsumptionDAO;
import com.byegor.edrive.dao.MachineDAO;
import com.byegor.edrive.localDB.DBHelper;
import com.byegor.edrive.localDB.HelperFactory;
import com.byegor.edrive.model.Consumption;
import com.byegor.edrive.model.Machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NewMachineActivity extends Activity implements View.OnClickListener {

    private List<View> allEds;
    private LinearLayout linear;
    private View v;

    private MachineDAO machineDAO = null;
    private ConsumptionDAO consumptionDAO = null;

    RecyclerView recycler;

    RecyclerConsumptionAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_create_form);
        HelperFactory.setHelper(this);
        adapter = new RecyclerConsumptionAdapter();
        recycler = (RecyclerView) findViewById(R.id.rvNewConsumptionList);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        try {
            machineDAO = HelperFactory.getDbHelper().getMachineDAO();
            consumptionDAO = HelperFactory.getDbHelper().getConsumptionDAO();
            System.out.println("MachineDAO - " + machineDAO);
            System.out.println("ConsumptionDAO - " + consumptionDAO);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Button btnAddNewPoint = (Button) findViewById(R.id.btnAddNewConsumption);
        Button btnCreateNewMachine = (Button) findViewById(R.id.btnCreateNewMachine);
        btnCreateNewMachine.setOnClickListener(this);
        btnAddNewPoint.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           case R.id.btnAddNewConsumption:
               adapter.notifyDataSetChanged();
               adapter.addItem();
               break;
            case R.id.btnCreateNewMachine:
                EditText etNewMachineName = (EditText) findViewById(R.id.etMachineName);
                EditText etStartFuel = (EditText) findViewById(R.id.etStartFuel);
                Machine machine = new Machine(etNewMachineName.getText().toString(), Double.parseDouble(etStartFuel.getText().toString().trim()));
                try {
                    machineDAO.create(machine);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                for(int i  = 0; i < recycler.getAdapter().getItemCount(); i++) {
                    view = recycler.getChildAt(i);
                    EditText etUnitName = (EditText) view.findViewById(R.id.etConsumptionName);
                    EditText etFuelConsumption = (EditText) view.findViewById(R.id.etConsumptionNumber);
                    EditText etConventionalUnits = (EditText) view.findViewById(R.id.etConventionalUnits);
                    Consumption consumption = new Consumption(etUnitName.getText().toString(),
                            Double.parseDouble(etFuelConsumption.getText().toString()),
                            Double.parseDouble(etConventionalUnits.getText().toString()));
                    consumption.setMachine(machine);
                    try {
                        consumptionDAO.create(consumption);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    System.out.println("Created: " + consumption);
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDeletePoint:
                System.out.println("Delete");
                recycler.getChildAdapterPosition(view);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HelperFactory.releaseHelper();
    }
}
