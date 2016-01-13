package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mateo on 28.10.2015..
 */
public class loginAct extends AppCompatActivity{
    EditText imeE;
    EditText passE;
    private SharedPreferences login;
    private SharedPreferences.Editor loginEdit;
    Gson gson;
    Korisnik korisnik;
    String odgovor;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imeE=(EditText) findViewById(R.id.loginEdit1);
        passE=(EditText) findViewById(R.id.loginEdit2);
        login = getSharedPreferences("LOGIN", Context.MODE_PRIVATE); //Podaci o log in-u kao "SharedPreferences"
        loginEdit = login.edit();
        gson = new Gson();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Nema nazad!", Toast.LENGTH_LONG).show();
        return;
    }

    public void login1(View view){
        Log.d("*****Login  ", "POCETAK");

        korisnik = new Korisnik(imeE.getText().toString(), (passE.getText().toString()));
        String jsonKorisnik = gson.toJson(korisnik);
        //Korisnik k = gson.fromJson(jsonKorisnik, Korisnik.class);
        Log.d("*****KORISNIK", jsonKorisnik);
        NetworkConnection PROVJERA = new NetworkConnection(getApplicationContext(), login.getString("ADRESA_SERVERA", "192.168.1.102")+":8080/sza-webapp/android/");


        if(PROVJERA.isConnected()==false){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Network");
            alertDialog.setMessage("Network is not enabled. Do you want to go to settings menu?");
            alertDialog.setPositiveButton("Settings",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            //cancel = true;
                        }
                    });
            alertDialog.show();
        }
        else{
            try {
                odgovor=PROVJERA.execute(jsonKorisnik).get();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException s){
                s.printStackTrace();
            }
            Log.d("checkpoint ", "1");
            Log.d("odgovor", odgovor);
            try {
                JSONObject jsonOdgovor = new JSONObject(odgovor);
                Log.d("jsonOdgovor", jsonOdgovor.get("status").toString());

                if (jsonOdgovor.get("status").toString().equals("success")) {
                    loginEdit.putString("USERNAME", korisnik.getIme());

                    //*****************************************
                    loginEdit.putString("PASSWORD", "ovdje treba stavit lozinku");
                    Log.d("password2", "test lozinke");
                    //**********************************************

                    loginEdit.putBoolean("PRIJAVLJEN", true);
                    loginEdit.commit();
                    Log.d("*****Login", "SUCCESS");
                    Toast.makeText(getApplicationContext(), "Dobrodošli "+korisnik.getIme()+"!", Toast.LENGTH_LONG).show();
                    dataRefresh(jsonOdgovor);
                    finish();
                } else {
                    Log.d("*****Login", "FAIL");
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                    alertDialog.setTitle("Prijava");
                    alertDialog.setMessage("Status prijave: " + jsonOdgovor.get("status").toString() + ": "+jsonOdgovor.get("errormessage").toString());

                    alertDialog.setNegativeButton("ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    //cancel = true;
                                }
                            });
                    alertDialog.show();
                }
                }catch(JSONException e){
                    e.printStackTrace();
            }


            /*PROVJERA.provjeri(jsonKorisnik, new NetworkConnection.OnJSONResponseCallback() {
                @Override
                public void onJSONResponse(boolean success, JSONObject response) {
                    if (success) {
                        Log.d("SUCCESS", response.toString());
                        if(true) {   //<-Provjera korisnika i sinkronizacija
                            loginEdit.putString("USERNAME", korisnik.getIme());
                            loginEdit.putString("PASSWORD", korisnik.getLozinka()); <-Spremanje lozinke
                            loginEdit.putBoolean("PRIJAVLJEN", true);
                            loginEdit.commit();
                            Log.d("*****Login", "SUCCESS");
                            Toast.makeText(getApplicationContext(), "Dobrodošli "+korisnik.getIme()+"!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            Log.d("*****Login", "FAIL");
                            imeE.setHint("ponovo.....");
                            passE.setHint("ponovo.....");
                            Toast.makeText(getApplicationContext(), "Pogrešno korisničko ime ili lozinka!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Log.d("FAIL", /*response.toString()"asddf");
                        Toast.makeText(getApplicationContext(), "greška u povezivanju", Toast.LENGTH_LONG).show();
                    }
                }
            }, getApplicationContext());*/
        }
    }




    public void dataRefresh(JSONObject data){
        dataHandler dh = new dataHandler(getApplicationContext(), null, null, 1);
        Anketa anketa;
        try {
            JSONArray ankete = data.getJSONArray("anketa");
            for(int i=0;i<ankete.length();i++){
                String ime = ankete.getJSONObject(i).get("ime").toString();
                int id=Integer.parseInt(ankete.getJSONObject(i).get("id").toString());

                anketa = new Anketa(ime, id, "");
                dh.addAnketa(anketa, getApplicationContext());
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d("*****onCreateOptMenu  ", "done");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Morate biti prijavljeni!", Toast.LENGTH_LONG).show();
            return true;
        }
        if(id == R.id.network_settings){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Network");
            alertDialog.setMessage("Network address?");
            final EditText input = new EditText(loginAct.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            input.setHint(login.getString("ADRESA_SERVERA", ""));
            alertDialog.setView(input);

            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            loginEdit.putString("ADRESA_SERVERA", input.getText().toString());
                            loginEdit.commit();
                            Log.d("Network Changed", input.getText().toString());
                        }
                    });

            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            //cancel = true;
                        }
                    });

            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
