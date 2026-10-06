package com.example.fitnesstrackerproject;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class diet_page extends AppCompatActivity {

    Database profDatabase, dietDatabase;
    TextView tvslimWorldAllowance, tvSyns, tvWeight, tvWeightNumber, tvKG, tvWWallowance, tvAllowancePoints, tvPointsName, tvCalories, tvDAC, tvProteinName, tvFatName, tvCarbName, tvProteinAllowance,
            tvFatAllowance, tvCarbAllowance, tvProteinGrams, tvCarbsGrams, tvFatsGrams;
    EditText setSyns;
    Spinner dietChoice;
    String weight, DAC;
    Button setSWDiet, setWWDiet, setMacroDiet;
    double weightNumber, DACnumber;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_page);

        profDatabase = new Database(diet_page.this, "TABLE_NAME_PROFILE");
        dietDatabase = new Database(diet_page.this, "TABLE_NAME_DIET");

        tvslimWorldAllowance = findViewById(R.id.tvSlimWorldAllowance);
        setSyns = findViewById(R.id.editTextNumberSyns);
        tvSyns = findViewById(R.id.textViewSyns);
        tvWeight = findViewById(R.id.tvWeightWatchers);
        tvWeightNumber = findViewById(R.id.textViewExerciseWeight);
        tvKG = findViewById(R.id.textViewKg);
        tvWWallowance = findViewById(R.id.tvWWAllowance);
        tvAllowancePoints = findViewById(R.id.tvWWAllowancePoints);
        tvPointsName = findViewById(R.id.textViewPoints);
        tvCalories = findViewById(R.id.textViewCalories);
        tvDAC = findViewById(R.id.textViewUserDAC);
        tvProteinName = findViewById(R.id.textViewProtein);
        tvFatName = findViewById(R.id.textViewFat);
        tvCarbName = findViewById(R.id.textViewCarb);
        tvProteinAllowance = findViewById(R.id.textViewProteinAllowance);
        tvFatAllowance = findViewById(R.id.textViewFatAllowance);
        tvCarbAllowance = findViewById(R.id.textViewCarbsAllowance);
        dietChoice = findViewById(R.id.spinnerDietChoice);
        tvProteinGrams = findViewById(R.id.textViewProteinGrams);
        tvCarbsGrams = findViewById(R.id.textViewCarbsGrams);
        tvFatsGrams = findViewById(R.id.textViewFatGrams);
        setSWDiet = findViewById(R.id.buttonSetSWDiet);
        setWWDiet = findViewById(R.id.buttonSetWWDiet);
        setMacroDiet = findViewById(R.id.buttonSetMacroDiet);
        listProfileDetails();

        dietChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = (String) parent.getItemAtPosition(position);
                if (value.equals("Slimming World")) {
                    //set others to invisible
                    tvWeight.setVisibility(View.INVISIBLE);
                    tvWeightNumber.setVisibility(View.INVISIBLE);
                    tvKG.setVisibility(View.INVISIBLE);
                    tvWWallowance.setVisibility(View.INVISIBLE);
                    tvAllowancePoints.setVisibility(View.INVISIBLE);
                    tvPointsName.setVisibility(View.INVISIBLE);
                    tvCalories.setVisibility(View.INVISIBLE);
                    tvDAC.setVisibility(View.INVISIBLE);
                    tvProteinName.setVisibility(View.INVISIBLE);
                    tvFatName.setVisibility(View.INVISIBLE);
                    tvCarbName.setVisibility(View.INVISIBLE);
                    tvCarbAllowance.setVisibility(View.INVISIBLE);
                    tvFatAllowance.setVisibility(View.INVISIBLE);
                    tvProteinAllowance.setVisibility(View.INVISIBLE);
                    tvProteinGrams.setVisibility(View.INVISIBLE);
                    tvCarbsGrams.setVisibility(View.INVISIBLE);
                    tvFatsGrams.setVisibility(View.INVISIBLE);
                    setWWDiet.setVisibility(View.INVISIBLE);
                    setMacroDiet.setVisibility(View.INVISIBLE);

                    //sets slimming world to visible on choice
                    tvslimWorldAllowance.setVisibility(View.VISIBLE);
                    setSyns.setVisibility(View.VISIBLE);
                    tvSyns.setVisibility(View.VISIBLE);
                    setSWDiet.setVisibility(View.VISIBLE);

                } else if (value.equals("Weight Watchers")) {
                    //set others to invisible
                    tvslimWorldAllowance.setVisibility(View.INVISIBLE);
                    setSyns.setVisibility(View.INVISIBLE);
                    tvSyns.setVisibility(View.INVISIBLE);
                    tvCalories.setVisibility(View.INVISIBLE);
                    tvDAC.setVisibility(View.INVISIBLE);
                    tvProteinName.setVisibility(View.INVISIBLE);
                    tvFatName.setVisibility(View.INVISIBLE);
                    tvCarbName.setVisibility(View.INVISIBLE);
                    tvCarbAllowance.setVisibility(View.INVISIBLE);
                    tvFatAllowance.setVisibility(View.INVISIBLE);
                    tvProteinAllowance.setVisibility(View.INVISIBLE);
                    tvProteinGrams.setVisibility(View.INVISIBLE);
                    tvCarbsGrams.setVisibility(View.INVISIBLE);
                    tvFatsGrams.setVisibility(View.INVISIBLE);
                    setSWDiet.setVisibility(View.INVISIBLE);
                    setMacroDiet.setVisibility(View.INVISIBLE);

                    // sets weight watchers diet to visible
                    setWWAllowancePoints(weightNumber);
                    tvWeight.setVisibility(View.VISIBLE);
                    tvWeightNumber.setVisibility(View.VISIBLE);
                    tvWeightNumber.setText(weight);
                    tvKG.setVisibility(View.VISIBLE);
                    tvWWallowance.setVisibility(View.VISIBLE);
                    tvAllowancePoints.setVisibility(View.VISIBLE);
                    tvPointsName.setVisibility(View.VISIBLE);
                    setWWDiet.setVisibility(View.VISIBLE);

                } else if (value.equals("Macro Diet")) {
                    // sets other diets to invisible
                    tvslimWorldAllowance.setVisibility(View.INVISIBLE);
                    setSyns.setVisibility(View.INVISIBLE);
                    tvSyns.setVisibility(View.INVISIBLE);
                    tvWeight.setVisibility(View.INVISIBLE);
                    tvWeightNumber.setVisibility(View.INVISIBLE);
                    tvKG.setVisibility(View.INVISIBLE);
                    tvWWallowance.setVisibility(View.INVISIBLE);
                    tvAllowancePoints.setVisibility(View.INVISIBLE);
                    tvPointsName.setVisibility(View.INVISIBLE);
                    setSWDiet.setVisibility(View.INVISIBLE);
                    setWWDiet.setVisibility(View.INVISIBLE);

                    //sets macros diet to visible with running the functions
                    setProteinAllowance(DACnumber);
                    setCarbsAllowance(DACnumber);
                    setFatAllowance(DACnumber);
                    tvCalories.setVisibility(View.VISIBLE);
                    tvDAC.setVisibility(View.VISIBLE);
                    tvDAC.setText(DAC);
                    tvProteinName.setVisibility(View.VISIBLE);
                    tvFatName.setVisibility(View.VISIBLE);
                    tvCarbName.setVisibility(View.VISIBLE);
                    tvCarbAllowance.setVisibility(View.VISIBLE);
                    tvFatAllowance.setVisibility(View.VISIBLE);
                    tvProteinAllowance.setVisibility(View.VISIBLE);
                    tvProteinGrams.setVisibility(View.VISIBLE);
                    tvCarbsGrams.setVisibility(View.VISIBLE);
                    tvFatsGrams.setVisibility(View.VISIBLE);
                    setMacroDiet.setVisibility(View.VISIBLE);

                } else {
                    tvslimWorldAllowance.setVisibility(View.INVISIBLE);
                    setSyns.setVisibility(View.INVISIBLE);
                    tvSyns.setVisibility(View.INVISIBLE);
                    tvWeight.setVisibility(View.INVISIBLE);
                    tvWeightNumber.setVisibility(View.INVISIBLE);
                    tvKG.setVisibility(View.INVISIBLE);
                    tvWWallowance.setVisibility(View.INVISIBLE);
                    tvAllowancePoints.setVisibility(View.INVISIBLE);
                    tvPointsName.setVisibility(View.INVISIBLE);
                    tvCalories.setVisibility(View.INVISIBLE);
                    tvDAC.setVisibility(View.INVISIBLE);
                    tvProteinName.setVisibility(View.INVISIBLE);
                    tvFatName.setVisibility(View.INVISIBLE);
                    tvCarbName.setVisibility(View.INVISIBLE);
                    tvCarbAllowance.setVisibility(View.INVISIBLE);
                    tvFatAllowance.setVisibility(View.INVISIBLE);
                    tvProteinAllowance.setVisibility(View.INVISIBLE);
                    tvProteinGrams.setVisibility(View.INVISIBLE);
                    tvCarbsGrams.setVisibility(View.INVISIBLE);
                    tvFatsGrams.setVisibility(View.INVISIBLE);
                    setSWDiet.setVisibility(View.INVISIBLE);
                    setWWDiet.setVisibility(View.INVISIBLE);
                    setMacroDiet.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setSWDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int syns = 0;

                if (syns > 15 && syns <= 0) {

                    AlertDialog alertDialog = new AlertDialog.Builder(diet_page.this).create();
                    alertDialog.setTitle("Error!");
                    alertDialog.setMessage("Please enter an amount between 1 to 15!");
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                } else {
                    syns = Integer.parseInt(setSyns.getText().toString());
                    deleteData();
                    addSWDiet(syns);

                    Intent intent = new Intent(diet_page.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        setWWDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                addWWDiet(setWWAllowancePoints(weightNumber));

                Intent intent = new Intent(diet_page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setMacroDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                addMacrosDiet(setProteinAllowance(DACnumber), setFatAllowance(DACnumber), setCarbsAllowance(DACnumber));

                Intent intent = new Intent(diet_page.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public int setWWAllowancePoints(double weightNumber) {

        int wwAllowance;

        if (weightNumber <= 68) {
            wwAllowance = 23;
            tvAllowancePoints.setText("23");
        } else if (weightNumber >= 69 && weightNumber <= 79){
            wwAllowance = 25;
            tvAllowancePoints.setText("25");
        } else if (weightNumber >= 80 && weightNumber <= 90){
            wwAllowance = 27;
            tvAllowancePoints.setText("27");
        } else if (weightNumber >= 91 && weightNumber <= 101) {
            wwAllowance = 29;
            tvAllowancePoints.setText("29");
        } else if (weightNumber >= 102 && weightNumber <= 113){
            wwAllowance = 31;
            tvAllowancePoints.setText("31");
        } else if (weightNumber >= 114 && weightNumber <= 124){
            wwAllowance = 33;
            tvAllowancePoints.setText("33");
        } else if (weightNumber >= 125 && weightNumber <= 135){
            wwAllowance = 34;
            tvAllowancePoints.setText("34");
        } else if (weightNumber >= 136 && weightNumber <= 147){
            wwAllowance = 35;
            tvAllowancePoints.setText("35");
        } else if (weightNumber >= 148 && weightNumber <= 158){
            wwAllowance = 36;
            tvAllowancePoints.setText("36");
        } else {
            wwAllowance = 37;
            tvAllowancePoints.setText("37");
        }
        return wwAllowance;
    }

    public double setProteinAllowance(double DACnumber){

        double proteins;
        String proteinString;
        proteins = (DACnumber * 0.4) / 4;
        proteinString = Double.toString(Math.round(proteins));
        tvProteinAllowance.setText(proteinString);
        return proteins;
    }

    public double setCarbsAllowance(double DACnumber){

        double carbs;
        String carbsString;
        carbs = (DACnumber * 0.4) / 4;
        carbsString = Double.toString(Math.round(carbs));
        tvCarbAllowance.setText(carbsString);
        return carbs;
    }

    public double setFatAllowance(double DACnumber){

        double fats;
        String fatsString;
        fats = (DACnumber * 0.2) / 9;
        fatsString = Double.toString(Math.round(fats));
        tvFatAllowance.setText(fatsString);
        return fats;
    }

    public void listProfileDetails() {
        Cursor cursor = profDatabase.listProfile();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            weightNumber = cursor.getDouble(2);
            weight = cursor.getString(2);
            DAC = cursor.getString(7);
            DACnumber = cursor.getDouble(7);
        }
    }

    public void deleteData() {
         dietDatabase.deleteDiet();
    }

    public void addSWDiet(int syns){
        String name = "Slimming World";
        boolean addData = dietDatabase.addDietSlimmingWorld(name, syns);

        if (addData) {
            Toast.makeText(this, "Diet set!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to set diet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addWWDiet(int points){
        String name = "Weight Watchers";
        boolean addData = dietDatabase.addDietWeightWatchers(name, points);

        if (addData) {
            Toast.makeText(this, "Diet set!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to set diet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addMacrosDiet(double protein, double fat, double carbs){
        String name = "Macro Diet";
        boolean addData = dietDatabase.addDietMacro(name, protein, fat, carbs);

        if (addData) {
            Toast.makeText(this, "Diet set!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to set diet!", Toast.LENGTH_SHORT).show();
        }
    }
}
