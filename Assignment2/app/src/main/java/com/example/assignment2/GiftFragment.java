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

public class GiftFragment extends Fragment {
    private DBHelper db;
    private RecyclerView recyclerView;
    private Button guideButton;
    private final String table = "GIFTS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        recyclerView = view.findViewById(R.id.giftRecyclerView);
        guideButton = view.findViewById(R.id.giftGuideButton);

        // Checks that the gifts table isn't empty, and if it is, it populates the table
        db = new DBHelper(getContext());
        if (db.getItem(table, "Headphones") == null) {
            populateGifts();
        }
        setAdapter();

        // Sends the user to a gift guide (external application implementation)
        guideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.buzzfeed.com/giftguide";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;
    }

    /**
     * Passes the Gifts table to the recyclerview and sets the adapter using the custom adapter
     */
    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(table, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    /**
     * Adds the gifts to the database
     */
    public void populateGifts() {
        db.addItem(table, new Item("Headphones", "Astro A50 wireless headphones for PC.",
                339.99, "@drawable/gift1"));
        db.addItem(table, new Item("Chocolate", "Milk Chocolate in a fancy box.",
                8.99, "@drawable/gift2"));
        db.addItem(table, new Item("Promise Ring", "Ring to represent a promise to someone.",
                20.99, "@drawable/gift3"));
        db.addItem(table, new Item("Friendship Bracelet", "Bracelet with a magnet. Best used in a pair.",
                42.99, "@drawable/gift4"));
        db.addItem(table, new Item("Warm Light", "Light that can be turned on or off at a distance.",
                11.99, "@drawable/gift5"));
        db.addItem(table, new Item("Framed Poem", "A framed poem about love and friendship.",
                4.99, "@drawable/gift6"));
        db.addItem(table, new Item("$50 Amazon Gift Card", "Card with total value of $50 for Amazon.ca.",
                49.99, "@drawable/gift7"));
        db.addItem(table, new Item("ASUS ROG Strix 3080ti", "High-End Gaming Graphics Card.",
                2799.99, "@drawable/gift8"));
    }
}