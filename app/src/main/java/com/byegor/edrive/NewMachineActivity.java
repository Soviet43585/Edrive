package com.byegor.edrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_create_form);
        HelperFactory.setHelper(this);

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
        Button btnDeletePoint = (Button) findViewById(R.id.btnDeletePoint);

        allEds = new ArrayList<>();
        linear = (LinearLayout) findViewById(R.id.llNewConsumptionList);


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

                try {
                    machineDAO.create(machine);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                for (View v : allEds)
                {
                    EditText etUnitName = (EditText) v.findViewById(R.id.etConsumptionName);
                    EditText etFuelConsumption = (EditText) v.findViewById(R.id.etConsumptionNumber);
                    EditText etConventionalUnits = (EditText) v.findViewById(R.id.etConventionalUnits);
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
                System.out.println("Some debug message");
                linear.removeView((LinearLayout)view.getParent());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HelperFactory.releaseHelper();
    }
}
