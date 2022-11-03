package com.byegor.edrive.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byegor.edrive.R;
import com.byegor.edrive.model.Consumption;

import java.util.ArrayList;
import java.util.List;

public class RecyclerConsumptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Consumption> consumptions = new ArrayList<>(1);
    Button btnDeletePoint;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.consumption_point, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        btnDeletePoint = holder.itemView.findViewById(R.id.btnDeletePoint);
        btnDeletePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newPosition = holder.getAdapterPosition();
                notifyItemRemoved(newPosition);
                consumptions.remove(newPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return consumptions.size();
    }

    public void addItem() {
        consumptions.add(new Consumption());
    }

    public void deleteItemByPosition(int position) {
        consumptions.remove(position);
        notifyDataSetChanged();
    }
}
