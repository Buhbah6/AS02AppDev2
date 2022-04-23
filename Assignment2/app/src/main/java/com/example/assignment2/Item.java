package com.example.assignment2;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Anthony Nadeau - 2058983
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

public class Item {
    private String name;
    private String description;
    private double price;
    private String imageFile;

    public Item(String name, String description, double price, String imageFile) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return String.format("%s\nPrice: $%.2f\n\n", name, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

}
