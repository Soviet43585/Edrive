package com.byegor.edrive.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.byegor.edrive.dao.ConsumptionDAO;
import com.byegor.edrive.dao.MachineDAO;
import com.byegor.edrive.model.Consumption;
import com.byegor.edrive.model.Machine;

import java.sql.SQLException;
import java.util.List;

public class DeleteMachineDialogFragment extends DialogFragment {

    MachineDAO machineDAO;
    ConsumptionDAO consumptionDAO;
    Intent intent;
    Machine machine;


    public DeleteMachineDialogFragment(MachineDAO machineDAO, ConsumptionDAO consumptionDAO, Machine machine, Intent intent) {
        this.machineDAO = machineDAO;
        this.consumptionDAO = consumptionDAO;
        this.machine = machine;
        this.intent = intent;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        String title = "Warning!!!";
        String message = "Delete machine?";
        String btn1String = "Ok";
        String btn2String = "Cancel";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(btn1String, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    List<Consumption> consumptionList = consumptionDAO.getConsumptionByMachineId(machine.getId());
                    consumptionDAO.deleteConsumptions(consumptionList);
                    machineDAO.deleteMachine(machine);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                startActivity(intent);
            }
        })
                .setNegativeButton(btn2String, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }
}
