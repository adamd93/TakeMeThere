package com.example.adam.takemethere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.adam.takemethere.R;

/**
 * Created by Adam on 11/02/2016.
 */

public class restaurant extends AppCompatActivity{
    private static SeekBar seek_bar;
    private static TextView text_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);
         seekbarr();

    }
    public void seekbarr() {
        seek_bar = (SeekBar) findViewById(R.id.DistanceBar);
        text_view = (TextView) findViewById(R.id.Miles);
        text_view.setText("Km " + seek_bar.getProgress());

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress_value;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                text_view.setText("km" + progress_value);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                progress_value = progress;
                text_view.setText(progress + " Km");


            }
        });
    }
}
