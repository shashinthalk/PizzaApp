package com.example.pizzaapp;

public class ProductClass {

    private int id;
    private String title,shortdesc ,sts_now;
    private double rating;
    private double price;
    private String image;
    private String status;




    public ProductClass(int id, String title, String shortdesc, double rating, double price, String image, String status, String sts_now) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.status = status;
    }

    public String getSts_now() {
        return sts_now;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}

