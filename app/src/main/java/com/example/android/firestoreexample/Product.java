package com.example.android.firestoreexample;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Product implements Serializable {

    @Exclude String id;

    private String name, brand, desc;
    private int qty;
    private double price;

    public Product() {
    }

    public Product(String name, String brand, String desc, int qty, double price) {
        this.name = name;
        this.brand = brand;
        this.desc = desc;
        this.qty = qty;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDesc() {
        return desc;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }
}
