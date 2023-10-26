package com.miniproject.fitswitchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeartRate extends AppCompatActivity {
    private TextView heartRateTextView;
    private int simulatedHeartRate = 70; // Simulated heart rate value
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        heartRateTextView = findViewById(R.id.heartRateTextView);

        // Create a runnable to update the heart rate value every second
        Runnable updateHeartRate = new Runnable() {
            @Override
            public void run() {
                // In a real application, you would get the actual heart rate data from the sensor here.
                // For this example, we use a simulated value.
                simulatedHeartRate = (simulatedHeartRate + 1) % 120; // Simulate a heart rate value between 0 and 119.

                // Update the UI with the new heart rate value.
                heartRateTextView.setText("Heart Rate: " + simulatedHeartRate);

                // Schedule the update again after a delay (e.g., 1 second).
                handler.postDelayed(this, 1000);
            }
        };

        // Start the heart rate update loop
        handler.post(updateHeartRate);
    }
}
