package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class updateGoalPage extends AppCompatActivity {

    Database goalDatabase;
    TextView targetTv, currentTypeTV, targetTypeTV, startDateTv, goalTypeTv;
    EditText updateNameET, updateCurrentET;
    Button updateButton, deleteButton;
    String name, nameNew, startDate, endDate, goalType, isComplete;
    int goalID, current, currentNew, target;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_goal_layout);
        goalDatabase = new Database(updateGoalPage.this, "TABLE_NAME_GOAL");
        targetTv = findViewById(R.id.textViewUpdateGoalTarget);
        currentTypeTV = findViewById(R.id.textViewUpdateGoalCurrentType);
        targetTypeTV = findViewById(R.id.textViewUpdateGoalTargetType);
        startDateTv = findViewById(R.id.textViewUpdateGoalStartDate);
        goalTypeTv = findViewById(R.id.textViewUpdateGoalType);
        updateNameET = findViewById(R.id.editTextUpdateGoalName);
        updateCurrentET = findViewById(R.id.editTextUpdateCurrentGoal);
        updateButton = findViewById(R.id.buttonUpdateGoal);
        deleteButton = findViewById(R.id.buttonSaveNew);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            goalID = bundle.getInt("goalID");
            setGoalData(goalID);
            updateNameET.setText(name, TextView.BufferType.EDITABLE);
            updateCurrentET.setText(String.valueOf(current));
            targetTv.setText(String.valueOf(target));
            startDateTv.setText(startDate);
            goalTypeTv.setText(goalType);
        }


        if (goalType.equals("Weight Loss") || goalType.equals("Weight Gain")) {
            currentTypeTV.setText("Kg");
            targetTypeTV.setText("Kg");
        } else {
            currentTypeTV.setText("Steps");
            targetTypeTV.setText("Steps");
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameNew = updateNameET.getText().toString();
                currentNew = Integer.parseInt(updateCurrentET.getText().toString());
                if (goalType.equals("Weight Loss")) {
                    if (currentNew <= target) {
                        endDate = setDate();
                        isComplete = "Yes";
                    } else {
                        isComplete = "No";
                    }
                } else if (goalType.equals("Weight Gain")) {
                    if (currentNew >= target) {
                        endDate = setDate();
                        isComplete = "Yes";
                    } else {
                        isComplete = "No";
                    }
                } else {
                    if (currentNew >= target) {
                        endDate = setDate();
                        isComplete = "Yes";
                    } else {
                        isComplete = "No";
                    }
                }
                updateGoal(nameNew, currentNew, endDate);
                Intent intent = new Intent(updateGoalPage.this, goal_main_page.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGoal();
                Intent intent = new Intent(updateGoalPage.this, goal_main_page.class);
                startActivity(intent);
            }
        });

    }

    public void updateGoal (@NonNull String name, int current, String endDate){
        boolean update = goalDatabase.updateGoalData(name, current, endDate, isComplete, goalID);

        if(update){
            Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to update data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteGoal () {
        boolean delete = goalDatabase.deleteGoalData(goalID);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void setGoalData (int goalID){
       Cursor cursor = goalDatabase.singleGoalData(goalID);
       if(cursor.getCount() > 0 ){
           cursor.moveToFirst();
           name = cursor.getString(1);
           target = cursor.getInt(2);
           current = cursor.getInt(3);
           startDate = cursor.getString(4);
           goalType = cursor.getString(6);
       }
    }

    public String setDate() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String stringdate = dt.format(date);
        return stringdate;
    }
}
