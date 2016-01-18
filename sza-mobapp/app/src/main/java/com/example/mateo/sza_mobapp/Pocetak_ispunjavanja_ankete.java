package com.example.mateo.sza_mobapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Pocetak_ispunjavanja_ankete extends AppCompatActivity {
    long anketaId;
    String anketaIme;
    String anketaOpis;
    TextView tekst;
    TextView tekstOpis;
    boolean poznataLokacija = false;
    //boolean start = false;
    double longitude=0;
    double latitude=0;
    GPSLocator mGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetak_ispunjavanja_ankete);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tekst = (TextView) findViewById(R.id.pocetakText1);
        tekstOpis = (TextView) findViewById(R.id.textOpisAnkete);
        Bundle extras = getIntent().getExtras();
        mGPS = new GPSLocator(this);
        anketaId =0;
        anketaIme="default";
        anketaOpis="default";
        if (extras != null) {
            anketaId = extras.getLong("anketa");
            anketaIme = extras.getString("anketaIme");
            anketaOpis = extras.getString("anketaOpis");
            //Log.d("*****pocIspunjavanja  ", "extras != null, anketa: " + anketaId);
            extras.clear();
        }
        tekst.setText(anketaIme);
        tekstOpis.setText(anketaOpis);
        if(mGPS.canGetLocation){
            Log.d("pitanja", "u if-u");
            mGPS.getLocation();
            longitude = mGPS.longitude;
            latitude = mGPS.latitude;
            poznataLokacija = true;
            Log.d("PPA_GPS", "*****lokacija poznata*****");

        }
        else{
            Log.d("pitanja", "u else if-u");
            mGPS.showSettingsAlert();

            Log.d("pitanja", "provjera = false");
        }
    }

    public void pitanja(View view){
        //Calendar c = Calendar.getInstance();
        //long timeStamp = c.getTimeInMillis();
        if(mGPS.canGetLocation){
            Log.d("pitanja", "u if-u");
            mGPS.getLocation();
            longitude = mGPS.longitude;
            latitude = mGPS.latitude;
            poznataLokacija = true;
            Log.d("PPA_GPS", "*****lokacija poznata*****");

        }
        else{

            longitude = 0;
            latitude = 0;
            poznataLokacija = false;
        }

        /*else{
            Log.d("pitanja", "u else if-u");
            mGPS.showSettingsAlert();

            Log.d("pitanja", "provjera = false");
        }*/

        Log.d("pitanja", "u ifu");
        Intent i3 = new Intent(this, listaPitanja2.class);
        i3.putExtra("anketa1", anketaId);
        i3.putExtra("lon", longitude);
        i3.putExtra("lat", latitude);
        i3.putExtra("poznato", poznataLokacija);
        startActivity(i3);

        Log.d("pitanja", "iza ifa");
    }
}
