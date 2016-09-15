package com.example.adam.takemethere.Services;

import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.adam.takemethere.R;

import java.text.DecimalFormat;

/**
 * Created by Adam on 15/09/2016.
 */
// TODO nowhere near completed //
public class calcDisatance {
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
                double prog = progress;
                // Toast.makeText(getApplicationContext(), prog+ "selected distance in kms", Toast.LENGTH_LONG).show();

                globalVariable.setuDistance(prog);




            }
        });
    }
}
