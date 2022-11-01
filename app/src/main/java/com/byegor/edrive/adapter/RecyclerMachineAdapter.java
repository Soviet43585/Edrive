package com.byegor.edrive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byegor.edrive.TripActivity;
import com.byegor.edrive.R;
import com.byegor.edrive.model.Machine;

import java.util.List;

public class RecyclerMachineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Machine> machines;
    private final Context context;


    public RecyclerMachineAdapter(List<Machine> machines, Context context) {

        this.context = context;
        this.machines = machines;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.machine_point, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Machine machine = machines.get(position);
        TextView tvMachineName = holder.itemView.findViewById(R.id.tvMachineName);
        TextView tvMachineFuel = holder.itemView.findViewById(R.id.tvMachineFuel);
        tvMachineName.setText(machines.get(position).getName());
        tvMachineFuel.setText("" + machines.get(position).getFuel() + " l.");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Selected " + machine.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, TripActivity.class);
                intent.putExtra(Machine.class.getSimpleName(), machine);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() throws NullPointerException {
        try {
            return this.machines.size();
        } catch (NullPointerException e) {
            System.out.println("DB is empty (RecyclerMachineAdapter).");
        }
        return 0;
    }

}
