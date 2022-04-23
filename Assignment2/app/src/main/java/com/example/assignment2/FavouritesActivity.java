package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class FavouritesActivity extends AppCompatActivity {
    private Button startCheckoutButton;
    private RecyclerView recyclerView;
    private DBHelper db;
    private final String table = "FAVOURITE_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        // uses the database to verify that there are actually items within it
        db = new DBHelper(getApplicationContext());
        recyclerView = findViewById(R.id.favouritesRecyclerView);

        setAdapter();
        startCheckoutButton = findViewById(R.id.checkoutButton);
        startCheckoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.getAnyItem(table) != null) { // makes sure the table isn't empty
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity1.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please favourite some items before proceeding to checkout.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Sends the favourite table name to the recyclerview and sets the adapter to the custom adapter
     */
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(table, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}