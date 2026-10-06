package com.example.fitnesstrackerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class allFoodListAdapterDinner extends RecyclerView.Adapter<allFoodListAdapterDinner.foodListViewHolder> {

    private ArrayList food, calories;
    private Context context;

    public allFoodListAdapterDinner(ArrayList food, ArrayList calories, Context context) {
        this.food = food;
        this.calories = calories;
        this.context = context;
    }

    @NonNull
    @Override
    public allFoodListAdapterDinner.foodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_list_card_layout, parent, false);
        return new foodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull allFoodListAdapterDinner.foodListViewHolder holder, int position) {
        holder.tvFoodName.setText(String.valueOf(food.get(position)));
        holder.tvCalories.setText(String.valueOf(calories.get(position)));


    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class foodListViewHolder extends RecyclerView.ViewHolder {

        TextView tvFoodName, tvCalories;

        public  foodListViewHolder(@NonNull View foodView) {
            super(foodView);
            tvFoodName = foodView.findViewById(R.id.tvFoodName);
            tvCalories = foodView.findViewById(R.id.tvCalories);
        }
    }
}
