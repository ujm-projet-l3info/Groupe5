package com.example.neo.group5_gps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    public static Button button_connexion;
    public static Button button_inscrire;
    public static int  etat,invisible;
    public static Double longitude,latitude;
    public static String pseudo;
    public static ArrayList<HashMap<String, String>> usersList = null;


    public static void mainActivity_update(String pseudo, Double latitude, Double longitude, int etat, int invisible) {
        MainActivity.pseudo = pseudo;
        MainActivity.latitude = latitude;
        MainActivity.longitude = longitude;
        MainActivity.etat = etat;
        MainActivity.invisible = invisible;

    }
    public static void mainActivity_update(ArrayList<HashMap<String, String>> usersList ) {
        MainActivity.usersList=usersList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // pseudo="";
        //etat=0;
        if(!isNetworkAvailable()){
            //Create an alertdialog
            AlertDialog.Builder Checkbuilder=new  AlertDialog.Builder(MainActivity.this);
            Checkbuilder.setIcon(R.drawable.error);
            Checkbuilder.setTitle("Error!");
            Checkbuilder.setMessage("Check Your Internet Connection.");
            //Builder Retry Button

            Checkbuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Restart The Activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);

                }
            });

            Checkbuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }) ;

            AlertDialog alert=Checkbuilder.create();
            alert.show();

        }




        onClickButtonListener_connexion();
        onClickButtonListener_Inscrire();

    }

    /**************
     * START method Button Connection  Listener
     ***********/

    public void onClickButtonListener_connexion() {
        button_connexion = (Button) findViewById(R.id.connexion);
        button_connexion.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View V) {
                        Intent intent = new Intent("com.example.neo.group5_gps.ConnexionActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    /****************FIN method Button Connection Listener *************/

    /**************
     * START method Button Inscription  Listener
     ***********/

    public void onClickButtonListener_Inscrire() {
        button_inscrire = (Button) findViewById(R.id.inscrire_btn_main);
        button_inscrire.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View V) {
                        Intent intent = new Intent("com.example.neo.group5_gps.InscriptionActivity");
                        startActivity(intent);
                    }
                }
        );
    }
    /****************FIN method Button Connection Listener *************/
    /**************** method test INTERNET CONNSECTION *************/

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo !=null;
    }


    public void quitterMain (View v){

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        int pid = android.os.Process.myPid();//=====> use this if you want to kill your activity. But its not a good one to do.
        android.os.Process.killProcess(pid);
        System.exit(1);
    }



}