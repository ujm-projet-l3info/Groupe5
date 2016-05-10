package com.example.neo.group5_gps;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendsList extends AppCompatActivity {
    public static ArrayList<HashMap<String, String>> usersList = MainActivity.usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_friends);
        String[] columns = new String[] { "_id", "col1", "col2" };

        MatrixCursor matrixCursor= new MatrixCursor(columns);
        startManagingCursor(matrixCursor);
        matrixCursor.addRow(new Object[] {0,"pseudo","connécté depuis"});
        int i=0;
        for (i=0;i<usersList.size();i++){
            String pseudo= usersList.get(i).get("pseudo");
            String heure=usersList.get(i).get("heure");
            matrixCursor.addRow(new Object[] { i+1,pseudo,heure });
        }

        // on prendra les données des colonnes 1 et 2...
        String[] from = new String[] {"col1", "col2"};

        // ...pour les placer dans les TextView définis dans "row_item.xml"
                int[] to = new int[] { R.id.textViewCol1, R.id.textViewCol2};

        // création de l'objet SimpleCursorAdapter...
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor, from, to, 0);

        // ...qui va remplir l'objet ListView
                ListView lv = (ListView) findViewById(R.id.lv);
                lv.setAdapter(adapter);


    }
}
