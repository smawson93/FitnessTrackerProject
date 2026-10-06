package com.example.fitnesstrackerproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

public class daily_diary_entry extends AppCompatActivity {

    Database mainFoodData, breakfastFoodData, lunchFoodData, dinnerFoodData, dietDatabase, profDatabase;
    String date,currentCalories, totalCalories, water, dietName, synsCurrent, synsMax, pointsCurrent, pointsMax, proteinsCurrent, proteinsMax, fatsCurrent, fatsMax, carbsCurrent, carbsMax;
    ArrayList<String> breakfastFood, breakfastcalories, lunchFood, lunchCalories, dinnerFood, dinnerCalories;
    RecyclerView breakfastRecycler, lunchRecycler, dinnerRecycler;
    breakfast_listAdapter breakfast_listAdapter;
    lunch_listAdapter lunch_listAdapter;
    dinner_listAdapter dinner_listAdapter;
    CardView foodCardView;
    TextView datetv, currentCaloriestv, totalCaloriestv, watertv, synsNametv, synsCurrenttv, synsSeptv, synsMaxtv, pointsNametv, pointsCurrenttv, pointsSeptv, pointsMaxtv, proteinsNametv, proteinsCurrenttv, proteinsSeptv, proteinsMaxtv;
    TextView fatsNametv, fatsCurrenttv, fatsSeptv, fatsMaxtv, carbsNametv, carbsCurrenttv, carbsSeptv, carbsMaxtv;
    Button addBreakfastButton, addLunchButton, addDinnerButton, addWaterButton, deleteButton;
    int synsCurrentNumber, synsTotalNumber, pointsCurrentNumber, pointsTotalNumber;
    double caloriesCurrentNumber, caloriesTotalNumber, proteinCurrentNumber, proteinTotalNumber, fatsCurrentNumber, fatsTotalNumber, carbsCurrentNumber, carbsTotalNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_diary_entry);

        // All the layout id's used on this page.
        datetv = findViewById(R.id.dailyDiaryDate);
        totalCaloriestv = findViewById(R.id.dailyDiaryTotalCalories);
        watertv = findViewById(R.id.tvWater);
        foodCardView = findViewById(R.id.foodCardView);
        addBreakfastButton = findViewById(R.id.buttonAddToBreakfast);
        addLunchButton = findViewById(R.id.buttonAddToLunch);
        addDinnerButton = findViewById(R.id.buttonAddToDinner);
        addWaterButton = findViewById(R.id.buttonAddToWater);
        breakfastRecycler = findViewById(R.id.breakfastRecyclerView);
        lunchRecycler = findViewById(R.id.lunchRecyclerView);
        dinnerRecycler = findViewById(R.id.dinnerRecylclerView);
        deleteButton = findViewById(R.id.buttonSaveNew);
        currentCaloriestv = findViewById(R.id.textViewCurrentCalories);
        //Diet find Views
        synsNametv = findViewById(R.id.textViewSyns);
        synsCurrenttv = findViewById(R.id.textViewSynsCurrent);
        synsSeptv = findViewById(R.id.textViewSynsSep);
        synsMaxtv = findViewById(R.id.textViewSynsMax);
        pointsNametv = findViewById(R.id.textViewWeightWatchers);
        pointsCurrenttv = findViewById(R.id.textViewWWCurrent);
        pointsSeptv = findViewById(R.id.textViewWWSep);
        pointsMaxtv = findViewById(R.id.textViewWWMAx);
        proteinsNametv = findViewById(R.id.textViewProtein);
        proteinsCurrenttv = findViewById(R.id.textViewProteinCurrent);
        proteinsSeptv = findViewById(R.id.textViewProteinSep);
        proteinsMaxtv = findViewById(R.id.textViewProteinMax);
        fatsNametv = findViewById(R.id.textViewFats);
        fatsCurrenttv = findViewById(R.id.textViewFatCurrent);
        fatsSeptv = findViewById(R.id.textViewFatSep);
        fatsMaxtv = findViewById(R.id.textViewFatMax);
        carbsNametv = findViewById(R.id.textViewCarbs);
        carbsCurrenttv = findViewById(R.id.textViewCarbsCurrent);
        carbsSeptv = findViewById(R.id.textViewCarbsSep);
        carbsMaxtv = findViewById(R.id.textViewCarbsMax);

        //all the database tables used and initialized below.
        profDatabase = new Database(daily_diary_entry.this, "TABLE_NAME_PROFILE");
        mainFoodData = new Database(daily_diary_entry.this, "TABLE_NAME_DIARY");
        breakfastFoodData = new Database(daily_diary_entry.this, "TABLE_NAME_BREAKFAST_LIST");
        lunchFoodData = new Database(daily_diary_entry.this, "TABLE_NAME_LUNCH_LIST");
        dinnerFoodData = new Database(daily_diary_entry.this, "TABLE_NAME_DINNER_LIST");
        dietDatabase = new Database(daily_diary_entry.this, "TABLE_NAME_DIET");

        breakfastFood = new ArrayList<>();
        breakfastcalories = new ArrayList<>();
        lunchFood = new ArrayList<>();
        lunchCalories = new ArrayList<>();
        dinnerFood = new ArrayList<>();
        dinnerCalories = new ArrayList<>();
        breakfast_listAdapter = new breakfast_listAdapter(daily_diary_entry.this, breakfastFood, breakfastcalories);
        lunch_listAdapter = new lunch_listAdapter(daily_diary_entry.this, lunchFood, lunchCalories);
        dinner_listAdapter = new dinner_listAdapter(daily_diary_entry.this, dinnerFood, dinnerCalories);
        breakfastRecycler.setAdapter(breakfast_listAdapter);
        lunchRecycler.setAdapter(lunch_listAdapter);
        dinnerRecycler.setAdapter(dinner_listAdapter);
        breakfastRecycler.setLayoutManager(new LinearLayoutManager(daily_diary_entry.this));
        lunchRecycler.setLayoutManager(new LinearLayoutManager(daily_diary_entry.this));
        dinnerRecycler.setLayoutManager(new LinearLayoutManager(daily_diary_entry.this));


        getProfileData();
        getDietData();

        if (dietName.equals("Slimming World")) {
            //Set Slimming world params to visible
            synsNametv.setVisibility(View.VISIBLE);
            synsCurrenttv.setVisibility(View.VISIBLE);
            synsSeptv.setVisibility(View.VISIBLE);
            synsMaxtv.setVisibility(View.VISIBLE);

            //set the rest to invisible
            pointsNametv.setVisibility(View.INVISIBLE);
            pointsCurrenttv.setVisibility(View.INVISIBLE);
            pointsSeptv.setVisibility(View.INVISIBLE);
            pointsMaxtv.setVisibility(View.INVISIBLE);
            proteinsNametv.setVisibility(View.INVISIBLE);
            proteinsCurrenttv.setVisibility(View.INVISIBLE);
            proteinsSeptv.setVisibility(View.INVISIBLE);
            proteinsMaxtv.setVisibility(View.INVISIBLE);
            fatsNametv.setVisibility(View.INVISIBLE);
            fatsCurrenttv.setVisibility(View.INVISIBLE);
            fatsSeptv.setVisibility(View.INVISIBLE);
            fatsMaxtv.setVisibility(View.INVISIBLE);
            carbsNametv.setVisibility(View.INVISIBLE);
            carbsCurrenttv.setVisibility(View.INVISIBLE);
            carbsSeptv.setVisibility(View.INVISIBLE);
            carbsMaxtv.setVisibility(View.INVISIBLE);

        } else if (dietName.equals("Weight Watchers")) {
            //set weight watchers to visible
            pointsNametv.setVisibility(View.VISIBLE);
            pointsCurrenttv.setVisibility(View.VISIBLE);
            pointsSeptv.setVisibility(View.VISIBLE);
            pointsMaxtv.setVisibility(View.VISIBLE);

            //set the rest to invisible
            synsNametv.setVisibility(View.INVISIBLE);
            synsCurrenttv.setVisibility(View.INVISIBLE);
            synsSeptv.setVisibility(View.INVISIBLE);
            synsMaxtv.setVisibility(View.INVISIBLE);
            proteinsNametv.setVisibility(View.INVISIBLE);
            proteinsCurrenttv.setVisibility(View.INVISIBLE);
            proteinsSeptv.setVisibility(View.INVISIBLE);
            proteinsMaxtv.setVisibility(View.INVISIBLE);
            fatsNametv.setVisibility(View.INVISIBLE);
            fatsCurrenttv.setVisibility(View.INVISIBLE);
            fatsSeptv.setVisibility(View.INVISIBLE);
            fatsMaxtv.setVisibility(View.INVISIBLE);
            carbsNametv.setVisibility(View.INVISIBLE);
            carbsCurrenttv.setVisibility(View.INVISIBLE);
            carbsSeptv.setVisibility(View.INVISIBLE);
            carbsMaxtv.setVisibility(View.INVISIBLE);

        } else if (dietName.equals("Macro Diet")) {
            //set macro params to visible
            proteinsNametv.setVisibility(View.VISIBLE);
            proteinsCurrenttv.setVisibility(View.VISIBLE);
            proteinsSeptv.setVisibility(View.VISIBLE);
            proteinsMaxtv.setVisibility(View.VISIBLE);
            fatsNametv.setVisibility(View.VISIBLE);
            fatsCurrenttv.setVisibility(View.VISIBLE);
            fatsSeptv.setVisibility(View.VISIBLE);
            fatsMaxtv.setVisibility(View.VISIBLE);
            carbsNametv.setVisibility(View.VISIBLE);
            carbsCurrenttv.setVisibility(View.VISIBLE);
            carbsSeptv.setVisibility(View.VISIBLE);
            carbsMaxtv.setVisibility(View.VISIBLE);

            //set the rest to invisible
            synsNametv.setVisibility(View.INVISIBLE);
            synsCurrenttv.setVisibility(View.INVISIBLE);
            synsSeptv.setVisibility(View.INVISIBLE);
            synsMaxtv.setVisibility(View.INVISIBLE);
            pointsNametv.setVisibility(View.INVISIBLE);
            pointsCurrenttv.setVisibility(View.INVISIBLE);
            pointsSeptv.setVisibility(View.INVISIBLE);
            pointsMaxtv.setVisibility(View.INVISIBLE);

        } else if (dietName.equals("No Diet Set")){
            synsNametv.setVisibility(View.INVISIBLE);
            synsCurrenttv.setVisibility(View.INVISIBLE);
            synsSeptv.setVisibility(View.INVISIBLE);
            synsMaxtv.setVisibility(View.INVISIBLE);
            pointsNametv.setVisibility(View.INVISIBLE);
            pointsCurrenttv.setVisibility(View.INVISIBLE);
            pointsSeptv.setVisibility(View.INVISIBLE);
            pointsMaxtv.setVisibility(View.INVISIBLE);
            proteinsNametv.setVisibility(View.INVISIBLE);
            proteinsCurrenttv.setVisibility(View.INVISIBLE);
            proteinsSeptv.setVisibility(View.INVISIBLE);
            proteinsMaxtv.setVisibility(View.INVISIBLE);
            fatsNametv.setVisibility(View.INVISIBLE);
            fatsCurrenttv.setVisibility(View.INVISIBLE);
            fatsSeptv.setVisibility(View.INVISIBLE);
            fatsMaxtv.setVisibility(View.INVISIBLE);
            carbsNametv.setVisibility(View.INVISIBLE);
            carbsCurrenttv.setVisibility(View.INVISIBLE);
            carbsSeptv.setVisibility(View.INVISIBLE);
            carbsMaxtv.setVisibility(View.INVISIBLE);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            date = bundle.getString("date");
            showMainData(date);
            listBreakfastFood(date);
            listLunchFood(date);
            listDinnerFood(date);
            datetv.setText(date);
        }

        addBreakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daily_diary_entry.this, addToBreakfastListPage.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        addLunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daily_diary_entry.this, addToLunchListPage.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        addDinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daily_diary_entry.this, addToDinnerListPage.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        addWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daily_diary_entry.this, addWaterPage.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMainEntry();
                deleteBreakfastEntry();
                deleteLunchEntry();
                deleteDinnerEntry();
                Intent intent = new Intent(daily_diary_entry.this, diary_main_page.class);
                startActivity(intent);
            }
        });

        showDialogs();
    }


    public void showDialogs() {

        boolean calorieMessage = false, synMessage = false, wwMessage = false, proteinMessage = false, fatMessage = false, carbsMessage = false;
        String message = null;

        for(int i = 0; i < 7; i++) {
            if (!calorieMessage && caloriesCurrentNumber >= caloriesTotalNumber) {

                message = "You have reached the daily limit of calories!";
                calorieMessage = true;

            } else if (!synMessage && dietName.equals("Slimming World") && synsCurrentNumber >= synsTotalNumber) {

                message = "You have reached the daily limit of syns!";
                synMessage = true;

            } else if (!wwMessage && dietName.equals("Weight Watchers") && pointsCurrentNumber >= pointsTotalNumber) {

                message = "You have reached the daily limit of points!";
                wwMessage = true;

            } else if (!proteinMessage && dietName.equals("Macro Diet") && proteinCurrentNumber >= proteinTotalNumber) {

                message = "You have reached the daily limit of proteins!";
                proteinMessage = true;

            } else if (!fatMessage && dietName.equals("Macro Diet") && fatsCurrentNumber >= fatsTotalNumber) {

                message = "You have reached the daily limit of fats!";
                fatMessage = true;

            } else if (!carbsMessage && dietName.equals("Macro Diet") && carbsCurrentNumber >= carbsTotalNumber) {

                message = "You have reached the daily limit of carbs!";
                carbsMessage = true;

            } else {
                break;
            }

            AlertDialog alertDialog = new AlertDialog.Builder(daily_diary_entry.this).create();
            alertDialog.setTitle("Limit Reached!");
            alertDialog.setMessage(message);
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
    }
    public void deleteMainEntry() {
        boolean delete = mainFoodData.deleteDiaryEntry(date);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBreakfastEntry() {
        boolean delete = breakfastFoodData.deleteBreakfastEntries(date);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteLunchEntry() {
        boolean delete = lunchFoodData.deleteLunchEntries(date);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDinnerEntry() {
        boolean delete = dinnerFoodData.deleteDinnerEntries(date);

        if(delete){
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Was unable to delete data!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showMainData(String date) {
        Cursor cursor = mainFoodData.singleDiaryDataEntry(date);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            currentCalories=cursor.getString(1);
            currentCaloriestv.setText(currentCalories);
            water=cursor.getString(2);
            watertv.setText(water);

            synsCurrent = cursor.getString(3);
            pointsCurrent = cursor.getString(4);
            proteinsCurrent = cursor.getString(5);
            fatsCurrent = cursor.getString(6);
            carbsCurrent = cursor.getString(7);
            synsCurrenttv.setText(synsCurrent);
            pointsCurrenttv.setText(pointsCurrent);
            proteinsCurrenttv.setText(proteinsCurrent);
            fatsCurrenttv.setText(fatsCurrent);
            carbsCurrenttv.setText(carbsCurrent);

            caloriesCurrentNumber = cursor.getDouble(1);
            synsCurrentNumber = cursor.getInt(3);
            pointsCurrentNumber = cursor.getInt(4);
            proteinCurrentNumber = cursor.getDouble(5);
            fatsCurrentNumber = cursor.getDouble(6);
            carbsCurrentNumber = cursor.getDouble(7);
        }
        }

    public void listBreakfastFood(String date){
        Cursor cursor = breakfastFoodData.listBreakfastFoods(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                breakfastFood.add(cursor.getString(2));
                breakfastcalories.add(cursor.getString(3));
            }
        }
    }

    public void listLunchFood(String date){
        Cursor cursor = lunchFoodData.listLunchFoods(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                lunchFood.add(cursor.getString(2));
                lunchCalories.add(cursor.getString(3));
            }
        }
    }

    public void listDinnerFood(String date){
        Cursor cursor = dinnerFoodData.listDinnerFoods(date);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                dinnerFood.add(cursor.getString(2));
                dinnerCalories.add(cursor.getString(3));
            }
        }
    }

    public void getDietData(){
        Cursor cursor = dietDatabase.getDietChoice();
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            dietName = cursor.getString(0);
            synsMax = cursor.getString(1);
            pointsMax = cursor.getString(2);
            proteinsMax = cursor.getString(3);
            fatsMax = cursor.getString(4);
            carbsMax = cursor.getString(5);

            synsTotalNumber = cursor.getInt(1);
            pointsTotalNumber = cursor.getInt(2);
            proteinTotalNumber = cursor.getDouble(3);
            fatsTotalNumber = cursor.getDouble(4);
            carbsTotalNumber = cursor.getDouble(5);

            synsMaxtv.setText(synsMax);
            pointsMaxtv.setText(pointsMax);
            proteinsMaxtv.setText(proteinsMax);
            fatsMaxtv.setText(fatsMax);
            carbsMaxtv.setText(carbsMax);
        } else {
            dietName = "No Diet Set";
        }
    }

    public void getProfileData(){
        Cursor cursor = profDatabase.listProfile();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            totalCalories = cursor.getString(7);
            totalCaloriestv.setText(totalCalories);
            caloriesTotalNumber = cursor.getDouble(7);
        }
    }
}