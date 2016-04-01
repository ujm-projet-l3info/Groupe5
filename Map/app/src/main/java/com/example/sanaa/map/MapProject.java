package com.example.sanaa.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;


public class MapProject extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SharedPreferences sharedPreferences;
   // Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_project);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
      //  btn = (Button)findViewById(R.id.returne);
      //  btn.setOnClickListener(new View.OnClickListener() {
      //      @Override
        //    public void onClick(View v) {
       // setContentView(R.layout.activity_map_project);}});
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quitter:
                this.finish();
            case R.id.voiramis:
                this.setContentView(R.layout.activity_main);
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
    public void onMapReady(GoogleMap googleMap) throws SecurityException {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(new LatLng(45.441435, 4.391127)).title("Mohammad"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);

        mMap.addMarker(new MarkerOptions().position(new LatLng(45.433310, 4.383333)).title("Sana"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(45.410545, 4.386314)).title("Ahmed"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(45.432067, 4.404804)).title("Latifa"));

    }
}