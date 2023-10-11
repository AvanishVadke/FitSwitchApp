package com.miniproject.fitswitchapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMICalculator extends Activity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private TextView textViewResult;
    private TextView dietPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        textViewResult = findViewById(R.id.textViewResult);
        dietPlan = findViewById(R.id.textViewComment);
    }

    public void calculateBMI(View view) {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            float bmi = calculateBMIValue(weight, height);

            String result = getString(R.string.bmi_result, bmi);
            textViewResult.setText(result);

            String suggestion = getSuggestion(result,bmi);
            dietPlan.setText(suggestion);

        } else {
            textViewResult.setText(getString(R.string.invalid_input));
        }
    }

    private String getSuggestion(String result, double valBMI) {
        if (valBMI < 18.5) {
            return "You are underweight. Please consult a doctor or nutritionist.";
        } else if (valBMI >= 18.5 && valBMI < 24.9) {
            return "You have a healthy weight. Keep up the good work!";
        } else if (valBMI >= 25 && valBMI < 29.9) {
            return "You are overweight. Consider a balanced diet and exercise.";
        } else {
            return "You are obese. Please consult a doctor for guidance.";
        }
    }

    private float calculateBMIValue(float weight, float height) {
        // BMI formula: BMI = weight(kg) / (height(m) * height(m))
        return weight / (height * height);
    }
}
