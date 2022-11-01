package com.byegor.edrive.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.byegor.edrive.NewMachineActivity;
import com.byegor.edrive.R;

public class CustomOnClickListener implements View.OnClickListener {

    private Context context;

    public CustomOnClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddNewMachine) {
            Intent intent = new Intent(context, NewMachineActivity.class);
            context.startActivity(intent);
        }



    }
}
