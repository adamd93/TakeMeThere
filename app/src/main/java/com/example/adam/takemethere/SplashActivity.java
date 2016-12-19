package com.example.adam.takemethere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.adam.takemethere.Logic.MainScreen;

/**
 * Created by Adam on 19/12/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        finish();
    }
}
