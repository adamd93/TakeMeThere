package com.example.adam.takemethere;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.adam.takemethere.Services.Services;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.DecimalFormat;

public class MapsActivityDrive extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activity_drive);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void onClick(View view) {
        Intent newActivity = null;
        final Services globalVariable = (Services) getApplicationContext();
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        double Rlatitude=globalVariable.getRLatitude();
        double Rlongitude=globalVariable.getRLongitude();
        switch (view.getId()) {
            case R.id.btnDirections:
                // Creating directions for user to follow // //now a random of 3 places
                 Intent intentNav = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + latitude + "," + longitude + "&daddr=" + Rlatitude + ","+ Rlongitude));
                startActivity(intentNav);

                break;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Calling Application class (see application tag in AndroidManifest.xml)
        final Services globalVariable = (Services) getApplicationContext();
        double latitude = globalVariable.getLatitude();
        double longitude = globalVariable.getLongitude();
        //double latitude = 52.83098;
        //double longitude = -6.920013;
        double Rlatitude=globalVariable.getRLatitude();
        double Rlongitude=globalVariable.getRLongitude();
        // Add a marker to users location and move the camera
        LatLng userLocation = new LatLng(latitude, longitude);
        LatLng randomLocation = new LatLng(Rlatitude,Rlongitude);
        String value = getIntent().getExtras().getString("key");
        Button mButton=(Button)findViewById(R.id.place);
        mButton.setText(value);
        // users marker //
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

        // Random location marker //
        mMap.addMarker(new MarkerOptions().position(randomLocation).title(value));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));

        // Zoom in, animating the camera.
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)

                .build();
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(latitude,longitude))
                .add(new LatLng(Rlatitude, Rlongitude)) ; // North of the previous point, but at the same longitude


// Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MapsActivityDrive Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.adam.takemethere/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MapsActivityDrive Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.adam.takemethere/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    // determining distance between two points //


}
