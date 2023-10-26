package com.miniproject.fitswitchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalorieCount extends AppCompatActivity {

    private EditText stepsEditText;
    private EditText weightEditText;
    private TextView caloriesBurnedTextView;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_count);

        stepsEditText = findViewById(R.id.editTextSteps);
        weightEditText = findViewById(R.id.editTextWeight);
        caloriesBurnedTextView = findViewById(R.id.textViewCaloriesBurned);
        calculateButton = findViewById(R.id.buttonCalculate);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCaloriesBurned();
            }
        });
    }

    private void calculateCaloriesBurned() {
        String stepsStr = stepsEditText.getText().toString();
        String weightStr = weightEditText.getText().toString();

        if (stepsStr.isEmpty() || weightStr.isEmpty()) {
            caloriesBurnedTextView.setText("Please enter both steps and weight.");
            return;
        }

        int steps = Integer.parseInt(stepsStr);
        double weight = Double.parseDouble(weightStr);

        // Calculate approximate calories burned per step
        double caloriesPerStep = 0.04; // This is a rough estimate, it can vary.

        // Calculate total calories burned
        double caloriesBurned = steps * caloriesPerStep;

        caloriesBurnedTextView.setText("Calories Burned: " + caloriesBurned + " kcal");
    }
}