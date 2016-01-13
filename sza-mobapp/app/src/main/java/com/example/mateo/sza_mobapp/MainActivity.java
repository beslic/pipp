package com.example.mateo.sza_mobapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
//import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView view1;
    TextView view2;
    ListView Ankete;


    AnketeAdapter myArrayAdapter;
    ArrayList ankete = new ArrayList();
    private SharedPreferences loginInfo;
    private SharedPreferences.Editor loginInfoEditor;
    dataHandler dbH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("*****Main  ", "pocetak");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view1 = (TextView)findViewById(R.id.text1);
        view2 = (TextView)findViewById(R.id.text2);
        loginInfo = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
        loginInfoEditor = loginInfo.edit();

        Ankete = (ListView)findViewById(R.id.lista);
        Ankete.setOnItemClickListener(this);
        ///Ankete.setAdapter(mJSONAdapter);
        myArrayAdapter = new AnketeAdapter(this, R.layout.anketa, ankete);
        Ankete.setAdapter(myArrayAdapter);
        dbH = new dataHandler(this, null, null, 1);
        //Intent i = new Intent(this, loginAct.class);
        //startActivity(i);
        //refresh();
        //dbH.inicijalizacija();
        //dbH.ispisKorisnika();
        login();
    }



    //LOGIN: podaci o loginu se spremaju kao "SharedPreferences"******************
    public void login(){
        String ime = loginInfo.getString("USERNAME", " ");
        //String lozinka = loginInfo.getString("PASSWORD", " "); <- Spremanje lozinke********************
        Boolean prijavljen = loginInfo.getBoolean("PRIJAVLJEN", false);

        Log.d("*****Login", "ime: " + ime);

        if(!prijavljen) {
            Intent i = new Intent(this, loginAct.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Dobrodošli " + ime + "!", Toast.LENGTH_LONG).show();
        }
        refresh(null);
    }

    public void logout(View view){
        loginInfoEditor.putString("USERNAME","");
        loginInfoEditor.putBoolean("PRIJAVLJEN", false);
        loginInfoEditor.putString("PASSWORD","");
        loginInfoEditor.commit();
        login();
    }




    public void Dodaj(View view){
        //String[] imeA;
        Log.d("*****Dodaj  ", "pocetak");
        Intent intent = new Intent(this, dodajAnketu.class);
        startActivity(intent);
        Log.d("*****Dodaj  ", "zavrsen activity");
    }

    public void refresh(View view){
        List<Anketa> imeA;
        imeA=dbH.findAnketa();
        dbH.ispisBaze();
        myArrayAdapter.clear();
        for(int i=0; i<imeA.size();i++) {
            myArrayAdapter.add(imeA.get(i));
            Log.d("*****MainActivity", i+" dodana: " + imeA.get(i).getNazivAnketa());
        }
        myArrayAdapter.notifyDataSetChanged();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        int aId = myArrayAdapter.anketa.get(position).getIdAnketa();
        Log.d("*****OnItemClick  ", "anketa: " + aId);
        //Intent i2 = new Intent(this, listaPitanja2.class);
        Intent i2 = new Intent(this, Pocetak_ispunjavanja_ankete.class);
        i2.putExtra("anketa", aId);
        i2.putExtra("anketaIme", myArrayAdapter.anketa.get(position).getNazivAnketa());
        startActivity(i2);
    }
}
