package com.example.neo.group5_gps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

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

/**
 * Created by neo on 23/04/16.
 */
public class BackgroundTask extends AsyncTask <String,Void ,String>  {
    private ProgressDialog pd;
    Context ctx;
    String login_name;
    Double longitude;
    Double latitude;
    BackgroundTask(Context ctx,String login_name,Double latitude,Double longitude) //BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
        this.login_name=login_name;
        this.latitude=latitude;
        this.longitude=longitude;

    }
    BackgroundTask(Context ctx){
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(ctx, "Signing in",
                "Please wait while we are signing you in..");

    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://109.29.198.199/register.php";
        String login_url = "http://109.29.198.199/login.php";
        String json_url ="http://109.29.198.199/all.php";
        String method = params[0];
        if (method.equals("register")) {
            String user_name = params[1];
            String user_pass = params[2];
            String user_longitude = params[4];
            String user_latitude = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                        URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8") + "&" +
                        URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(user_latitude, "UTF-8") + "&" +
                        URLEncoder.encode("longitude","UTF-8")+ "=" +URLEncoder.encode(user_longitude,"UTF-8");
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
                    response+= line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login"))
        {
            String login_name = params[1];
            String login_pass = params[2];
            String longitude = params[3];
            String latitude = params[4];
            try {

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8")+"&"+
                        URLEncoder.encode("latitude","UTF-8")+"="+URLEncoder.encode(latitude,"UTF-8")+"&"+
                        URLEncoder.encode("longitude","UTF-8")+"="+URLEncoder.encode(longitude,"UTF-8");
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
                    response+= line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;
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
        if (result.equals("Reg Success")) {
            MainActivity.mainActivity_update(login_name, latitude, longitude, 1, 1);
            Toast.makeText(ctx, "Registration Success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent("com.example.neo.group5_gps.MapsActivity");
            ctx.startActivity(intent);
        } else {
            if (result.equals("Reg failure")) {
                Toast.makeText(ctx, "ERR : Registration failed Please TRY AGAIN \n Please chose another " + login_name + " \n", Toast.LENGTH_LONG).show();
                Intent intent = new Intent("com.example.neo.group5_gps.InscriptionActivity");
                ctx.startActivity(intent);
            }
        }
        if (result.equals("Login success")) {
            MainActivity.mainActivity_update(login_name, latitude, longitude, 1, 1);
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            String method ="getJeson";
            ParsorJasonTask parsorJasonTask = new ParsorJasonTask(ctx);
            parsorJasonTask.execute(method, login_name);

            /// /Intent intent = new Intent("com.example.neo.group5_gps.MapsActivity");
            //ctx.startActivity(intent);
        } else {

            if (result.equals("Login failure")) {
                Toast.makeText(ctx, "ERR :Login Failed Please TRY AGAIN ", Toast.LENGTH_LONG).show();

                Intent intent = new Intent("com.example.neo.group5_gps.ConnexionActivity");
                ctx.startActivity(intent);
            }
        }

    }

}
