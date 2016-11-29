package com.example.adam.takemethere.Services;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.adam.takemethere.GPSTracker;

/**
 * Created by Adam on 28/11/2016.
 */
public class Places extends ListActivity {
    ArrayList<GooglePlace> venuesList;
    String server_response;
    GPSTracker gps;
    String userLocation;
    // the google key

    // ============== YOU SHOULD MAKE NEW KEYS ====================//
    final String GOOGLE_KEY = "AIzaSyB-1fag6ZNgvgmIhFMQm6KAAUgA7mVY3gg";

    // we will need to take the latitude and the logntitude from a certain point
    // this is the carlow
    /*final String latitude = "52.839714";
    final String longtitude = "-6.928389299999935";*/
    /*double latitude;
    double longitude;*/
    double latitude = 52.839714;
    double longitude = -6.928389299999935;
    //Places plc = new Places();


    ArrayAdapter<String> myAdapter;

    public class googlePlaces extends AsyncTask<View, Void, String> {

        @Override
        protected String doInBackground(View... urls) {
            /*final Services globalVariable = (Services) getApplicationContext();
            double latitude = globalVariable.getLatitude();
            double longitude = globalVariable.getLongitude();*/
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            System.out.println("FUCK!!!");
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude + "&radius=1000&type=restaurant&sensor=true&key=" + GOOGLE_KEY);
                //url = new URL("https://maps.googleapis.com/maps/api/place/search/json?location=" + userLocation + "&radius=1000&sensor=true&key=" + GOOGLE_KEY);

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
            // we can start a progress bar here
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
            if (result == null) {
                // we have an error to the call
                // we can also stop the progress bar
                System.out.println("error");
            } else {
                // all things went right
                // System.out.println(temp);
                // parse Google places search result
                venuesList = (ArrayList<GooglePlace>) parseGoogleParse(result);

                List<String> listTitle = new ArrayList<String>();

                for (int i = 0; i < venuesList.size(); i++) {
                    // make a list of the venus that are loaded in the list.
                    // show the name, the category and the city
                    listTitle.add(i, venuesList.get(i).getName() + "\nOpen Now: " + venuesList.get(i).getOpenNow() + "\n(" + venuesList.get(i).getCategory() + ")");
                }

                // set the results to the list
                // and show them in the xml
               // myAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.row_layout, R.id.listText, listTitle);
                setListAdapter(myAdapter);
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
                        poi.setName(jsonArray.getJSONObject(i).optString("name"));
                        poi.setRating(jsonArray.getJSONObject(i).optString("rating", " "));

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
}
