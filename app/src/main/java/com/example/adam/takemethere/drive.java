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
import com.example.adam.takemethere.Services.DriveLocation;
import com.example.adam.takemethere.Services.Services;
import com.google.android.gms.maps.model.LatLng;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Adam on 11/02/2016.
 */

public class drive extends AppCompatActivity{
    private static SeekBar seek_bar;
    private static TextView text_view;
    Button btnShowLocation;
    String place;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Services globalVariable = (Services) getApplicationContext();
        globalVariable.setLongitude(0);
        globalVariable.setLatitude(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive);
        seekbarr();
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // The fuck is this shite? not even used?
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
        gps = new GPSTracker(drive.this);
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();



        switch (view.getId()) {
            case R.id.showMap:


                Intent intent = new Intent(getBaseContext(), MapsActivityDrive.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("key", place);
                intent.putExtras(mBundle);
                startActivity(intent);
                break;

            case R.id.btnShowLocation:
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    globalVariable.setLongitude(longitude);
                    globalVariable.setLatitude(latitude);


                    // \n is for new line
                    Toast.makeText(getApplicationContext(),"Generating Random Place...", Toast.LENGTH_SHORT).show();

                    //  Toast.makeText(getApplicationContext(),"Gathering GPS Data...", Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
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
               // randomPlace();
                DriveLocation cls2= new DriveLocation();
                cls2.randomPlace(); // should call random place for drive /

                // Starting the map activity //

                break;
        }

        if (newActivity != null) startActivity(newActivity);
    }
    // whole method should become redundant, use DriveLocation instead //
    public void randomPlace(){

        final Services globalVariable = (Services) getApplicationContext();
        double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double distanceInMeters=999999999; //=99999 in kms
        double uDistance = globalVariable.getUdistance();

        //Toast.makeText(getApplicationContext(), uDistance+ "selected distance in kms", Toast.LENGTH_LONG).show();

       // while(distanceInMeters < uDistance ) {
            Random rand = new Random();
            //Toast.makeText(getApplicationContext(), "It went in!", Toast.LENGTH_LONG).show();

            int n = rand.nextInt(13);
        // really need to make this into a json or API // SERIOUSLY!! //
            switch (n) {
                // Oak Park //
                case 0:
                    globalVariable.setRLatitude(52.8636388);
                    globalVariable.setRLongitude(-6.8947948);
                    place="oak Park";
                    break;
                // altamount gardens //
                case 1:
                    globalVariable.setRLatitude(52.735844);
                    globalVariable.setRLongitude(-6.720746);
                    place = "Altamount Gardens";
                    break;
                // Duckets Grove //
                case 2:
                    globalVariable.setRLatitude(52.857218);
                    globalVariable.setRLongitude(-6.812316);
                    place="Duckets Grove";
                    break;
                // Rathwood //
                case 3:
                    globalVariable.setRLatitude(52.796104);
                    globalVariable.setRLongitude(-6.659993);
                    place="Rathwood";
                    break;
                // Gilberts Orchard //
                case 4:
                    place="Gilberts Orchard";
                    globalVariable.setRLatitude(52.732383);
                    globalVariable.setRLongitude(-6.648521399999936);
                   // globalVariable.setRlocation("sd");
                    break;
                case 6:
                    place="Visual Arts Center Carlow";
                    globalVariable.setRLatitude(52.839714);
                    globalVariable.setRLongitude(-6.928389299999935);
                    break;
                case 7:
                    place="Kilkenny Castle";
                    globalVariable.setRLatitude(52.6504624);
                    globalVariable.setRLongitude(-7.249297899999988);
                    break;
                case 8:
                    place="Glendalough";
                    globalVariable.setRLatitude(53.01197999999999);
                    globalVariable.setRLongitude(-6.32983999999999);
                    break;
                case 9:
                    place="The Nine Stones, Mt.Leinster";
                    globalVariable.setRLatitude(52.6361111);
                    globalVariable.setRLongitude(-6.772222199999987);
                    break;
                case 10:
                    place = "Kilmainhan Gaol";
                    globalVariable.setRLatitude(53.34208719999999);
                    globalVariable.setRLongitude(-6.310002199999985);
                    break;
                case 11:
                    place = "Powerscout House & Gardens";
                    globalVariable.setRLatitude(53.184251);
                    globalVariable.setRLongitude(-6.186632700000018);
                    break;
                case 12:
                    place = "Pirates Cove Mini golf";
                    globalVariable.setRLatitude(52.6429983);
                    globalVariable.setRLongitude(-6.23281);
                    break;
                case 13:
                    place = "Castlecomer Adventure Park";
                    globalVariable.setRLatitude(52.8065903);
                    globalVariable.setRLongitude(-7.203715500000044);
                    break;
                case 14:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                // need new locations from here down //
                case 15:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 16:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 17:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 18:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 19:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 20:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 21:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 22:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 23:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 24:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 25:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 26:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 27:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 28:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 29:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 30:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 31:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 32:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;
                case 33:
                    place = "Smithwicks Experience";
                    globalVariable.setRLatitude(52.654305);
                    globalVariable.setRLongitude(-7.2544450000000325);
                    break;

                default:
                    Toast.makeText(getApplicationContext(), " Random Location not found, Please try again...", Toast.LENGTH_LONG).show();
            }
            //Toast.makeText(getApplicationContext(), "Random Location is - \nLat: " + Rlatitude + "\nLong: " + Rlongitude, Toast.LENGTH_LONG).show();

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
// need to make into its own service //
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
        text_view.setText(seek_bar.getProgress() + " Km");
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
                text_view.setText(progress_value + " Km");
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                 text_view.setText(progress + " Km");
                // converting user selected distance to meters //
                int prog = progress *100;
               // Toast.makeText(getApplicationContext(), prog+ "selected distance in kms", Toast.LENGTH_LONG).show();

                globalVariable.setuDistance(prog);




            }
        });
    }
}
