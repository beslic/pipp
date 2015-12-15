package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by Mateo on 28.10.2015..
 */
public class loginAct extends Activity{
    EditText imeE;
    EditText passE;
    private SharedPreferences login;
    private SharedPreferences.Editor loginEdit;

    Gson gson;

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


    //Kad se stisne "Log in" gumb:
    public void login1(View view){
        Log.d("*****Login  ", "POCETAK");

        Korisnik korisnik = new Korisnik(imeE.getText().toString(), passE.getText().toString());
        String stringKorisnik = gson.toJson(korisnik);

        if(SINKRONIZACIJA_ProvjeraKorisnika.provjeri(stringKorisnik)){   //<-Provjera korisnika i sinkronizacija
            loginEdit.putString("USERNAME", korisnik.getIme());
            //loginEdit.putString("PASSWORD", korisnik.getLozinka()); <-Spremanje lozinke
            loginEdit.putBoolean("PRIJAVLJEN", true);
            loginEdit.commit();
            Log.d("*****Login", "SUCCESS");
            Toast.makeText(this, "Dobrodošli "+korisnik.getIme()+"!", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            Log.d("*****Login", "FAIL");
            imeE.setHint("ponovo.....");
            passE.setHint("ponovo.....");
            Toast.makeText(this, "Pogrešno korisničko ime ili lozinka!", Toast.LENGTH_LONG).show();
        }
    }
}
