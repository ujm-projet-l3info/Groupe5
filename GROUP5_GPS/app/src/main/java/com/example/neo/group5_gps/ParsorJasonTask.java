package com.example.neo.group5_gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by neo on 07/05/16.
 */
public class ParsorJasonTask extends AsyncTask<String,Void ,String> {
    private static final String TAG_USERINFO = "users";
    private static final String TAG_ID = "ID";
    private static final String TAG_PSEUDO = "pseudo";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_HEURE = "heure";
    private ProgressDialog pd;
    ArrayList<HashMap<String, String>> usersList;
    Context ctx;

    ParsorJasonTask(Context ctx){
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(ctx, "Parsing Information",
                "Please wait while we are parsing Data...");
    }
    @Override
    protected String doInBackground(String... params) {
        String json_url ="http://109.29.198.199/all.php";
        String logout_url="http://109.29.198.199/logout.php";
        String method = params[0];
        if(method.equals("getJeson")){
            String login_name = params[1];
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response +=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                usersList = ParseJSON(response);
                return ("parser success");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(method.equals("exit")){
            String login_name = params[1];
            try {
                URL url = new URL(logout_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    response +=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return ("logout success");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if (result.equals("parser success")) {
            Toast.makeText(ctx, "jaison Success ", Toast.LENGTH_LONG).show();
            MainActivity.mainActivity_update(usersList);
            System.out.println("$$$$$$$$$$$$$$$$$$$\n"+usersList.toString()+"\n$$$$$$$$$$$$\n$$$$$$$$$\n");
            MainActivity.usersList=usersList;
            Intent intent = new Intent("com.example.neo.group5_gps.MapsActivity");
            ctx.startActivity(intent);

        }else{
            if(!(result.equals("parser success"))){
                Toast.makeText(ctx, "ERR :Parser failed to get DATA ", Toast.LENGTH_LONG).show();
            }
        }
        if (result.equals("logout success")) {
            Toast.makeText(ctx, "LOGOUT Success !!", Toast.LENGTH_LONG).show();
            System.exit(0);
        }else{
            if(result.equals("")){
                Toast.makeText(ctx, "ERR :Parser failed to Logout ", Toast.LENGTH_LONG).show();
            }
        }
    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> usersList = new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(json);

                // Getting JSON Array node
                JSONArray users = jsonObj.getJSONArray(TAG_USERINFO);


                // looping through All Students
                for (int i = 0; i < users.length(); i++) {
                    JSONObject c = users.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String pseudo = c.getString(TAG_PSEUDO);
                    String latitude = c.getString(TAG_LATITUDE);
                    String longitude = c.getString(TAG_LONGITUDE);
                    String heure = c.getString(TAG_HEURE);

                    // tmp hashmap for single student
                    HashMap<String, String> user = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    user.put(TAG_ID, id);
                    user.put(TAG_PSEUDO, pseudo);
                    user.put(TAG_LATITUDE,latitude );
                    user.put(TAG_LONGITUDE, longitude);
                    user.put(TAG_HEURE, heure);

                    // adding user to usersList
                    usersList.add(user);
                }
                return usersList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }

    }
 /****************
 * public class SingleKeyMultipleValueUsingList {

 * public static void main(String[] args) {

 * // create map to store

 * Map<String, List<String>> map = new HashMap<String, List<String>>();

 * // create list one and store values

 * List<String> valSetOne = new ArrayList<String>();

 * valSetOne.add("Apple");

 *  valSetOne.add("Aeroplane");

 * // create list two and store values

 * List<String> valSetTwo = new ArrayList<String>();

 * valSetTwo.add("Bat");

 * valSetTwo.add("Banana");

 * // create list three and store values

 * List<String> valSetThree = new ArrayList<String>();

 *  valSetThree.add("Cat");

 * valSetThree.add("Car");

 * // put values into map

 * map.put("A", valSetOne);

 * map.put("B", valSetTwo);

 map.put("C", valSetThree);

 // iterate and display values

 System.out.println("Fetching Keys and corresponding [Multiple] Values n");

 for (Map.Entry<String, List<String>> entry : map.entrySet()) {

 String key = entry.getKey();

 List<String> values = entry.getValue();

 System.out.println("Key = " + key);

 System.out.println("Values = " + values + "n");

 * }
 *
 * }
 *
 * }
  *************************/