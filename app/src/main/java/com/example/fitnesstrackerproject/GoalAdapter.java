package com.example.fitnesstrackerproject;

import android.content.Context;
import android.content.Intent;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    private ArrayList goalID, name, target, current, selection, isComplete;
//    private static int weight_goal = 1;
//    private static int exercise_goal = 2;
//    private static int isNotComplete = 3;
//    private static int isComplete = 4;
    private Context context;

    public GoalAdapter(ArrayList goalID, ArrayList name, ArrayList target, ArrayList current, ArrayList selection, ArrayList isComplete, Context context) {
        this.goalID = goalID;
        this.name = name;
        this.target = target;
        this.current = current;
        this.selection = selection;
        this.isComplete = isComplete;
        this.context = context;
    }

    @NonNull
    @Override
    public GoalAdapter.GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1 || viewType == 5) {
            view = inflater.inflate(R.layout.weight_goal_card_layout_notcomplete, parent, false);
        } else if (viewType == 2 ){
            view = inflater.inflate(R.layout.exercise_goal_card_layout_notcomplete,parent,false);
        } else if (viewType == 3 || viewType == 6){
            view = inflater.inflate(R.layout.weight_goal_card_layout_complete,parent,false);
        } else {
            view = inflater.inflate(R.layout.exercise_goal_card_layout_complete,parent,false);
        }
        return new GoalViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, final int position) {

        holder.goalID_text.setText(String.valueOf(goalID.get(position)));
        holder.name_text.setText(String.valueOf(name.get(position)));
        holder.target_text.setText(String.valueOf(target.get(position)));
        holder.current_text.setText(String.valueOf(current.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, updateGoalPage.class);
                intent.putExtra("goalID" , Integer.parseInt(goalID.get(position).toString()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goalID.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (selection.get(position).toString().equals("Weight Loss") && isComplete.get(position).toString().equals("No")) {
            return 1;
        } else if (selection.get(position).toString().equals("Steps") && isComplete.get(position).toString().equals("No")) {
            return 2;
        } else if (selection.get(position).toString().equals("Weight Loss") && isComplete.get(position).toString().equals("Yes")) {
            return 3;
        } else if (selection.get(position).toString().equals("Weight Gain") && isComplete.get(position).toString().equals("No")) {
            return 5;
        } else if (selection.get(position).toString().equals("Weight gain") && isComplete.get(position).toString().equals("Yes")) {
            return 6;
        } else {
            return 4;
        }
    }

    public class GoalViewHolder extends RecyclerView.ViewHolder {

        TextView goalID_text, name_text, target_text, current_text;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            goalID_text = itemView.findViewById(R.id.textViewGoalID);
            name_text = itemView.findViewById(R.id.textViewGoalName);
            target_text = itemView.findViewById(R.id.textViewGoalTarget);
            current_text = itemView.findViewById(R.id.textViewGoalCurrent);
        }
    }


}
