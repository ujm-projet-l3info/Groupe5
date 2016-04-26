package com.example.neo.group5_gps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class InscriptionActivity extends AppCompatActivity {
    public static EditText nickname = null;
    public static EditText password = null , password2=null;
    GPSTracker gps;
    String login_name, login_pass,login_pass2, latitude, longitude;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        nickname = (EditText)findViewById(R.id.pseudo);
        password = (EditText)findViewById(R.id.mdp);
        password2 = (EditText)findViewById(R.id.reconfirmation);
    }

    public void userRegister(View view) {
        login_name = nickname.getText().toString();
        login_pass = password.getText().toString();
        login_pass2 = password2.getText().toString();
     /*   if(login_pass.equals(login_pass2)) {

        gps = new GPSTracker(InscriptionActivity.this);
            if (gps.canGetLocation()) {
                latitude = Double.toString(gps.getLatitude());
                longitude = Double.toString(gps.getLongitude());
                //System.out.println("$$$$ Longitude : "+longitude+" | Latitude : "+latitude+ " $$$$");
            }
            String method = "register";

            //System.out.println("**** Longitude : " + longitude + " | Latitude : " + latitude + " !!****");
            Toast.makeText(getApplicationContext(), "Your Location is -\nLat: " + latitude + "\nLong: "
                    + longitude, Toast.LENGTH_LONG).show();
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, login_name, login_pass, latitude,longitude );
        }else{
            Toast.makeText(getApplicationContext(), "ERR : Votre mots passe ne sont pas IDENTEQUE !! ", Toast.LENGTH_LONG).show();
        }*/
    }
}
