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

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

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

        // uses the database to get all items being processed in the transaction
        db = new DBHelper(getApplicationContext());
        transaction = db.getAllItems("FAVOURITE_ITEMS");

        name = findViewById(R.id.nameEditText);
        address = findViewById(R.id.addressEditText);
        postalCode = findViewById(R.id.postalCodeEditText);
        receipt = findViewById(R.id.receiptTextView);

        // creates the receipt that appears on the checkout page
        String receiptString = "All items in transaction:\n";
        double totalPrice = 0;
        for (Item item : transaction) {
            receiptString += item;
            totalPrice += item.getPrice();
        }
        receiptString += String.format("Total Cost: $%.2f", totalPrice);
        receipt.setText(receiptString);

        nextButton = findViewById(R.id.nextButton);

        // Checks that all fields have been filled out, and starts the next step in the checkout
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

    /**
     * Adds the back button to the program
     * @param menu the menu containing containing the option
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    /**
     * Adds the functionality of the back button
     * @param item the back button
     * @return the super method
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}