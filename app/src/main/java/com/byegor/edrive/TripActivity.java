package com.byegor.edrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.byegor.edrive.adapter.RecyclerTripAdapter;
import com.byegor.edrive.dao.ConsumptionDAO;
import com.byegor.edrive.dao.MachineDAO;
import com.byegor.edrive.localDB.HelperFactory;
import com.byegor.edrive.model.Machine;

import java.sql.SQLException;

public class TripActivity extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerTripAdapter adapter;

    TextView tvMachineName;
    TextView tvMachineFuel;

    EditText etFill;

    Machine machine;
    ConsumptionDAO consumptionDAO;
    MachineDAO machineDAO;

    double fuel;
    double fill = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        HelperFactory.setHelper(this);
        try {
            machineDAO = HelperFactory.getDbHelper().getMachineDAO();
            consumptionDAO = HelperFactory.getDbHelper().getConsumptionDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            machine = (Machine) arguments.getSerializable(Machine.class.getSimpleName());
        }


        tvMachineName = (TextView) findViewById(R.id.tvMachineName1);
        tvMachineFuel = (TextView) findViewById(R.id.tvMachineFuel1);

        tvMachineName.setText(machine.getName());
        tvMachineFuel.setText("" + machine.getFuel() + " l.");

        fuel = machine.getFuel();

        try {
            recycler = (RecyclerView) findViewById(R.id.rvConsumptionList);
            adapter = new RecyclerTripAdapter(consumptionDAO.getConsumptionByMachineId(machine.getId()));
            recycler.setLayoutManager(new LinearLayoutManager(this));
            recycler.setAdapter(adapter);
        } catch (SQLException throwables) {

        }
        Button btnSaveTrip = (Button) findViewById(R.id.btnSaveTrip);
        btnSaveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < recycler.getAdapter().getItemCount(); i++) {
                    view = recycler.getChildAt(i);
                    EditText etTrip = (EditText) view.findViewById(R.id.etTrip);
                    TextView tvConsumption = (TextView) view.findViewById((R.id.tvConsumption1));
                    TextView tvUnit = (TextView) view.findViewById((R.id.tvUnit));

                    double consumption = Double.parseDouble(tvConsumption.getText().toString());
                    double unit = Double.parseDouble(tvUnit.getText().toString());
                    double trip = Double.parseDouble(etTrip.getText().toString());

                    fuel -= calculateFuel(consumption, unit, trip);
                }
                etFill = (EditText) findViewById(R.id.etFill);
                fill = Double.parseDouble(etFill.getText().toString());
                System.out.println("Fuel1 = " + fuel + " Fill = " + fill);
                fuel+=fill;
                System.out.println("Fuel2 = " + fuel);
                machine.setFuel(fuel);
                try {
                    machineDAO.updateMachine(machine);
                    System.out.println("Updated");
                } catch (SQLException throwables) {
                    System.out.println("================ SQLException ==============");
                    throwables.printStackTrace();
                }
                System.out.println("Fuel = " + fuel);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HelperFactory.releaseHelper();
    }

    private double calculateFuel(double consumption, double unit, double trip) {
        return consumption/unit*trip;
    }



}