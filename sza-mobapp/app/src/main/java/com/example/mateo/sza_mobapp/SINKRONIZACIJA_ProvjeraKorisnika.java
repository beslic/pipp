package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by Mateo on 15.12.2015..
 */
public class SINKRONIZACIJA_ProvjeraKorisnika {
    private static String URL= "http://gturnquist-quoters.cfapps.io/api/random"; //<-bezveze


    public static boolean provjeri(String stringKorisnik){
        final boolean prihvaceno = true;
        AsyncHttpClient httpClient = new AsyncHttpClient();
        try {
            JSONObject korisnik = new JSONObject(stringKorisnik); //<- Json objekt koji predstavlja korisnika(ime i lozinka)
        }catch (Exception e){
            e.printStackTrace();
        }
        //ovo dalje je neki zahtjev, prima neki random json objekt sa stranice http://gturnquist-quoters.cfapps.io/api/random
        //možda možes iskoristit za ovaj naš problem
        httpClient.get(URL, new JsonHttpResponseHandler(){
           @Override
            public void onSuccess(JSONObject jsonObject){
               Log.d("*****JsonObject",jsonObject.toString());//<-Json objekt koji dobijes kao response
           }
            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {}

        });
        return prihvaceno;
    }

    /*public static boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }*/

}
