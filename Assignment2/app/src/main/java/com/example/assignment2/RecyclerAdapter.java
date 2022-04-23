package com.example.assignment2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<Item> objects;
    private Context context;
    private DBHelper db;

    /**
     * Constructor takes the tableName and the context of the application
     * @param tableName the table being queried to display data
     * @param context the current context of the application upon instantiation
     */
    public RecyclerAdapter(String tableName, Context context) {
        db = new DBHelper(context);
        this.objects = db.getAllItems(tableName);
        this.context = context;
    }

    /**
     * Inner class that contains the view for each object being showed
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView description;
        private TextView price;
        private ImageView favouriteButton;
        private int clickNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            favouriteButton = itemView.findViewById(R.id.favouriteButton);
            favouriteButton.setClickable(true);
        }
    }

    // inflates the view inside the viewholder and returns it
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    // Defines the amount of objects to be displayed
    @Override
    public int getItemCount() {
        return objects.size();
    }

    //
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = objects.get(position); // stores the item to be used later

        // Setting the elements of the viewholder to contain the object contents
        holder.image.setImageDrawable(getResource(item.getImageFile()));
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText("$" + item.getPrice());

        // Sets the favourite button to active if the item is already favourited
        if (db.getItem("FAVOURITE_ITEMS", item.getName()) != null) {
            holder.clickNum = 1;
            holder.favouriteButton.setImageDrawable(getResource("@android:drawable/btn_star_big_on"));
        }
        else
            holder.clickNum = 0;

        // Makes the favourite button add or remove the object from the favourites table depending on its current status
        holder.favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.clickNum == 0) {
                    db.addItem("FAVOURITE_ITEMS", item);
                    holder.favouriteButton.setImageDrawable(getResource("@android:drawable/btn_star_big_on"));
                    holder.clickNum = 1;
                }
                else {
                    db.removeItem("FAVOURITE_ITEMS", item);
                    holder.favouriteButton.setImageDrawable(getResource("@android:drawable/btn_star_big_off"));
                    holder.clickNum = 0;
                }
            }
        });
    }

    /**
     * Gets the Drawable file from the passed name
     * @param name the name of the @drawable file
     * @return the file as a Drawable resource
     */
    public Drawable getResource(String name) {
        int imageResource = context.getResources().getIdentifier(name, null, context.getPackageName());
        return context.getResources().getDrawable(imageResource);
    }


}
