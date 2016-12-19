package com.example.adam.takemethere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.adam.takemethere.Services.Services;

/**
 * Created by Adam on 03/12/2016.
 */
public class settings extends AppCompatActivity{
    private static TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        final Services globalVariable = (Services) getApplicationContext();
        RadioButton rb;
        RadioButton rb1;
        rb = (RadioButton) findViewById(R.id.radioButton3);
        rb1 = (RadioButton) findViewById(R.id.radioButton4);
        int options = globalVariable.getOption();
        if (options==1){
            rb.setChecked(true);
        }
        else if(options==3) {
            rb1.setChecked(true);
        }
        else{
            rb.setChecked(true);
        }

    }
    public void onClick(View view){
        final Services globalVariable = (Services) getApplicationContext();
        RadioButton rb;
        rb = (RadioButton) findViewById(R.id.radioButton3);
        if(rb.isChecked()==true){
            globalVariable.setOption(3);
        }
        else{
            globalVariable.setOption(1);
        }

    }
    public void changeMeasurment(View view){
        text_view = (TextView) findViewById(R.id.switch1);
        String text = text_view.toString();
        if(text=="Miles"){
            text_view.setText("Kilometers");
        }
        else{
            text_view.setText("Miles");
        }


    }


}
