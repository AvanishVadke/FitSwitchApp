package com.miniproject.fitswitchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Exercises extends AppCompatActivity {
    private EditText editWeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);


        editWeight = findViewById(R.id.editWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateSteps();
            }
        });
    }

    private void calculateSteps() {
        String weightStr = editWeight.getText().toString();
        if (weightStr.isEmpty()) {
            tvResult.setText("Please enter your weight.");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        int minSteps, maxSteps;

        if (weight < 80) {
            minSteps = 8000;
            maxSteps = 10000;
        } else {
            minSteps = 10000;
            maxSteps = 12000;
        }

        tvResult.setText("Recommended Steps Range: " + minSteps + " - " + maxSteps + " steps per day");
    }
}