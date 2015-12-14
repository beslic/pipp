package com.example.mateo.anketa_proba1;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mateo on 28.10.2015..
 */
public class dodajAnketu extends AppCompatActivity{
    EditText editImeA;
    EditText editIdA;

    EditText editP;
    EditText editIdP;
    EditText editIdPA;

    EditText editO;
    EditText editIdOP;
    EditText editBrO;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodavanje_anketa);
        Log.d("*****dodajAnketu  ", "pocetak");
        editImeA = (EditText) findViewById(R.id.dodajImeAnkete);
        editIdA =(EditText) findViewById(R.id.dodajIdAnkete);

        editP = (EditText)findViewById(R.id.dodajPitanje);
        editIdP = (EditText)findViewById(R.id.dodajIdPitanja);
        editIdPA = (EditText)findViewById(R.id.dodajIdPitanjeAnketa);

        editO = (EditText)findViewById(R.id.dodajOdgovor);
        editIdOP = (EditText)findViewById(R.id.dodajIdOdgovorPitanje);
        editBrO = (EditText)findViewById(R.id.dodajBrojOdgovora);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dodaj1(View view){
        dataHandler dbHandler = new dataHandler(this, null, null, 1);
        Anketa anketa = new Anketa(editImeA.getText().toString(), Integer.parseInt(editIdA.getText().toString()), "admin");
        dbHandler.addAnketa(anketa, this);
        editImeA.setText("");
        editIdA.setText("");
    }

    public void dodaj2(View view){
        dataHandler dbHandler = new dataHandler(this, null, null, 1);
        Pitanje pitanje = new Pitanje(Integer.parseInt(editIdPA.getText().toString()), editP.getText().toString(), Integer.parseInt(editIdP.getText().toString()), null);
        dbHandler.addPitanje(pitanje, this);
        editP.setText("");
        editIdP.setText("");
        editIdPA.setText("");
    }

    public void dodaj3(View view){
        dataHandler dbHandler = new dataHandler(this, null, null, 1);
        Odgovor odgovor = new Odgovor(Integer.parseInt(editIdOP.getText().toString()), editO.getText().toString(), Integer.parseInt(editBrO.getText().toString()));
        String anketa=editImeA.getText().toString();
        if(anketa==""){
            Toast.makeText(this, "napisi ime ankete!", Toast.LENGTH_LONG);
        }
        else {
            dbHandler.addOdgovor(odgovor, this);
            editImeA.setText("");
            editO.setText("");
            editBrO.setText("");
            editIdOP.setText("");
        }
    }

    public void gotovo(View view){
        Log.d("*****dodajAnketu  ", "gotovo");
        finish();
    }

    public void random(View view){
        int maxA = 5;
        int maxP = 5;
        int maxO = 5;
        Anketa anketa;
        Pitanje pitanje;
        Odgovor odgovor;
        dataHandler dbHandler = new dataHandler(this, null, null, 1);
        for(int i=1;i<maxA;i++) {
            anketa = new Anketa("Anketa " + i, i, "admin");
            dbHandler.addAnketa(anketa, this);
            for(int j = 1;j<maxP;j++){
                pitanje = new Pitanje(i, "Pitanje "+j, j+10*i, null);
                dbHandler.addPitanje(pitanje, this);
                for(int k=1;k<maxO;k++){
                    odgovor = new Odgovor(j+10*i, "odgovor "+k+ " na pitanje "+ j, 0);
                    dbHandler.addOdgovor(odgovor, this);
                }
            }
        }
        dbHandler.inicijalizacija();
    }
}
