package com.byegor.edrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.byegor.edrive.localDB.DBHelper;
import com.byegor.edrive.model.Consumption;
import com.byegor.edrive.model.Machine;
import com.byegor.edrive.repository.ConsumptionRepository;
import com.byegor.edrive.repository.MachineRepository;

import java.util.ArrayList;
import java.util.List;

public class NewMachineActivity extends Activity implements View.OnClickListener {

    ConsumptionRepository consumptionRepository;
    MachineRepository machineRepository;
    DBHelper dbHelper = new DBHelper(this);
    List<View> allEds;
    LinearLayout linear;
    View v;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_create_form);

        Button btnAddNewPoint = (Button) findViewById(R.id.btnAddNewConsumption);
        Button btnCreateNewMachine = (Button) findViewById(R.id.btnCreateNewMachine);

        allEds = new ArrayList<>();
        linear = (LinearLayout) findViewById(R.id.llNewConsumptionList);
        machineRepository = new MachineRepository(dbHelper);
        consumptionRepository = new ConsumptionRepository(dbHelper);

        btnCreateNewMachine.setOnClickListener(this);
        btnAddNewPoint.setOnClickListener(this);
        v = getLayoutInflater().inflate(R.layout.consumption_point, null, false);
        allEds.add(v);
        linear.addView(v);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddNewConsumption:
                v = getLayoutInflater().inflate(R.layout.consumption_point, null, false);
                System.out.println(allEds.size() + " ");
                allEds.add(v);
                linear.addView(v);
                break;
            case R.id.btnCreateNewMachine:

                EditText etNewMachineName = (EditText) findViewById(R.id.etMachineName);
                EditText etStartFuel = (EditText) findViewById(R.id.etStartFuel);
                Machine machine = new Machine(etNewMachineName.getText().toString(), Integer.parseInt(etStartFuel.getText().toString().trim()));
                machineRepository.create(machine);

                for (View v : allEds)
                {
                    EditText etUnitName = (EditText) v.findViewById(R.id.etConsumptionName);
                    EditText etFuelConsumption = (EditText) v.findViewById(R.id.etConsumptionNumber);
                    EditText etConventionalUnits = (EditText) v.findViewById(R.id.etConventionalUnits);
                    Consumption consumption = new Consumption(etUnitName.getText().toString(), Float.parseFloat(etFuelConsumption.getText().toString()), Float.parseFloat(etConventionalUnits.getText().toString()), machine.getId());
                    consumptionRepository.create(consumption);
                }
                
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }



}
