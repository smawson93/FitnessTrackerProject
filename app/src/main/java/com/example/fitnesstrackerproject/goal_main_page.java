package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class goal_main_page extends AppCompatActivity {

    Database goalDatabase;
    ArrayList<String> goalID, name, target, current, selection, isComplete;
    RecyclerView recyclerView;
    GoalAdapter goalAdapter;
    Button newGoal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_main_page);
        recyclerView = findViewById(R.id.recyclerViewGoalList);
        newGoal = findViewById(R.id.buttonAddNewGoal);
        goalDatabase = new Database(this, "TABLE_NAME_GOAL");
        goalID = new ArrayList<>();
        name = new ArrayList<>();
        target = new ArrayList<>();
        current = new ArrayList<>();
        selection = new ArrayList<>();
        isComplete = new ArrayList<>();

        goalAdapter = new GoalAdapter(goalID, name, target, current, selection, isComplete, this);
        recyclerView.setAdapter(goalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listGoalData();

        newGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(goal_main_page.this, addNewGoal.class);
                startActivity(intent);
            }
        });
    }

    private void listGoalData() {
        Cursor cursor = goalDatabase.listGoalData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                goalID.add(cursor.getString(0));
                name.add(cursor.getString(1));
                target.add(cursor.getString(2));
                current.add(cursor.getString(3));
                selection.add(cursor.getString(6));
                isComplete.add(cursor.getString(7));
            }
        }
    }
}
