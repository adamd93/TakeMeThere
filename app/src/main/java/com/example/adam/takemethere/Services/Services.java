package com.example.adam.takemethere.Services;

import android.app.Application;

import android.app.Application;

import com.example.adam.takemethere.Logic.MainScreen;

/**
 * Created by Adam on 11/02/2016.
 */
public class Services extends Application{

    //assigning variables for users location and random location //
    private double longitude;
    private double latitude;
    private double Rlongitude;
    private double Rlatitude;
    private String Rlocation;
    private double uDistance;

    public double getUdistance() {

        return uDistance;
    }

    public void setuDistance(double aName) {

        uDistance = aName;

    }
    public double getLongitude() {

        return longitude;
    }

    public void setLongitude(double aName) {

        longitude = aName;

    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double aEmail) {

        latitude = aEmail;
    }

    public double getRLongitude() {

        return Rlongitude;
    }

    public void setRLongitude(double aName) {

        Rlongitude = aName;

    }

    public double getRLatitude() {

        return Rlatitude;
    }

    public void setRLatitude(double aEmail) {

        Rlatitude = aEmail;
    }
    public String setRlocation() {

        return Rlocation;
    }

    public void getRlocation(String aEmail) {

        Rlocation = aEmail;
    }

}
