package com.example.adam.takemethere.Services;

/**
 * Created by Adam on 28/11/2016.
 **/

public class GooglePlace {
    private String name;
    private String category;
    private String rating;
    private String open;
    private String county;
    private double latitude;
    private double longitude;

    public GooglePlace() {
        this.name = "";
        this.county = "";
        this.rating = "";
        this.open = "";
        this.setCategory("");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCounty(String county){
        this.county = county;
    }
    public String getCounty(){
        return county;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setOpenNow(String open) {
        this.open = open;
    }

    public String getOpenNow() {
        return open;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }
}