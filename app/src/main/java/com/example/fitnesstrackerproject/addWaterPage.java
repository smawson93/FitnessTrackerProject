package com.example.fitnesstrackerproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class addWaterPage extends AppCompatActivity {

    Database diaryDatabase;
    EditText waterAmountET;
    Button add500ml, add1000ml, add2000ml, saveAmount;
    String date;
    int waterAmount;
    ImageButton backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_water_layout);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            date = bundle.getString("date");
        }

        diaryDatabase = new Database(this, "TABLE_NAME_DIARY");
        waterAmountET = findViewById(R.id.editTextWaterAmount);
        add500ml = findViewById(R.id.button500ML);
        add1000ml = findViewById(R.id.button1000ML);
        add2000ml = findViewById(R.id.button2000ML);
        saveAmount = findViewById(R.id.buttonSaveWater);
        backButton = findViewById(R.id.imageButtonWaterBack);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addWaterPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        add500ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWater(500);
                Intent intent = new Intent(addWaterPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        add1000ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWater(1000);
                Intent intent = new Intent(addWaterPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        add2000ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateWater(2000);
                Intent intent = new Intent(addWaterPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });

        saveAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterAmount = Integer.parseInt(waterAmountET.getText().toString());
                updateWater(waterAmount);
                Intent intent = new Intent(addWaterPage.this, daily_diary_entry.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }

    public void updateWater(int water){
        boolean update = diaryDatabase.updateWater(water,date);
        if (update){
            Toast.makeText(this,"Water Added!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}
