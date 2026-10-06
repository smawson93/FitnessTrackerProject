package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class addNewGoal extends AppCompatActivity {

    Database goalDatabase;
    TextView currentTypeTV, targetTypeTV, startDateTV;
    EditText nameET, currentET, targetET;
    Spinner goalTypeSpinner;
    Button saveButton;
    String name, startDate, goalType, isComplete;
    int current, target;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_goal);
        goalDatabase = new Database(addNewGoal.this, "TABLE_NAME_GOAL");
        currentTypeTV = findViewById(R.id.textViewCurrentType);
        targetTypeTV = findViewById(R.id.textViewTargetType);
        startDateTV = findViewById(R.id.textViewStartDate);
        nameET = findViewById(R.id.editTextTextGoalName);
        currentET = findViewById(R.id.editTextNumberDecimalCurrent);
        targetET = findViewById(R.id.editTextNumberDecimalTarget);
        goalTypeSpinner = findViewById(R.id.spinnerGoalType);
        saveButton = findViewById(R.id.buttonSaveGoal);

        startDateTV.setText(setDate());

        goalTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                if (value.equals("Weight")) {
                    currentTypeTV.setText("Kg");
                    targetTypeTV.setText("Kg");
                }else if(value.equals("Steps")) {
                    currentTypeTV.setText("Steps");
                    targetTypeTV.setText("Steps");
                }else {
                    currentTypeTV.setText("");
                    targetTypeTV.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameET.getText().toString();
                current = Integer.parseInt(currentET.getText().toString());
                target = Integer.parseInt(targetET.getText().toString());
                startDate = startDateTV.getText().toString();
                goalType = goalTypeSpinner.getSelectedItem().toString();
                isComplete = "No";

                addNewGoal(name, target, current, startDate, goalType, isComplete);
                Intent intent = new Intent(addNewGoal.this, goal_main_page.class);
                startActivity(intent);
            }
        });
    }

    public void addNewGoal (@NonNull String name, int target, int current, String startDate, String goalType, String isComplete){
        boolean addGoalData = goalDatabase.newGoalInput(name, target, current, startDate, goalType, isComplete);

        if (addGoalData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public String setDate() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String stringdate = dt.format(date);
        return stringdate;
    }
}
