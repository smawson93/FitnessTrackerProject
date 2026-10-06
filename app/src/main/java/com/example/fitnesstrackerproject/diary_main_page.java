package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class diary_main_page extends AppCompatActivity {

    Database mDatabase, dietDatabase;
    ArrayList<String> date, totalCalories;
    RecyclerView recyclerView;
    DiaryAdapter diaryAdapter;
    CardView diaryCardView;
    Button newDayButton;
    String dietName;
    TextView tvDietName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main_page);
        recyclerView = findViewById(R.id.recyclerViewDiary);
        mDatabase = new Database(diary_main_page.this, "TABLE_NAME_DIARY");
        dietDatabase = new Database(diary_main_page.this, "TABLE_NAME_DIET");
        date = new ArrayList<>();
        totalCalories = new ArrayList<>();
        newDayButton = findViewById(R.id.buttonNewDay);
        tvDietName = findViewById(R.id.textViewDiet);

        listDiaryData();
        setDiet();

        diaryAdapter = new DiaryAdapter(diary_main_page.this, date, totalCalories);
        recyclerView.setAdapter(diaryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(diary_main_page.this));
        diaryCardView = findViewById(R.id.diaryListCardView);

        for (int i = 0; i < date.size(); i++) {
            if (setDate().equals(date.get(i))) {
                newDayButton.setVisibility(View.INVISIBLE);
            } else {
                newDayButton.setVisibility(View.VISIBLE);
            }
        }
        newDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDiaryDataInput(setDate(), 0, 0,0,0,0,0,0);
                Intent intent = new Intent(diary_main_page.this, daily_diary_entry.class);
                intent.putExtra("date", setDate());
                startActivity(intent);
            }
        });
    }

    public void listDiaryData() {
        Cursor cursor = mDatabase.listAllDiaryData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }else  {
            while (cursor.moveToNext()){
                date.add(cursor.getString(0));
                totalCalories.add(cursor.getString(1));
            }
        }
    }

    public void setDiet() {
        Cursor cursor = dietDatabase.getDietChoice();
        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();
            dietName = cursor.getString(0);
            tvDietName.setText(dietName);
        }
    }

    public void newDiaryDataInput(String date, int totalCalories, int water, int syns, int points, double proteins, double fat, double carbs) {
        boolean addNewData = mDatabase.newDiaryDataInput(date, totalCalories, water, syns, points, proteins, fat, carbs);

        if (addNewData) {
            toastMessage("New Day Made!");
        } else {
            toastMessage("Error when adding new day!");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public String setDate() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String stringdate = dt.format(date);
        return stringdate;
    }

}
