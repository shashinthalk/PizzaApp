package com.example.pizzaapp;

public class HotProductsClass {

    private int id;
    private String title,shortdesc ,sts_now;
    private double rating;
    private double price;
    private String image;
    private String status;

    public HotProductsClass(int id, String title, String shortdesc, double rating, double price, String image, String status, String sts_now) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.sts_now = sts_now;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getSts_now() {
        return sts_now;
    }

    public void setSts_now(String sts_now) {
        this.sts_now = sts_now;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
