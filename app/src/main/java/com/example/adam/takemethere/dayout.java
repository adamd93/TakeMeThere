package com.example.adam.takemethere;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adam.takemethere.Services.GooglePlace;
import com.example.adam.takemethere.Services.Services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Adam on 11/12/2016.
 */
public class dayout extends AppCompatActivity{
    private static SeekBar seek_bar;
    private static TextView text_view;
    private static ListView list_view;
    ArrayList<GooglePlace> venuesList;
    Button btnShowLocation;
    String place;
    String server_response;
    double latitude = 52.839714;
    double longitude = -6.928389299999935;
    final String GOOGLE_KEY = "AIzaSyB-1fag6ZNgvgmIhFMQm6KAAUgA7mVY3gg";

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Services globalVariable = (Services) getApplicationContext();
       /* globalVariable.setLongitude(0);
        globalVariable.setLatitude(0);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayout);
        seekbarr();
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
    }
    public void settingsScreen (View view){
        Intent intent = new Intent(getBaseContext(), settings.class);
        startActivity(intent);

    }

    public void onClickNext(View view) {
        Intent newActivity = null;
        final Services globalVariable = (Services) getApplicationContext();
        gps = new GPSTracker(dayout.this);
       /* double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        /*double Rlatitude = globalVariable.getRLatitude();
        double Rlongitude = globalVariable.getRLongitude();*/

        switch (view.getId()) {
            case R.id.showMap:

                Intent intent = new Intent(getBaseContext(), MapsActivityDrive.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("key", place);
                intent.putExtras(mBundle);
                startActivity(intent);
                startActivity(intent);
                break;

            case R.id.btnShowLocation:
                if(gps.canGetLocation()){

                    /*latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    globalVariable.setLongitude(longitude);
                    globalVariable.setLatitude(latitude);*/

                    // \n is for new line
                    Toast.makeText(getApplicationContext(),"Generating random Restaurant" + latitude+longitude, Toast.LENGTH_SHORT).show();
                    randomPlace();

                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                // getting the random place from the travel distance selected //

                // Starting the map activity //
                break;
        }

        if (newActivity != null) startActivity(newActivity);
    }
    public void randomPlace(){
        final Services globalVariable = (Services) getApplicationContext();
        int uDistance = globalVariable.getUdistance();

        new googleplaces().execute();
    }
    public class googleplaces extends AsyncTask<View, Void, String> {
        final Services globalVariable = (Services) getApplicationContext();
        int uDistance = globalVariable.getUdistance();
        @Override
        protected String doInBackground(View... urls) {
            final Services globalVariable = (Services) getApplicationContext();
            /*double latitude = globalVariable.getLatitude();
            double longitude = globalVariable.getLongitude();*/
            //latitude = globalVariable.getLatitude();
            //longitude = globalVariable.getLongitude();

            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude + "&radius=" + uDistance + "&&type=park&sensor=true&key=" + GOOGLE_KEY);

                urlConnection = (HttpURLConnection) url.openConnection();

                int responseCode = urlConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            // start pulse animation here
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Response", "" + server_response);
            getPlaces(server_response);
        }
        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
        public void getPlaces(String result) {
            final Services globalVariable = (Services) getApplicationContext();
            double numOfOptions = globalVariable.getOption();
            if (result == null) {
                // we have an error to the call
                // stop pulse execution here
                System.out.println("error");
            } else {
                // parse Google places search result
                venuesList = (ArrayList<GooglePlace>) parseGoogleParse(result);

                List<String> listTitle = new ArrayList<String>();
                RadioButton rb;
                int numberOfRandoms = 0;
                // rb = (RadioButton) findViewById(R.id.radioButton);

                if(numOfOptions==1){
                    numberOfRandoms = 1;
                }
                else{
                    numberOfRandoms = 3;
                }
                LinearLayout l_layout = (LinearLayout) findViewById(R.id.linear_layout);
                l_layout.setOrientation(LinearLayout.VERTICAL);
                l_layout.removeAllViews();

                int items = venuesList.size();
                for(int i =0; i < numberOfRandoms ; i ++){
                    Random rand = new Random();
                    //int place = rand.nextInt(items);


                    final Button btn1 = new Button(dayout.this);
                    final int place =  rand.nextInt(items);
                    String Location = venuesList.get(place).getName();
                    double Rlatitude = venuesList.get(place).getLatitude();
                    double Rlongitude = venuesList.get(place).getLongitude();
                    double distance = calcDistance(latitude,longitude,Rlatitude,Rlongitude);
                    // double distance = Math.round(distance1);
                    String.format(Locale.CANADA,"%.2f", distance);
                    btn1.setText(Location + "\n" +distance+ "km away");
                    l_layout.addView(btn1);
                    // btn1.setTextColor(333333);
                    // btn1.setTextSize(12);
                    btn1.setBackgroundColor(000000);
                    btn1.setPadding(10,5,0,5);
                    //list_view = (ListView) findViewById(R.id.listView);
                    btn1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            // Perform action on click
                            Toast.makeText(getApplicationContext(),"" +venuesList.get(place).getName() + " " +venuesList.get(place).getCounty() , Toast.LENGTH_SHORT).show();
                            //set longitude and latitude here to open map screen with marker location
                        }
                    });
                }
                //stop pulse execution here
            }
        }
    }
    private static ArrayList<GooglePlace> parseGoogleParse(final String response) {

        ArrayList<GooglePlace> temp = new ArrayList<GooglePlace>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("results")) {

                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    GooglePlace poi = new GooglePlace();
                    if (jsonArray.getJSONObject(i).has("name")) {
                        // Storing details of each place in list
                        // Should optomize later to store only the random places choosen
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setCounty(jsonArray.getJSONObject(i).optString("vicinity"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));
                        poi.setLatitude(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optDouble("lat"));
                        poi.setLongitude(jsonArray.getJSONObject(i).getJSONObject("geometry").getJSONObject("location").optDouble("lng"));

                        if (jsonArray.getJSONObject(i).has("opening_hours")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").has("open_now")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("opening_hours").getString("open_now").equals("true")) {
                                    poi.setOpenNow("YES");
                                } else {
                                    poi.setOpenNow("NO");
                                }
                            }
                        } else {
                            poi.setOpenNow("Not Known");
                        }
                        if (jsonArray.getJSONObject(i).has("types")) {
                            JSONArray typesArray = jsonArray.getJSONObject(i).getJSONArray("types");

                            for (int j = 0; j < typesArray.length(); j++) {
                                poi.setCategory(typesArray.getString(j) + ", " + poi.getCategory());
                            }
                        }
                    }
                    temp.add(poi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<GooglePlace>();
        }
        return temp;

    }
    public void seekbarr() {
        seek_bar = (SeekBar) findViewById(R.id.DistanceBar);
        text_view = (TextView) findViewById(R.id.Miles);
        text_view.setText(seek_bar.getProgress()+ " Km");
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
                int prog = progress * 1000;
                // Toast.makeText(getApplicationContext(), prog+ "selected distance in kms", Toast.LENGTH_LONG).show();

                globalVariable.setuDistance(prog);
            }
        });
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
}
