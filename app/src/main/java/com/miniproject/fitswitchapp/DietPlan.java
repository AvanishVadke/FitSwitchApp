package com.miniproject.fitswitchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DietPlan extends AppCompatActivity {

    private EditText editWeight;
    private Button btnRecommend;
    private TextView tvRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        editWeight = findViewById(R.id.editWeight);
        btnRecommend = findViewById(R.id.btnRecommend);
        tvRecommendation = findViewById(R.id.tvRecommendation);

        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendDiet();
            }
        });
    }

    private void recommendDiet() {
        String weightStr = editWeight.getText().toString();
        if (weightStr.isEmpty()) {
            tvRecommendation.setText("Please enter your weight.");
            return;
        }

        double weight = Double.parseDouble(weightStr);
        String recommendation;

        if (weight < 50) {
            recommendation = "Recommended Diet for Weight < 50kg:\n" +
                    "Breakfast: Scrambled eggs with spinach\n" +
                    "Lunch: Grilled chicken salad\n" +
                    "Dinner: Baked salmon with asparagus\n" +
                    "Snacks: Greek yogurt, mixed nuts";
        } else if (weight < 80) {
            recommendation = "Recommended Diet for Weight 50-80kg:\n" +
                    "Breakfast: Oatmeal with fruits\n" +
                    "Lunch: Quinoa and vegetable stir-fry\n" +
                    "Dinner: Baked or Grilled Salmon\n" +
                    "Snacks: Fresh fruit, hummus with veggies";
        } else {
            recommendation = "Recommended Diet for Weight > 80kg:\n" +
                    "Breakfast: Whole-grain cereal with milk\n" +
                    "Lunch: Brown rice and black bean bowl\n" +
                    "Dinner: Grilled salmon with quinoa\n" +
                    "Snacks: Low-fat cottage cheese, rice cakes";
        }

        tvRecommendation.setText(recommendation);
    }
}