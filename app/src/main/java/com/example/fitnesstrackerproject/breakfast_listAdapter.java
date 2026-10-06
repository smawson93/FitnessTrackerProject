package com.example.fitnesstrackerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class breakfast_listAdapter extends RecyclerView.Adapter<breakfast_listAdapter.BreakfastViewHolder> {

    private ArrayList food, calories;
    private Context context;

    breakfast_listAdapter(Context context, ArrayList food, ArrayList calories){
        this.context=context;
        this.food=food;
        this.calories=calories;
    }

    @NonNull
    @Override
    public BreakfastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_card_layout, parent, false);
        return new BreakfastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastViewHolder holder, int position) {

        holder.tvFoodName.setText(String.valueOf(food.get(position)));
        holder.tvCalories.setText(String.valueOf(calories.get(position)));

    }

    @Override
    public int getItemCount() {
       return food.size();
    }

    public class BreakfastViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName, tvCalories;

        public BreakfastViewHolder(@NonNull View foodView) {
            super(foodView);
            tvFoodName = foodView.findViewById(R.id.tvFoodName);
            tvCalories = foodView.findViewById(R.id.tvCalories);

        }
    }
}
