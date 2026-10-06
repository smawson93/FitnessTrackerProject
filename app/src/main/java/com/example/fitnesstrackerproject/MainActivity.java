package com.example.fitnesstrackerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToProfilePageMain (View view){
        Intent intent = new Intent(this, profile_page.class);
        startActivity(intent);
    }

    public void goToDiaryPageMain (View view){
        Intent intent = new Intent(this, diary_main_page.class);
        startActivity(intent);
    }

    public void goToGoalsPageMain (View view) {
        Intent intent = new Intent(this, goal_main_page.class);
        startActivity(intent);
    }

    public void goToDietsPage (View view){
        Intent intent = new Intent(this, diet_page.class);
        startActivity(intent);
    }

    public void goToExercisePage (View view){
        Intent intent = new Intent(this, exercise_page_main.class);
        startActivity(intent);
    }
}
