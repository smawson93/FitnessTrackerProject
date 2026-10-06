package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class exercise_page_main extends AppCompatActivity {

    Database exerciseDatabase;
    ArrayList<String> id, name, date, type;
    RecyclerView recyclerView;
    Button newExercise;
    ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_page_main);
        exerciseDatabase = new Database(exercise_page_main.this, "TABLE_NAME_EXERCISE");
        recyclerView = findViewById(R.id.exerciseListRecyclerView);
        newExercise = findViewById(R.id.buttonNewExercise);
        id = new ArrayList<>();
        name = new ArrayList<>();
        date = new ArrayList<>();
        type = new ArrayList<>();

        exerciseAdapter = new ExerciseAdapter(id, name, date, type, exercise_page_main.this);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(exercise_page_main.this));
        listExerciseData();

        newExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(exercise_page_main.this, add_new_exercise.class);
                startActivity(intent);
            }
        });

    }

    public void listExerciseData() {
        Cursor cursor = exerciseDatabase.listAllExercise();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                type.add(cursor.getString(2));
                date.add(cursor.getString(3));
            }
        }
    }
}
