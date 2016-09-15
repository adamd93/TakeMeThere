package com.example.adam.takemethere.Services;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.adam.takemethere.drive;

import java.util.Random;

/**
 * Created by Adam on 15/09/2016.
 */
public class DriveLocation extends AppCompatActivity {
    String place;
    public void randomPlace(){

        final Services globalVariable = (Services) getApplicationContext();
        double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double distanceInMeters=999999999; //=99999 in kms
        double uDistance = globalVariable.getUdistance();

        Random rand = new Random();
        int n = rand.nextInt(13);
        // really need to make this into a json or API // SERIOUSLY!! // made into own service should do //
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
        drive cls2= new drive();
        double now = cls2.calcDistance(latitude,longitude,Rlatitude,Rlongitude); //should work?
        //distanceInMeters = calcDistance(latitude,longitude,Rlatitude,Rlongitude); //now actually kms
        if(distanceInMeters > uDistance){
            randomPlace();
        }
        else{
            Toast.makeText(getApplicationContext(), distanceInMeters + "distance appart", Toast.LENGTH_LONG).show();
        }

        //}


    }
}
