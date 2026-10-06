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

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private ArrayList id, name, date, type;
    private Context context;

    public ExerciseAdapter(ArrayList id, ArrayList name, ArrayList date, ArrayList type, Context context) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_card_layout, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, final int position) {

        holder.idtv.setText(String.valueOf(id.get(position)));
        holder.nametv.setText(String.valueOf(name.get(position)));
        holder.datetv.setText(String.valueOf(date.get(position)));
        holder.typetv.setText(String.valueOf(type.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, add_new_exercise.class);
                intent.putExtra("id", Integer.parseInt(id.get(position).toString()));
                intent.putExtra("typeName", type.get(position).toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        TextView idtv, nametv, datetv, typetv;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            idtv = itemView.findViewById(R.id.textViewExerciseID);
            nametv = itemView.findViewById(R.id.textViewExerciseName);
            datetv = itemView.findViewById(R.id.textViewExerciseDate);
            typetv = itemView.findViewById(R.id.textViewExerciseType);
        }
    }
}
