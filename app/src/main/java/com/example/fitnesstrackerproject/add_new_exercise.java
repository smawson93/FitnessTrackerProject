package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class add_new_exercise extends AppCompatActivity {

    Database exerciseDatabase;
    TextView datetv, durationtv, caloriesburnedtv, setstv, repstv, weighttv, kgtv, exerciseTypetv;
    EditText nameet, durationet, caloriesburnedet, setset, repset, weightet;
    Spinner exerciseTypeSpinner;
    Button saveCardioButton, saveStrengthButton, updateCardioButton, updateStrengthButton, deleteButton;
    int id;
    String name, date, type, duration, reps, sets, calories, weight, typeName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise_page);

        exerciseDatabase = new Database(add_new_exercise.this, "TABLE_NAME_EXERCISE");

        datetv = findViewById(R.id.textViewSetExerciseDate);
        durationtv = findViewById(R.id.textViewDuration);
        caloriesburnedtv = findViewById(R.id.textViewCaloriesBurned);
        setstv = findViewById(R.id.textViewSets);
        repstv = findViewById(R.id.textViewReps);
        weighttv = findViewById(R.id.textViewExerciseWeight);
        kgtv = findViewById(R.id.textViewExerciseKG);
        exerciseTypetv = findViewById(R.id.textViewExerciseType);

        nameet = findViewById(R.id.editTextExerciseName);
        durationet = findViewById(R.id.editTextExerciseDuration);
        caloriesburnedet = findViewById(R.id.editTextExerciseCaloriesBurned);
        setset = findViewById(R.id.editTextExerciseSets);
        repset = findViewById(R.id.editTextExerciseReps);
        weightet = findViewById(R.id.editTextExerciseWeight);

        exerciseTypeSpinner = findViewById(R.id.spinnerExerciseType);

        saveCardioButton = findViewById(R.id.buttonSaveExerciseCardio);
        saveStrengthButton = findViewById(R.id.buttonSaveExerciseStrength);
        deleteButton = findViewById(R.id.buttonDeleteExercise);
        updateCardioButton = findViewById(R.id.buttonUpdateCardioExercise);
        updateStrengthButton = findViewById(R.id.buttonUpdateStrengthExercise);

        datetv.setText(setDate());



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            deleteButton.setVisibility(View.VISIBLE);
            id = bundle.getInt("id");
            typeName = bundle.getString("typeName");
            getExerciseData();
            exerciseTypeSpinner.setVisibility(View.INVISIBLE);
            exerciseTypetv.setVisibility(View.VISIBLE);
            exerciseTypetv.setText(typeName);
            if (type.equals("Cardiovascular")){
                updateCardioButton.setVisibility(View.VISIBLE);
                updateStrengthButton.setVisibility(View.INVISIBLE);

                //set what to be set as visible
                durationtv.setVisibility(View.VISIBLE);
                durationet.setVisibility(View.VISIBLE);
                caloriesburnedtv.setVisibility(View.VISIBLE);
                caloriesburnedet.setVisibility(View.VISIBLE);

                //Set rest to invisible
                setstv.setVisibility(View.INVISIBLE);
                setset.setVisibility(View.INVISIBLE);
                repstv.setVisibility(View.INVISIBLE);
                repset.setVisibility(View.INVISIBLE);
                weighttv.setVisibility(View.INVISIBLE);
                weightet.setVisibility(View.INVISIBLE);
                kgtv.setVisibility(View.INVISIBLE);

            }else{
                updateStrengthButton.setVisibility(View.VISIBLE);
                updateCardioButton.setVisibility(View.INVISIBLE);
                //set what needs to be visible
                setstv.setVisibility(View.VISIBLE);
                setset.setVisibility(View.VISIBLE);
                repstv.setVisibility(View.VISIBLE);
                repset.setVisibility(View.VISIBLE);
                weighttv.setVisibility(View.VISIBLE);
                weightet.setVisibility(View.VISIBLE);
                kgtv.setVisibility(View.VISIBLE);

                //set rest to invisible
                durationtv.setVisibility(View.INVISIBLE);
                durationet.setVisibility(View.INVISIBLE);
                caloriesburnedtv.setVisibility(View.INVISIBLE);
                caloriesburnedet.setVisibility(View.INVISIBLE);
            }
        }else {
            exerciseTypeSpinner.setVisibility(View.VISIBLE);

            exerciseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String value = (String) parent.getItemAtPosition(position);
                    if (value.equals("Cardiovascular")){
                        //set what to be set as visible
                        durationtv.setVisibility(View.VISIBLE);
                        durationet.setVisibility(View.VISIBLE);
                        caloriesburnedtv.setVisibility(View.VISIBLE);
                        caloriesburnedet.setVisibility(View.VISIBLE);
                        saveCardioButton.setVisibility(View.VISIBLE);

                        //Set rest to invisible
                        setstv.setVisibility(View.INVISIBLE);
                        setset.setVisibility(View.INVISIBLE);
                        repstv.setVisibility(View.INVISIBLE);
                        repset.setVisibility(View.INVISIBLE);
                        weighttv.setVisibility(View.INVISIBLE);
                        weightet.setVisibility(View.INVISIBLE);
                        saveStrengthButton.setVisibility(View.INVISIBLE);
                        kgtv.setVisibility(View.INVISIBLE);
                    } else if (value.equals("Strength Training")){
                        //set what needs to be visible
                        setstv.setVisibility(View.VISIBLE);
                        setset.setVisibility(View.VISIBLE);
                        repstv.setVisibility(View.VISIBLE);
                        repset.setVisibility(View.VISIBLE);
                        weighttv.setVisibility(View.VISIBLE);
                        weightet.setVisibility(View.VISIBLE);
                        saveStrengthButton.setVisibility(View.VISIBLE);
                        kgtv.setVisibility(View.VISIBLE);

                        //set rest to invisible
                        durationtv.setVisibility(View.INVISIBLE);
                        durationet.setVisibility(View.INVISIBLE);
                        caloriesburnedtv.setVisibility(View.INVISIBLE);
                        caloriesburnedet.setVisibility(View.INVISIBLE);
                        saveCardioButton.setVisibility(View.INVISIBLE);
                    } else {
                        durationtv.setVisibility(View.INVISIBLE);
                        durationet.setVisibility(View.INVISIBLE);
                        caloriesburnedtv.setVisibility(View.INVISIBLE);
                        caloriesburnedet.setVisibility(View.INVISIBLE);
                        saveCardioButton.setVisibility(View.INVISIBLE);
                        setstv.setVisibility(View.INVISIBLE);
                        setset.setVisibility(View.INVISIBLE);
                        repstv.setVisibility(View.INVISIBLE);
                        repset.setVisibility(View.INVISIBLE);
                        weighttv.setVisibility(View.INVISIBLE);
                        weightet.setVisibility(View.INVISIBLE);
                        saveStrengthButton.setVisibility(View.INVISIBLE);
                        kgtv.setVisibility(View.INVISIBLE);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            deleteButton.setVisibility(View.INVISIBLE);
            updateCardioButton.setVisibility(View.INVISIBLE);
            updateStrengthButton.setVisibility(View.INVISIBLE);
            exerciseTypetv.setVisibility(View.INVISIBLE);
        }

        saveStrengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName, getType;
                int getSets, getReps;
                double getWeight;

                getName = nameet.getText().toString();
                getType = exerciseTypeSpinner.getSelectedItem().toString();
                getSets = Integer.parseInt(setset.getText().toString());
                getReps = Integer.parseInt(repset.getText().toString());
                getWeight = Double.parseDouble(weightet.getText().toString());

                addStrengthExercise(getName,getType,setDate(),getSets,getReps,getWeight);
                Intent intent = new Intent(add_new_exercise.this, exercise_page_main.class);
                startActivity(intent);
            }
        });

        saveCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName, getType;
                int getDuration, getCalories;

                getName = nameet.getText().toString();
                getType = exerciseTypeSpinner.getSelectedItem().toString();
                getDuration = Integer.parseInt(durationet.getText().toString());
                getCalories = Integer.parseInt(caloriesburnedet.getText().toString());

                addCardioExercise(getName,getType,setDate(),getDuration,getCalories);
                Intent intent = new Intent(add_new_exercise.this, exercise_page_main.class);
                startActivity(intent);
            }
        });

        updateStrengthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName;
                int getSets, getReps;
                double getWeight;

                getName = nameet.getText().toString();
                getSets = Integer.parseInt(setset.getText().toString());
                getReps = Integer.parseInt(repset.getText().toString());
                getWeight = Double.parseDouble(weightet.getText().toString());

                updateStrengthExercise(id, getName, getSets, getReps, getWeight);
                Intent intent = new Intent(add_new_exercise.this, exercise_page_main.class);
                startActivity(intent);
            }
        });

        updateCardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName;
                int getDuration, getCalories;

                getName = nameet.getText().toString();
                getDuration = Integer.parseInt(durationet.getText().toString());
                getCalories = Integer.parseInt(caloriesburnedet.getText().toString());

                updateCardioExercise(id, getName, getDuration, getCalories);
                Intent intent = new Intent(add_new_exercise.this, exercise_page_main.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(id);
                Intent intent = new Intent(add_new_exercise.this, exercise_page_main.class);
                startActivity(intent);
            }
        });
    }

    public void addStrengthExercise(String name, String type, String date, int sets, int reps, double weight) {
        boolean addData = exerciseDatabase.addStrengthTrainingExercise(name, type, date, sets, reps, weight);

        if (addData) {
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCardioExercise(String name, String type, String date, int duration, int calories){
        boolean addData = exerciseDatabase.addCardioExercise(name, type, date, duration, calories);

        if (addData) {
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStrengthExercise(int id, String name, int sets, int reps, double weight){
        boolean updateData = exerciseDatabase.updateStrengthExercise(id, name, sets, reps, weight);

        if (updateData) {
            Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to update data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateCardioExercise(int id, String name,  int duration, int calories) {
        boolean updateData = exerciseDatabase.updateCardioExercise(id, name, duration, calories);

        if (updateData) {
            Toast.makeText(this, "Data Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to update data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(int id){
        boolean deleteData = exerciseDatabase.deleteExercise(id);

        if (deleteData) {
            Toast.makeText(this, "Data Deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getExerciseData(){
        Cursor cursor = exerciseDatabase.getSingleExerciseData(id);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            name = cursor.getString(1);
            type = cursor.getString(2);
            date = cursor.getString(3);
            nameet.setText(name, TextView.BufferType.EDITABLE);
            datetv.setText(date);

            if (type.equals("Cardiovascular")){
                duration = cursor.getString(4);
                calories = cursor.getString(5);
                durationet.setText(duration, TextView.BufferType.EDITABLE);
                caloriesburnedet.setText(calories, TextView.BufferType.EDITABLE);
            }else {
                sets = cursor.getString(6);
                reps = cursor.getString(7);
                weight = cursor.getString(8);
                setset.setText(sets, TextView.BufferType.EDITABLE);
                repset.setText(reps, TextView.BufferType.EDITABLE);
                weightet.setText(weight, TextView.BufferType.EDITABLE);
            }
        }
    }

    public String setDate() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String stringdate = dt.format(date);
        return stringdate;
    }

}
