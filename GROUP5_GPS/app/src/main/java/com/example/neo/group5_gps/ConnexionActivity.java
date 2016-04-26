package com.example.neo.group5_gps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ConnexionActivity extends AppCompatActivity {
    private static EditText nickname = null;
    private static EditText password = null;
    GPSTracker gps;
    String login_name, login_pass, latitude, longitude;

    public static EditText getNickname() {
        return nickname;
    }

    public static void setNickname(EditText nickname) {
        ConnexionActivity.nickname = nickname;
    }

    public static EditText getPassword() {
        return password;
    }

    public static void setPassword(EditText password) {
        ConnexionActivity.password = password;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        setNickname((EditText) findViewById(R.id.pseudo_cnct));
        setPassword((EditText) findViewById(R.id.mdp_cnct));

    }


    public void userLogin(View view) {
        gps = new GPSTracker(ConnexionActivity.this);
        if(gps.canGetLocation()) {
            latitude = Double.toString(gps.getLatitude());
            longitude = Double.toString(gps.getLongitude());
            //System.out.println("$$$$ Longitude : "+longitude+" | Latitude : "+latitude+ " $$$$");
        }
        login_name = getNickname().getText().toString();
        login_pass = getPassword().getText().toString();
                        System.out.println("**** Longitude : "+longitude+" | Latitude : "+latitude+ " !!****");
        Toast.makeText(getApplicationContext(),"Your Location is -\nLat: " + latitude + "\nLong: "
                        + longitude, Toast.LENGTH_LONG).show();
        String method = "login";
        //System.out.println("**** method : "+method+" !!****");
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, login_name, login_pass, latitude,longitude );
    }

}