package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class update_profile_page extends AppCompatActivity {

    private Database profDatabase;
    EditText edit_text_height, etUsername;
    EditText edit_text_weight;
    EditText edit_text_age;
    Spinner spinner_gender;
    Spinner spinner_activity_level, weightLossSpinner;
    Button button_save, deleteButton, updateButton;
    String name, getName, getHeight, getWeight, getActivity, getGender, getAge, getWeightLoss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile_page);

        profDatabase = new Database(update_profile_page.this, "TABLE_NAME_PROFILE");

        etUsername = findViewById(R.id.editTextUserName);
        edit_text_height = findViewById(R.id.editTextHeight);
        edit_text_weight = findViewById(R.id.editTextWeight);
        edit_text_age = findViewById(R.id.editTextAge);
        spinner_gender = findViewById(R.id.spinnerGender);
        spinner_activity_level = findViewById(R.id.spinnerActivityLevel);
        button_save = findViewById(R.id.buttonSaveNew);
        deleteButton = findViewById(R.id.buttonDelete);
        weightLossSpinner = findViewById(R.id.spinnerWeightLoss);
        updateButton = findViewById(R.id.buttonUpdate);

            listProfile();
            etUsername.setText(getName, TextView.BufferType.EDITABLE);
            edit_text_height.setText(getHeight, TextView.BufferType.EDITABLE);
            edit_text_weight.setText(getWeight, TextView.BufferType.EDITABLE);
            edit_text_age.setText(getAge, TextView.BufferType.EDITABLE);

            spinner_gender.setSelection(getGenderIndex(spinner_gender, getGender));
            spinner_activity_level.setSelection(getActivityIndex(spinner_activity_level, getActivity));
            weightLossSpinner.setSelection(getWeightLossIndex(weightLossSpinner, getWeightLoss));

             Bundle bundle = getIntent().getExtras();
             name = bundle.getString("name");
             if (name != null) {
                 button_save.setVisibility(View.INVISIBLE);
                 updateButton.setVisibility(View.VISIBLE);
             }

            button_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int height;
                    double weight;
                    int age;
                    String gender;
                    String activity_level, weightLoss;

                    name = etUsername.getText().toString();
                    height = Integer.parseInt(edit_text_height.getText().toString());
                    weight = Double.parseDouble(edit_text_weight.getText().toString());
                    age = Integer.parseInt(edit_text_age.getText().toString());
                    gender = spinner_gender.getSelectedItem().toString();
                    activity_level = spinner_activity_level.getSelectedItem().toString();
                    weightLoss = weightLossSpinner.getSelectedItem().toString();

                    double BMR = getBMR(height, weight, age, gender);
                    double preDAC = getDailyCalorieIntake(BMR, activity_level);
                    double DAC = setWeightLostDAC(preDAC, weightLoss);

                    addProfile(name, height, weight, gender, age, activity_level, BMR, DAC, weightLoss);
                    Intent intent = new Intent(update_profile_page.this, profile_page.class);
                    startActivity(intent);
                }
            });

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name;
                    int height;
                    double weight;
                    int age;
                    String gender;
                    String activity_level, weightLoss;

                    name = getName;
                    height = Integer.parseInt(edit_text_height.getText().toString());
                    weight = Double.parseDouble(edit_text_weight.getText().toString());
                    age = Integer.parseInt(edit_text_age.getText().toString());
                    gender = spinner_gender.getSelectedItem().toString();
                    activity_level = spinner_activity_level.getSelectedItem().toString();
                    weightLoss = weightLossSpinner.getSelectedItem().toString();

                    double BMR = getBMR(height, weight, age, gender);
                    double preDAC = getDailyCalorieIntake(BMR, activity_level);
                    double DAC = setWeightLostDAC(preDAC, weightLoss);

                    updateProfile(name, height, weight, gender, age, activity_level, BMR, DAC, weightLoss);
                    Intent intent = new Intent(update_profile_page.this, profile_page.class);
                    startActivity(intent);
                }
            });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile();
                Intent intent = new Intent(update_profile_page.this, profile_page.class);
                startActivity(intent);
            }
        });
    }

    static double setWeightLostDAC(double preDAC, String weightLoss) {
        double DAC;

        switch (weightLoss) {
            case "Lose half a kg per week":
                DAC = preDAC - 500;
                return DAC;
            case "Lose one kg per week":
                DAC = preDAC - 1000;
                return DAC;
            case "Gain half a kg per week":
                DAC = preDAC + 500;
                return DAC;
            case "Gain one kg per week":
                DAC = preDAC + 1000;
                return DAC;
            default:
                DAC = preDAC;
                return DAC;
        }

    }

    private void deleteProfile() {
        boolean delete = profDatabase.deleteProfile(getName);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    private int getActivityIndex(Spinner spinner_activity_level, String activity_level) {
        for (int i=0;i<spinner_activity_level.getCount();i++){
            if (spinner_activity_level.getItemAtPosition(i).toString().equalsIgnoreCase(activity_level)){
                return i;
            }
        }
        return 0;
    }

    private int getGenderIndex(Spinner spinner_gender, String gender) {
        for (int i=0;i<spinner_gender.getCount();i++){
            if (spinner_gender.getItemAtPosition(i).toString().equalsIgnoreCase(gender)){
                return i;
            }
        }
        return 0;
    }

    private int getWeightLossIndex(Spinner weightLossSpinner, String getWeightLoss) {
        for (int i=0;i<weightLossSpinner.getCount();i++){
            if (weightLossSpinner.getItemAtPosition(i).toString().equalsIgnoreCase(getWeightLoss)){
                return i;
            }
        }
        return 0;
    }

    static double getBMR(double height, double weight, int age, String gender) {

                if (gender.equals("Male")) {
                    return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
                } else {
                    return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
                }
    }

    static double getDailyCalorieIntake(double BMR, String activity_level) {
                double result;
                double result2D;

                switch (activity_level) {
                    case "Sedentary":
                        result = BMR * 1.2;
                        result2D = Math.round(result);
                        return result2D;
                    case "Lightly active":
                        result = BMR * 1.375;
                        result2D = Math.round(result);
                        return result2D;
                    case "Moderately active":
                        result = BMR * 1.55;
                        result2D = Math.round(result);
                        return result2D;
                    case "Very active":
                        result = BMR * 1.725;
                        result2D = Math.round(result);
                        return result2D;
                    default:
                        result = BMR * 1.9;
                        result2D = Math.round(result);
                        return result2D;
                        }
                }

    private void addProfile(String name, int height, double weight, String gender, int age, String activity_level, double BMR, double DAC, String weightLoss) {
        boolean addData = profDatabase.addProfile(name, height, weight, gender, age, activity_level, BMR, DAC, weightLoss);

        if (addData) {
            Toast.makeText(this, "Profile Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to Add Profile!", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateProfile(String name, int height, double weight, String gender, int age, String activity_level, double BMR, double DAC, String weightLoss) {
        boolean updateData = profDatabase.updateProfile(name, height, weight, gender, age, activity_level, BMR, DAC, weightLoss);

        if (updateData) {
            Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to Update Profile!", Toast.LENGTH_SHORT).show();
        }
    }

    public void listProfile() {
        Cursor cursor = profDatabase.listProfile();
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            getName = cursor.getString(0);
            getHeight = cursor.getString(1);
            getWeight = cursor.getString(2);
            getGender = cursor.getString(3);
            getAge = cursor.getString(4);
            getActivity = cursor.getString(5);
            getWeightLoss = cursor.getString(8);
        }
    }

    }
