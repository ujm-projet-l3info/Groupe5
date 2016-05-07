package com.example.neo.group5_gps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String login_name = MainActivity.pseudo;
    private GoogleMap mMap;
    public static ArrayList<HashMap<String, String>> usersList = MainActivity.usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //usersList=MainActivity.usersList;

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        System.out.println("$$$$$$$$$$$$$$$$$$$\n"+usersList.toString()+"\n$$$$$$$$$$$$\n$$$$$$$$$\n");

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
        int i=0;
        // Add a marker in Sydney and move the camera
          LatLng sydney = new LatLng(MainActivity.latitude, MainActivity.longitude);

        for (i=0;i<usersList.size();i++){
            String titre= usersList.get(i).get("pseudo");
            Double longitude=Double.parseDouble(usersList.get(i).get("longitude"));
            Double latitude=Double.parseDouble(usersList.get(i).get("latitude"));
            LatLng pos = new LatLng(longitude,latitude);

            mMap.addMarker(new MarkerOptions().position(pos).title(titre));
        }
        mMap.addMarker(new MarkerOptions().position(sydney).title("Moi : " + MainActivity.pseudo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.setMyLocationEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

    }
    public void showFrinds (View view){
        //String method ="getJeson";
        //ParsorJasonTask parsorJasonTask = new ParsorJasonTask(this);
        //parsorJasonTask.execute(method, login_name);


        //Toast.makeText(MapsActivity.this, usersList.toString(), Toast.LENGTH_LONG).show();
    }

    public void quitter (View view){
        String method ="exit";
        ParsorJasonTask parsorJasonTask = new ParsorJasonTask(this);
        parsorJasonTask.execute(method, login_name);


    }
    public void goToP2P (View view){
        Intent intent = new Intent("com.example.neo.group5_gps.WiFierviceDiscoveryAcStivity");
        startActivity(intent);


    }
}
