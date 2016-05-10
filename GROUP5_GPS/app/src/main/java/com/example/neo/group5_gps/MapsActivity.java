package com.example.neo.group5_gps;
/*
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
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String login_name = MainActivity.pseudo;
    private GoogleMap mMap;
    public static ArrayList<HashMap<String, String>> usersList = MainActivity.usersList;
    ArrayList<LatLng> markerPoints;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //mMap = mapFragment.getMap();

        mapFragment.getMapAsync(this);
        System.out.println("$$$$$$$$$$$$$$$$$$$\n" + usersList.toString() + "\n$$$$$$$$$$$$\n$$$$$$$$$\n");
        //mMap.setMyLocationEnabled(true);





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
  /*  @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        int i=0;
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setMyLocationEnabled(true);

        //setListenersToGoogleMap();
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
          LatLng moi = new LatLng(MainActivity.latitude, MainActivity.longitude);

        for (i=0;i<usersList.size();i++){
            String titre= usersList.get(i).get("pseudo");
            Double longitude=Double.parseDouble(usersList.get(i).get("longitude"));
            Double latitude=Double.parseDouble(usersList.get(i).get("latitude"));
            LatLng pos = new LatLng(longitude,latitude);

            mMap.addMarker(new MarkerOptions().position(pos).title(titre));
        }
        mMap.addMarker(new MarkerOptions().position(moi).title("Moi : " + MainActivity.pseudo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moi,18));
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
        Intent intent= new Intent("com.example.neo.group5_gps.TchatcheActivity");
        startActivity(intent);
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
*/


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class  MapsActivity extends FragmentActivity {
    String login_name = MainActivity.pseudo;
    private GoogleMap mMap2;
    public static ArrayList<HashMap<String, String>> usersList = MainActivity.usersList;
    float[] colours = { BitmapDescriptorFactory.HUE_BLUE ,BitmapDescriptorFactory.HUE_RED,BitmapDescriptorFactory.HUE_ROSE,BitmapDescriptorFactory.HUE_CYAN,BitmapDescriptorFactory.HUE_GREEN,BitmapDescriptorFactory.HUE_MAGENTA,BitmapDescriptorFactory.HUE_ORANGE,BitmapDescriptorFactory.HUE_YELLOW,BitmapDescriptorFactory.HUE_VIOLET/* etc */ };

    GoogleMap map;
    ArrayList<LatLng> markerPoints;
    TextView tvDistanceDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        tvDistanceDuration = (TextView) findViewById(R.id.tv_distance_time);

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        // Getting reference to SupportMapFragment of the activity_main
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting Map for the SupportMapFragment
        map = fm.getMap();
        map.setBuildingsEnabled(true);
        map.setIndoorEnabled(true);
        //map.setMyLocationEnabled(true);
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setAllGesturesEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        LatLng moi = new LatLng(MainActivity.latitude, MainActivity.longitude);
        int i=0;
        for (i=0;i<usersList.size();i++){
            String titre= usersList.get(i).get("pseudo");
            Double longitude=Double.parseDouble(usersList.get(i).get("longitude"));
            Double latitude=Double.parseDouble(usersList.get(i).get("latitude"));
            LatLng pos = new LatLng(longitude,latitude);

            map.addMarker(new MarkerOptions().position(pos).title(titre).icon(BitmapDescriptorFactory.defaultMarker(colours[new Random().nextInt(colours.length)])));
        }
        map.addMarker(new MarkerOptions().position(moi).title("Moi : " + MainActivity.pseudo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(moi,18));
        // Enable MyLocation Button in the Map
        //map.setMyLocationEnabled(true);

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
        map.setMyLocationEnabled(true);


        //map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);// Setting onclick event listener for the map
       map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
        //map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override

            public void onMapClick(LatLng point) {
              //public boolean onMarkerClick(this){
                int i=0;
                // Already two locations
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    map.clear();
                }
                /********************************************************************************/

                // Add a marker in Sydney and move the camera
                LatLng moi = new LatLng(MainActivity.latitude, MainActivity.longitude);

                for (i=0;i<usersList.size();i++){
                    String titre= usersList.get(i).get("pseudo");
                    Double longitude=Double.parseDouble(usersList.get(i).get("longitude"));
                    Double latitude=Double.parseDouble(usersList.get(i).get("latitude"));
                    LatLng pos = new LatLng(longitude,latitude);
                    map.addMarker(new MarkerOptions().position(pos).title(titre).icon(BitmapDescriptorFactory.defaultMarker(colours[new Random().nextInt(colours.length)])));
                }
                map.addMarker(new MarkerOptions().position(moi).title("Moi : " + MainActivity.pseudo).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                //map.moveCamera(CameraUpdateFactory.newLatLngZoom(moi,12));




                /**********************************************************************************/
                // Adding new item to the ArrayList

                markerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /**
                 * For the start location, the color of marker is GREEN and
                 * for the end location, the color of marker is RED.
                 */
                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                }

                // Add new marker to the Google Map Android API V2
                map.addMarker(options);

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    //map.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);


                    LatLng origin = markerPoints.get(0);
                    LatLng dest = markerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }
            }
        });
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = (String) point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = (String) point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            tvDistanceDuration.setText("Distance:" + distance + ", Duration:" + duration);

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

*/

    public void tchatche (View view){
    Intent intent= new Intent("com.example.neo.group5_gps.TchatcheActivity");
    startActivity(intent);
    //Toast.makeText(MapsActivity.this, usersList.toString(), Toast.LENGTH_LONG).show();
    }
    public void showFrinds (View view){
        //Intent intent= new Intent("com.example.neo.group5_gps.FriendsList");
        String method ="getupdate";
        ParsorJasonTask parsorJasonTask = new ParsorJasonTask(this);
        parsorJasonTask.execute(method, login_name);
        //startActivity(intent);
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
