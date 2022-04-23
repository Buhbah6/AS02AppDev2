package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class ConfirmationActivity extends AppCompatActivity {
    private DBHelper db;
    private TextView shipping;
    private TextView delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // retrieves the type of delivery from the intent
        Intent intent = getIntent();
        String method = intent.getStringExtra("shipping");

        shipping = findViewById(R.id.shippingTextView);
        delay = findViewById(R.id.delayTextView);

        // removes the items from favourites, similarly to a cart
        db = new DBHelper(getApplicationContext());
        db.removeAllItemsFromTable("FAVOURITE_ITEMS");

        // displays the chosen method of delivery
        shipping.setText("Method of Delivery: " + method);

        // Timer counts down from 60 to represent the preparation time
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                delay.setText("Order will be ready in: " + millisUntilFinished / 1000 + " Seconds");
            }

            // When the timer reaches 0, it returns to the first page
            public void onFinish() {
                delay.setText(method + " COMPLETE");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Note: All favourites have now been cleared.", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }.start();
    }
}