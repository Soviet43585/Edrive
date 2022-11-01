package com.byegor.edrive.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.byegor.edrive.R;
import com.byegor.edrive.model.Consumption;

import java.util.ArrayList;
import java.util.List;

public class RecyclerConsumptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Consumption> consumptions = new ArrayList<>();
    int listSize = 1;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.consumption_point, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listSize;
    }
}
