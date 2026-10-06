package com.example.fitnesstrackerproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.MyViewHolder> {

    private ArrayList date, totalCalories;
    private Context context;

    DiaryAdapter(Context context, ArrayList date, ArrayList totalCalories){
        this.context = context;
        this.date = date;
        this.totalCalories = totalCalories;
    }

    @NonNull
    @Override
    public DiaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.diary_list_data, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.MyViewHolder holder, final int position) {

        holder.date_text.setText(String.valueOf(date.get(position)));
        holder.totalCalories_text.setText(String.valueOf(totalCalories.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, daily_diary_entry.class);
                intent.putExtra("date", date.get(position).toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date_text, totalCalories_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date_text = itemView.findViewById(R.id.tvDate);
            totalCalories_text = itemView.findViewById(R.id.tvTotalCalories);

        }
    }
}
