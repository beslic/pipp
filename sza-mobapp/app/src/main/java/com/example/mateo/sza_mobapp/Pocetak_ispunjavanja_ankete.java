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
    TextView tekst;
    boolean poznataLokacija = false;
    boolean start = false;
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
        Bundle extras = getIntent().getExtras();
        mGPS = new GPSLocator(this);
        anketaId =0;
        anketaIme="default";
        if (extras != null) {
            anketaId = extras.getLong("anketa");
            anketaIme = extras.getString("anketaIme");
            //Log.d("*****pocIspunjavanja  ", "extras != null, anketa: " + anketaId);
            extras.clear();
        }
        tekst.setText(anketaIme);

    }

    public void pitanja(View view){
        //Calendar c = Calendar.getInstance();
        //long timeStamp = c.getTimeInMillis();
        if(mGPS.canGetLocation){
            mGPS.getLocation();
            longitude = mGPS.longitude;
            latitude = mGPS.latitude;
            poznataLokacija = true;
            //Log.d("PPA_GPS", "*****lokacija poznata*****");
            start=true;
        }
        else if(start == false){
            start = mGPS.showSettingsAlert();
        }
        //mGPS.stopUsingGPS();
        if(start) {
            Intent i3 = new Intent(this, listaPitanja2.class);
            i3.putExtra("anketa1", anketaId);
            i3.putExtra("lon", longitude);
            i3.putExtra("lat", latitude);
            i3.putExtra("poznato", poznataLokacija);
            startActivity(i3);
        }
    }
}
