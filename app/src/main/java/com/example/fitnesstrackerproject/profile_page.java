package com.example.fitnesstrackerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class profile_page extends AppCompatActivity {

    Database profDatabase;
    Button profile;
    String name, gender, activity, height, age, weight, DAC;
    TextView tvName, tvHeight, tvWeight, tvGender, tvAge, tvActivity, tvDAC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_profile_page);

          profDatabase = new Database(profile_page.this, "TABLE_NAME_PROFILE");
          tvName = findViewById(R.id.textViewName);
          tvHeight = findViewById(R.id.textViewHeight);
          tvWeight = findViewById(R.id.textViewExerciseWeight);
          tvGender = findViewById(R.id.textViewGender);
          tvAge = findViewById(R.id.textViewAge);
          tvActivity = findViewById(R.id.textViewActivity);
          tvDAC = findViewById(R.id.textViewDailyCalories);
          profile = findViewById(R.id.buttonProfile);

          listProfile();

          tvName.setText(name);
          tvHeight.setText(height);
          tvWeight.setText(weight);
          tvGender.setText(gender);
          tvAge.setText(age);
          tvActivity.setText(activity);
          tvDAC.setText(DAC);

          profile.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(profile_page.this, update_profile_page.class);
                  intent.putExtra("name", name);
                  startActivity(intent);
              }
          });
    }

    public void listProfile() {
        Cursor cursor = profDatabase.listProfile();
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            name = cursor.getString(0);
            height = cursor.getString(1);
            weight = cursor.getString(2);
            gender = cursor.getString(3);
            age = cursor.getString(4);
            activity = cursor.getString(5);
            DAC = cursor.getString(7);
        }
    }

    }

