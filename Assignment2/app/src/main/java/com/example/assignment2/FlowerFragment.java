package com.example.assignment2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class FlowerFragment extends Fragment {
    private DBHelper db;
    private RecyclerView recyclerView;
    private Button guideButton;
    private final String table = "FLOWERS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_flower, container, false);
        recyclerView = view.findViewById(R.id.flowerRecyclerView);
        guideButton = view.findViewById(R.id.flowerGuideButton);

        // Checks to see if the flower table is already populated, and if it isn't, it populates it
        db = new DBHelper(getContext());
        if (db.getItem(table, "Tulip") == null) {
            populateFlowers();
        }
        setAdapter();

        // Sends the user to a flower gift guide (external application implementation)
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://flowermag.com/present-perfect-the-flower-2018-gift-guide/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;
    }

    /**
     * Passes the Flower table to the recyclerview and sets the adapter using the custom adapter
     */
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(table, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Adds the flowers to the database
     */
    public void populateFlowers() {
        db.addItem(table, new Item("Tulip", "Red and Yellow flower.",
                5.99, "@drawable/flower1"));
        db.addItem(table, new Item("Rose", "Red flower.",
                6.99, "@drawable/flower2"));
        db.addItem(table, new Item("Blue Anemone", "Blue flower.",
                5.99, "@drawable/flower3"));
        db.addItem(table, new Item("Peony", "Pink flower.",
                3.99, "@drawable/flower4"));
        db.addItem(table, new Item("Lilac", "Purple flower.",
                3.99, "@drawable/flower5"));
        db.addItem(table, new Item("Dahlia", "Reddish-Pink flower.",
                9.99, "@drawable/flower6"));
        db.addItem(table, new Item("Hydrangea", "Blue and Purple flower.",
                11.99, "@drawable/flower7"));
        db.addItem(table, new Item("White Orchid", "White flower.",
                7.99, "@drawable/flower8"));
    }
}