package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckoutActivity1 extends AppCompatActivity {
    private TextView name;
    private TextView address;
    private TextView postalCode;
    private TextView receipt;
    private Button nextButton;
    private DBHelper db;
    private ArrayList<Item> transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout1);
        db = new DBHelper(getApplicationContext());
        transaction = db.getAllItems("FAVOURITE_ITEMS");

        name = findViewById(R.id.nameEditText);
        address = findViewById(R.id.addressEditText);
        postalCode = findViewById(R.id.postalCodeEditText);
        receipt = findViewById(R.id.receiptTextView);

        String receiptString = "All items in transaction:\n";
        double totalPrice = 0;
        for (Item item : transaction) {
            receiptString += item;
            totalPrice += item.getPrice();
        }
        receiptString += String.format("Total Cost: $%.2f", totalPrice);
        receipt.setText(receiptString);

        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equals("") &&
                    !address.getText().toString().equals("") &&
                    !postalCode.getText().toString().equals("")) {
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity2.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please complete all fields to proceed.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}