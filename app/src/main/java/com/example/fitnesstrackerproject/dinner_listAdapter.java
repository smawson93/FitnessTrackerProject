package com.example.fitnesstrackerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dinner_listAdapter extends RecyclerView.Adapter<dinner_listAdapter.DinnerViewHolder> {

    private ArrayList food, calories;
    private Context context;

    dinner_listAdapter(Context context, ArrayList food, ArrayList calories){
        this.context=context;
        this.food=food;
        this.calories=calories;
    }

    @NonNull
    @Override
    public DinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_card_layout, parent, false);
        return new DinnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DinnerViewHolder holder, int position) {

        holder.tvFoodName.setText(String.valueOf(food.get(position)));
        holder.tvCalories.setText(String.valueOf(calories.get(position)));

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class DinnerViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName, tvCalories;

        public DinnerViewHolder(@NonNull View foodView) {
            super(foodView);
            tvFoodName = foodView.findViewById(R.id.tvFoodName);
            tvCalories = foodView.findViewById(R.id.tvCalories);

        }
    }
}
