package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {
    private Fragment giftFragment;
    private Fragment flowerFragment;
    private Button giftButton;
    private Button flowerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        giftFragment = new GiftFragment();
        flowerFragment = new FlowerFragment();

        // Shows all the gifts
        giftButton = findViewById(R.id.giftButton);
        giftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(giftFragment);
            }
        });

        // Shows all the flowers
        flowerButton = findViewById(R.id.flowerButton);
        flowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(flowerFragment);
            }
        });
    }

    /**
     * Shows the menu with the favourites button
     * @param menu the menu containing the favourites button
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Makes the favourites button open the favourites activity
     * @param item the favourites button
     * @return the super method
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), FavouritesActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to set the fragment container content to the passed fragment
     * @param frag fragment being inflated int he fragment container
     */
    private void changeFragment(Fragment frag) {
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragMan.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, frag);
        fragmentTransaction.commit();
    }
}