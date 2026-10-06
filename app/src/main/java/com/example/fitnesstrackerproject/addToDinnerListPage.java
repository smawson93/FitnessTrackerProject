package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addToDinnerListPage extends AppCompatActivity {

    Database dinnerListDatabase, diaryDatabase, dietDatabase;
    String name, date, dietName;
    int calories, syns, points;
    double proteins, fats, carbs;
    EditText nameET, caloriesET, addSynsET, addPointsET, addProteinET, addFatsET, addCarbsET;
    TextView synsName, pointsName, proteinName, fatsName, carbsName;
    Button addFoodButton;
    ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_dinner);
        nameET = findViewById(R.id.editTextNameDinner);
        caloriesET = findViewById(R.id.editTextCalorieDinner);
        addFoodButton = findViewById(R.id.buttonSaveDinnerFood);
        addSynsET = findViewById(R.id.editTextAddSyns3);
        addPointsET = findViewById(R.id.editTextAddPoints3);
        addProteinET = findViewById(R.id.editTextAddProteins3);
        addFatsET = findViewById(R.id.editTextAddFats3);
        addCarbsET = findViewById(R.id.editTextAddCarbs3);
        synsName = findViewById(R.id.textViewSynsName3);
        pointsName = findViewById(R.id.textViewWWName3);
        proteinName = findViewById(R.id.textViewProteinName3);
        fatsName = findViewById(R.id.textViewFatsName3);
        carbsName = findViewById(R.id.textViewCarbsName3);
        backButton = findViewById(R.id.imageButtonDinnerBack);

        dinnerListDatabase = new Database(addToDinnerListPage.this, "TABLE_NAME_DINNER_LIST");
        diaryDatabase = new Database(addToDinnerListPage.this, "TABLE_NAME_DIARY");
        dietDatabase = new Database(addToDinnerListPage.this, "TABLE_NAME_DIET");
        getDietName();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            date = bundle.getString("date");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addToDinnerListPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        if(dietName.equals("Slimming World")){
            //set SW to visible
            synsName.setVisibility(View.VISIBLE);
            addSynsET.setVisibility(View.VISIBLE);

            //set rest to invisible
            pointsName.setVisibility(View.INVISIBLE);
            addPointsET.setVisibility(View.INVISIBLE);
            proteinName.setVisibility(View.INVISIBLE);
            addProteinET.setVisibility(View.INVISIBLE);
            fatsName.setVisibility(View.INVISIBLE);
            addFatsET.setVisibility(View.INVISIBLE);
            carbsName.setVisibility(View.INVISIBLE);
            addCarbsET.setVisibility(View.INVISIBLE);
        } else if (dietName.equals("Weight Watchers")){
            //set WW to visible
            pointsName.setVisibility(View.VISIBLE);
            addPointsET.setVisibility(View.VISIBLE);

            //set rest to invisible
            synsName.setVisibility(View.INVISIBLE);
            addSynsET.setVisibility(View.INVISIBLE);
            proteinName.setVisibility(View.INVISIBLE);
            addProteinET.setVisibility(View.INVISIBLE);
            fatsName.setVisibility(View.INVISIBLE);
            addFatsET.setVisibility(View.INVISIBLE);
            carbsName.setVisibility(View.INVISIBLE);
            addCarbsET.setVisibility(View.INVISIBLE);
        } else if (dietName.equals("Macro Diet")){
            //Set Macro to Visible
            proteinName.setVisibility(View.VISIBLE);
            addProteinET.setVisibility(View.VISIBLE);
            fatsName.setVisibility(View.VISIBLE);
            addFatsET.setVisibility(View.VISIBLE);
            carbsName.setVisibility(View.VISIBLE);
            addCarbsET.setVisibility(View.VISIBLE);

            //set rest to invisible
            synsName.setVisibility(View.INVISIBLE);
            addSynsET.setVisibility(View.INVISIBLE);
            pointsName.setVisibility(View.INVISIBLE);
            addPointsET.setVisibility(View.INVISIBLE);
        } else if (dietName.equals("No Diet Set")){
            //set all to invisible
            synsName.setVisibility(View.INVISIBLE);
            addSynsET.setVisibility(View.INVISIBLE);
            pointsName.setVisibility(View.INVISIBLE);
            addPointsET.setVisibility(View.INVISIBLE);
            proteinName.setVisibility(View.INVISIBLE);
            addProteinET.setVisibility(View.INVISIBLE);
            fatsName.setVisibility(View.INVISIBLE);
            addFatsET.setVisibility(View.INVISIBLE);
            carbsName.setVisibility(View.INVISIBLE);
            addCarbsET.setVisibility(View.INVISIBLE);
        }

    }

    public void addNewFoodName(@NonNull String name, int calories) {
        boolean addFoodData = dinnerListDatabase.newDinnerDataInput(date, name, calories);

        if (addFoodData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDiaryData(@NonNull int calories) {
        boolean updateData = diaryDatabase.updateDiaryData(date, calories);

        if (updateData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDiarySyns(@NonNull int calories, int syns) {
        boolean updateData = diaryDatabase.updateDiaryDataSyns(date, calories, syns);

        if (updateData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDiaryPoints(@NonNull int calories, int points) {
        boolean updateData = diaryDatabase.updateDiaryDataPoints(date, calories, points);

        if (updateData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDiaryMacros(@NonNull int calories, double proteins, double fats, double carbs) {
        boolean updateData = diaryDatabase.updateDiaryDataMacros(date, calories, proteins, fats, carbs);

        if (updateData){
            Toast.makeText(this, "Data Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to add data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addNewFoodButtonFunction (View view) {
        name = nameET.getText().toString();
        calories = Integer.parseInt(caloriesET.getText().toString());
        addNewFoodName(name, calories);

        if(dietName.equals("Slimming World")){
            syns = Integer.parseInt(addSynsET.getText().toString());
            updateDiarySyns(calories, syns);
        } else if(dietName.equals("Weight Watchers")){
            points = Integer.parseInt(addPointsET.getText().toString());
            updateDiaryPoints(calories,points);
        } else if(dietName.equals("Macro Diet")){
            proteins = Double.parseDouble(addProteinET.getText().toString());
            fats = Double.parseDouble(addFatsET.getText().toString());
            carbs = Double.parseDouble(addCarbsET.getText().toString());
            updateDiaryMacros(calories, proteins, fats, carbs);
        } else if (dietName.equals("No Diet Set")) {
            updateDiaryData(calories);
        }
        Intent intent = new Intent(addToDinnerListPage.this, daily_diary_entry.class);
        intent.putExtra("date", date);
        //intent.putExtra("calories", calories);
        startActivity(intent);
    }

    public void getDietName(){
        Cursor cursor = dietDatabase.getDietChoice();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            dietName = cursor.getString(0);
        } else {
            dietName = "No Diet Set";
        }
    }

}
