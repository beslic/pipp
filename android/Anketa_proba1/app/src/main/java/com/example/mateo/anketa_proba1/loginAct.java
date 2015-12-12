package com.example.mateo.anketa_proba1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mateo on 28.10.2015..
 */
public class loginAct extends Activity{
    EditText imeE;
    EditText passE;
    private SharedPreferences login;
    private SharedPreferences.Editor loginEdit;
    dataHandler db;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        imeE=(EditText) findViewById(R.id.loginEdit1);
        passE=(EditText) findViewById(R.id.loginEdit2);
        login = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        loginEdit = login.edit();
        db = new dataHandler(getApplicationContext(), null, null, 1);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Nema nazad!", Toast.LENGTH_LONG).show();
        return;
    }

    public void login1(View view){
        Log.d("*****Login  ", "POCETAK");
        String ime;
        String pass;
        ime = imeE.getText().toString();
        pass = passE.getText().toString();
        if(db.provjeriKorisnika(ime, pass)){
            loginEdit.putString("USERNAME", ime);
            loginEdit.putString("PASSWORD", pass);
            loginEdit.commit();
            Log.d("*****Login", "SUCCESS");
            Toast.makeText(this, "Dobrodošli "+ime+"!", Toast.LENGTH_LONG).show();
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
