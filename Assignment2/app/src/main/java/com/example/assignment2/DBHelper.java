package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class DBHelper extends SQLiteOpenHelper {

    // creates a local database called items.db
    public DBHelper(@Nullable Context context) {
        super(context, "items.db", null, 1);
    }

    /**
     * Creates all the necessary tables in the database
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE FAVOURITE_ITEMS (NAME TEXT, DESCRIPTION TEXT, PRICE DOUBLE, IMAGEFILE TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE GIFTS (NAME TEXT, DESCRIPTION TEXT, PRICE DOUBLE, IMAGEFILE TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE FLOWERS (NAME TEXT, DESCRIPTION TEXT, PRICE DOUBLE, IMAGEFILE TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    // auto-generated method that was not needed in this project
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    /**
     * Adds an item to the database
     * @param tableName the name of the table the item needs to be added to
     * @param item the item being added into the db
     */
    public void addItem(String tableName, Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", item.getName());
        cv.put("DESCRIPTION", item.getDescription());
        cv.put("PRICE", item.getPrice());
        cv.put("IMAGEFILE", item.getImageFile());
        db.insert(tableName, null, cv);
        db.close();
    }

    /**
     * Gets all items from a given table in the database
     * @param tableName the table being queried
     * @return an arraylist of all the items in the table
     */
    public ArrayList<Item> getAllItems(String tableName) {
        ArrayList<Item> itemsList = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                Double price = cursor.getDouble(2);
                String imageFile = cursor.getString(3);
                itemsList.add(new Item(name, description, price, imageFile));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return itemsList;
    }

    /**
     * Retrieves a specific item from the given table using the name
     * @param tableName the table being queried
     * @param name the name of the item being searched for
     * @return the item retrieved from the db
     */
    public Item getItem(String tableName,String name) {
        String query = "SELECT * FROM " + tableName + " WHERE NAME = '" + name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String itemName = cursor.getString(0);
            String description = cursor.getString(1);
            Double price = cursor.getDouble(2);
            String imageFile = cursor.getString(3);
            return new Item(itemName, description, price, imageFile);
        }
        return null;
    }

    /**
     * Removes an item from the given table
     * @param tableName the table having an item removed from it
     * @param item the item being removed from the table
     */
    public void removeItem(String tableName, Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, "NAME = '" + item.getName() + "'", null);
        db.close();
    }

    /**
     * Gets the first item in the given table
     * @param tableName the table being queried
     * @return the first item in the given table
     */
    public Item getAnyItem(String tableName) {
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String itemName = cursor.getString(0);
            String description = cursor.getString(1);
            Double price = cursor.getDouble(2);
            String imageFile = cursor.getString(3);
            return new Item(itemName, description, price, imageFile);
        }
        return null;
    }

    /**
     * Removes all items in the given table
     * @param tableName the table being cleared
     */
    public void removeAllItemsFromTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }
}
