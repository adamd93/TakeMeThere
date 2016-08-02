package com.example.adam.takemethere;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.takemethere.Logic.MainScreen;
import com.example.adam.takemethere.R;
import com.example.adam.takemethere.Services.Services;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Adam on 11/02/2016.
 */

public class restaurant extends AppCompatActivity{
    private static SeekBar seek_bar;
    private static TextView text_view;
    Button btnShowLocation;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Services globalVariable = (Services) getApplicationContext();
        globalVariable.setLongitude(0);
        globalVariable.setLatitude(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
        seekbarr();
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // show location button click event
/*        btnShowLocation.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View arg0) {


                // create class object
                gps = new GPSTracker(drive.this);

            // check if GPS enabled
                if(gps.canGetLocation()){
                    final Services globalVariable = (Services) getApplicationContext();

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    globalVariable.setLongitude(longitude);
                    globalVariable.setLatitude(latitude);


                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                Toast.makeText(getApplicationContext(),"Gathering GPS Data...", Toast.LENGTH_SHORT).show();

                   }




        });*/


    }

    public void onClickNext(View view) {
        Intent newActivity = null;
        final Services globalVariable = (Services) getApplicationContext();
        gps = new GPSTracker(restaurant.this);
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();



        switch (view.getId()) {
            case R.id.showMap:


                Intent intent = new Intent(getBaseContext(), MapsActivityDrive.class);
                startActivity(intent);
                break;

            case R.id.btnShowLocation:
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    globalVariable.setLongitude(longitude);
                    globalVariable.setLatitude(latitude);


                    // \n is for new line
                    Toast.makeText(getApplicationContext(),"Gathering GPS Data...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                // PLaces to be broken into objects /
               /* altamount gardens: 52.735844 , -6.720746
                duckets Grove:52.857281 , -6.812316
                Rathwood: 52.796104 , -6.659993
                Oak Park: 52.8636388 , -6.8947948*/
                // getting the random place from the travel distance selected //
                randomPlace();





                // Starting the map activity //

                break;


        }

        if (newActivity != null) startActivity(newActivity);
    }
    public void randomPlace(){

        final Services globalVariable = (Services) getApplicationContext();
        double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double distanceInMeters=999999999; //=99999 in kms
        double uDistance = globalVariable.getUdistance();
        Toast.makeText(getApplicationContext(), uDistance+ "selected distance in kms", Toast.LENGTH_LONG).show();

        // while(distanceInMeters < uDistance ) {
        Random rand = new Random();
        Toast.makeText(getApplicationContext(), "It went in!", Toast.LENGTH_LONG).show();

        int n = rand.nextInt(6);
        switch (n) {
          // Weeping Thiager Carlow //
            case 0:
                globalVariable.setRLatitude(52.8365072);
                globalVariable.setRLongitude(-6.934135900000001);
                break;
            // Ho's chinese
            case 1:
                globalVariable.setRLatitude(52.8365072);
                globalVariable.setRLongitude(-6.934135900000001);
                break;
            // Apache Pizza //
            case 2:
                globalVariable.setRLatitude(52.8355179);
                globalVariable.setRLongitude(-6.929197799999997);
                break;
            // Milano's takeaway //
            case 3:
                globalVariable.setRLatitude(52.8365072);
                globalVariable.setRLongitude(-6.934135900000001);
                break;
            // the Jasmine //
            case 4:
                globalVariable.setRLatitude(52.8359518);
                globalVariable.setRLongitude(-6.9194409000000405);
                break;
            // Lennons Visual //
            case 5:
                globalVariable.setRLatitude(52.839714);
                globalVariable.setRLongitude(-6.928389299999935);
                break;
            //Teach dolmen //
            case 6:
                globalVariable.setRLatitude(52.8361513);
                globalVariable.setRLongitude(-6.928155299999958);
                break;
            // lemonGrass //
            case 7:
                globalVariable.setRLatitude(52.8350289);
                globalVariable.setRLongitude(-6.929552000000058);
                break;
            case 8:
                globalVariable.setRLatitude(52.8350289);
                globalVariable.setRLongitude(-6.929552000000058);
                break;

        }
        Toast.makeText(getApplicationContext(), "Random Location is - \nLat: " + Rlatitude + "\nLong: " + Rlongitude, Toast.LENGTH_LONG).show();

        // distanceInMeters = loc1.distanceTo(loc2);
        distanceInMeters = calcDistance(latitude,longitude,Rlatitude,Rlongitude); //now actually kms
        if(distanceInMeters > uDistance){
            randomPlace();
        }
        else{
            Toast.makeText(getApplicationContext(), distanceInMeters + "distance appart", Toast.LENGTH_LONG).show();
        }

        //}


    }
    public double calcDistance(double StartPlat, double StartPlong, double EndPlat, double EndPlong){
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartPlat;
        double lat2 = EndPlat;
        double lon1 = StartPlong;
        double lon2 = EndPlong;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);


        return Radius * c;
    }

    public void seekbarr() {
        seek_bar = (SeekBar) findViewById(R.id.DistanceBar);
        text_view = (TextView) findViewById(R.id.Miles);
        text_view.setText("Miles " + seek_bar.getProgress());
        final Services globalVariable = (Services) getApplicationContext();


        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                text_view.setText(progress_value + "km");
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                text_view.setText("Miles" + progress);
                // converting user selected distance to meters //
                double prog = progress;
                // Toast.makeText(getApplicationContext(), prog+ "selected distance in kms", Toast.LENGTH_LONG).show();

                globalVariable.setuDistance(prog);




            }
        });
    }
}
