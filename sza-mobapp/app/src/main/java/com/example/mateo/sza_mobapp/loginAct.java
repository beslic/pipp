package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Mateo on 28.10.2015..
 */
public class loginAct extends Activity{
    EditText imeE;
    EditText passE;
    private SharedPreferences login;
    private SharedPreferences.Editor loginEdit;
    Gson gson;
    Korisnik korisnik;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
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
        korisnik = new Korisnik(imeE.getText().toString(), passE.getText().toString());
        String stringKorisnik = gson.toJson(korisnik);
        String jsonKorisnik = gson.toJson(korisnik);

        Log.d("*****KORISNIK", stringKorisnik);
        NetworkConnection PROVJERA = new NetworkConnection(getApplicationContext());


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
            PROVJERA.provjeri(korisnik, new NetworkConnection.OnJSONResponseCallback() {
                @Override
                public void onJSONResponse(boolean success, JSONObject response) {
                    if (success) {
                        Log.d("SUCCESS", response.toString());
                        if(true) {   //<-Provjera korisnika i sinkronizacija
                            loginEdit.putString("USERNAME", korisnik.getIme());
                            //loginEdit.putString("PASSWORD", korisnik.getLozinka()); <-Spremanje lozinke
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
                        Log.d("FAIL", /*response.toString()*/"asddf");
                        Toast.makeText(getApplicationContext(), "greška u povezivanju", Toast.LENGTH_LONG).show();
                    }
                }
            }, getApplicationContext());
        }
    }
}
