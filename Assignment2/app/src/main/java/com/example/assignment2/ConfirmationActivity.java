package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class ConfirmationActivity extends AppCompatActivity {
    private DBHelper db;
    private TextView shipping;
    private TextView delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        String method = intent.getStringExtra("shipping");
        shipping = findViewById(R.id.shippingTextView);
        delay = findViewById(R.id.delayTextView);
        db = new DBHelper(getApplicationContext());
        db.removeAllItemsFromTable("FAVOURITE_ITEMS");

        shipping.setText("Method of Delivery: " + method);

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                delay.setText("Order will be ready in: " + millisUntilFinished / 1000 + " Seconds");
            }

            public void onFinish() {
                delay.setText(method + " COMPLETE");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Toast.makeText(getApplicationContext(), "Note: All favourites have now been cleared.", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }.start();
    }
}