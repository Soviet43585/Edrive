package com.byegor.edrive.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byegor.edrive.R;
import com.byegor.edrive.model.Consumption;

import java.util.List;

public class RecyclerTripAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<Consumption> consumptions;


    public RecyclerTripAdapter(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.trip_point, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Consumption consumption = consumptions.get(position);

        TextView tvConsumptionName = holder.itemView.findViewById(R.id.tvConsumptionName1);
        TextView tvConsumption = holder.itemView.findViewById(R.id.tvConsumption1);
        TextView tvUnit = holder.itemView.findViewById(R.id.tvUnit);
        EditText etTrip = holder.itemView.findViewById(R.id.etTrip);
        tvConsumptionName.setText(consumptions.get(position).getName());
        tvConsumption.setText("" + consumptions.get(position).getFuelConsumption());
        tvUnit.setText("" + consumptions.get(position).getConventionalUnits());


    }

    @Override
    public int getItemCount() {                //Очень важный метод без него нечего не работает
        try {
            return this.consumptions.size();
        } catch (NullPointerException e) {
            System.out.println("DB is empty (RecyclerMachineAdapter).");
        }
        return 0;
    }
}
