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

    public RecyclerAdapter(String tableName, Context context) {
        db = new DBHelper(context);
        this.objects = db.getAllItems(tableName);
        this.context = context;
    }

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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = objects.get(position);
        holder.image.setImageDrawable(getResource(item.getImageFile()));
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText("$" + item.getPrice());
        if (db.getItem("FAVOURITE_ITEMS", item.getName()) != null) {
            holder.clickNum = 1;
            holder.favouriteButton.setImageDrawable(getResource("@android:drawable/btn_star_big_on"));
        }
        else
            holder.clickNum = 0;
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

    public Drawable getResource(String name) {
        int imageResource = context.getResources().getIdentifier(name, null, context.getPackageName());
        return context.getResources().getDrawable(imageResource);
    }


}
