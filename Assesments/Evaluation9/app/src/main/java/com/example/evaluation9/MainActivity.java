package com.example.evaluation9;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener {
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //TODO: goto https://console.cloud.google.com/ to setup the google maps sdk for this project
        //TODO: use the https://developers.google.com/maps/documentation/android-sdk/config to setup the google maps sdk in this project

    }

    PolylineOptions options;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO: The retrieved trip points should be plotted on the Google Map
        ArrayList<LatLng> points = DataServices.getPath();

        // using “Polyline” shape https://developers.google.com/maps/documentation/android-sdk/polygon-tutorial
        /*
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .addAll(points));
                */

        options = new PolylineOptions();

        for (LatLng point : points) {
            options.getPoints().
                    add(point);
        }

        googleMap.addPolyline(options);

        googleMap.setOnPolylineClickListener(this);

        // The start and end points of the trip should be indicated with markers
        // https://developers.google.com/maps/documentation/android-sdk/map-with-marker

        LatLng start = new LatLng(points.get(0).latitude, points.get(0).longitude);
        LatLng end = new LatLng(points.get(points.size() - 1).latitude, points.get(points.size() - 1).longitude);
        googleMap.addMarker(new MarkerOptions()
                .position(start)
                .title("Start Position"));

        googleMap.addMarker(new MarkerOptions()
                .position(end)
                .title("End Position"));


        //Also map should be auto zoomed to include all the trip points in the map’s bounding box.
        //Check CameraUpdateFactory class check: https://developers.google.com/android/reference/com/google/android/gms/maps/CameraUpdateFactory

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng point : points) {
            builder.include(point);
        }

        LatLngBounds latLngBounds = builder.build();
        googleMap.setOnMapLoadedCallback(() -> googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50)));
    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }

    @Override
    public void onPolylineClick(@NonNull Polyline polyline) {

    }
}


