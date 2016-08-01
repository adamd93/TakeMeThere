package com.example.adam.takemethere.Logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adam.takemethere.R;
import com.example.adam.takemethere.restaurant;
import com.example.adam.takemethere.drive;

public class MainScreen extends AppCompatActivity {

  //  private static SeekBar seek_bar;
    //private static TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }


    public void onClick(View view) {
        Intent newActivity = null;
        switch (view.getId()) {
            case R.id.btnShowLocation:
                Intent intent = new Intent(getBaseContext(), restaurant.class);
                startActivity(intent);
                break;
            case R.id.Drive:
                Intent intent1 = new Intent(getBaseContext(), drive.class);
                startActivity(intent1);
                break;


        }

        if (newActivity != null) startActivity(newActivity);
    }



}


