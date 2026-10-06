package com.example.fitnesstrackerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class lunch_listAdapter extends RecyclerView.Adapter<lunch_listAdapter.LunchViewHolder> {

    private ArrayList food, calories;
    private Context context;

    lunch_listAdapter(Context context, ArrayList food, ArrayList calories){
        this.context=context;
        this.food=food;
        this.calories=calories;
    }

    @NonNull
    @Override
    public LunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_card_layout, parent, false);
        return new LunchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LunchViewHolder holder, int position) {

        holder.tvFoodName.setText(String.valueOf(food.get(position)));
        holder.tvCalories.setText(String.valueOf(calories.get(position)));

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class LunchViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName, tvCalories;

        public LunchViewHolder(@NonNull View foodView) {
            super(foodView);
            tvFoodName = foodView.findViewById(R.id.tvFoodName);
            tvCalories = foodView.findViewById(R.id.tvCalories);

        }
    }
}
