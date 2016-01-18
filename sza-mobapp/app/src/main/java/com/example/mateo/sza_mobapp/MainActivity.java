package com.example.mateo.sza_mobapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
//import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
        //Log.d("*****Main  ", "pocetak");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        view1 = (TextView)findViewById(R.id.text1);
        view2 = (TextView)findViewById(R.id.text2);

        loginInfo = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
        loginInfoEditor = loginInfo.edit();
        if(loginInfo.getString("ADRESA_SERVERA", "").equals("")){
            loginInfoEditor.putString("ADRESA_SERVERA", "192.168.1.102");
            loginInfoEditor.commit();
        }

        Ankete = (ListView)findViewById(R.id.lista);
        Ankete.setOnItemClickListener(this);
        ///Ankete.setAdapter(mJSONAdapter);
        myArrayAdapter = new AnketeAdapter(this, R.layout.anketa, ankete);
        Ankete.setAdapter(myArrayAdapter);
        dbH = new dataHandler(this, null, null, 1);

        login();
    }



    //LOGIN: podaci o loginu se spremaju kao "SharedPreferences"******************
    public void login(){
        String ime = loginInfo.getString("USERNAME", " ");
        Boolean prijavljen = loginInfo.getBoolean("PRIJAVLJEN", false);

        //Log.d("*****Login", "ime: " + ime);

        if(!prijavljen) {
            Intent i = new Intent(this, loginAct.class);
            startActivityForResult(i, 1);
        }
        else {
            Toast.makeText(this, "Dobrodošli " + ime + "!", Toast.LENGTH_LONG).show();
            refreshTablica();
        }
    }

    public void logout(View view){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Odjava");
        alertDialog.setMessage("Jeste li sigurni da se želite odjaviti? (Za ponovnu prijavu potrebna je internetska veza)");

        alertDialog.setPositiveButton("Da",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loginInfoEditor.putString("USERNAME", "");
                        loginInfoEditor.putBoolean("PRIJAVLJEN", false);
                        loginInfoEditor.putString("PASSWORD", "");
                        loginInfoEditor.commit();
                        dbH.brisanjeAnketaPitanjaOdgovora();
                        login();
                    }
                });

        alertDialog.setNegativeButton("Odustani",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        //cancel = true;
                    }
                });

        alertDialog.show();
    }




    public void Dodaj(View view){
        //String[] imeA;
        Log.d("*****Dodaj  ", "pocetak");
        Intent intent = new Intent(this, dodajAnketu.class);
        startActivity(intent);
        Log.d("*****Dodaj  ", "zavrsen activity");
    }


    public void refresh(View view){
        Korisnik k = new Korisnik(loginInfo.getString("USERNAME", ""), loginInfo.getString("PASSWORD", ""));
        Refresh r = new Refresh();
        if(r.refreshAnkete(k, getApplicationContext(), loginInfo.getString("ADRESA_SERVERA", "192.168.1.102"))== false){
            loginInfoEditor.putBoolean("PRIJAVLJEN", false);
            loginInfoEditor.commit();
            login();
        };
        if(r.slanjeIspunjenih(loginInfo.getString("ADRESA_SERVERA", "192.168.1.102")) == true){
            dbH.brisanjeIspunjavanja();
        }
        refreshTablica();
    }

    public void refreshTablica(){
        List<Anketa> imeA;
        imeA=dbH.findAnketa();
        Log.d("poslije refresh-a", " ");
        dbH.ispisBaze();
        dbH.ispisOdgovora((long) 1);
        myArrayAdapter.clear();
        for(int i=0; i<imeA.size();i++) {
            myArrayAdapter.add(imeA.get(i));
            //Log.d("*****MainActivity", i+" dodana: " + imeA.get(i).getNazivAnketa());
        }
        myArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //Log.d("*****onCreateOptMenu  ", "done");
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
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        }

        if(id == R.id.network_settings){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Network");
            alertDialog.setMessage("Network address?");
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            input.setHint(loginInfo.getString("ADRESA_SERVERA", ""));
            alertDialog.setView(input);

            alertDialog.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            loginInfoEditor.putString("ADRESA_SERVERA", input.getText().toString());
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH);
        try {
            Date AktivnaOd = format.parse(myArrayAdapter.anketa.get(position).getAktivnaOd());
            Date AktivnaDo = format.parse(myArrayAdapter.anketa.get(position).getAktivnaDo());
            Log.d("aktivna od", AktivnaOd.toString());
            Log.d("aktivna do", AktivnaDo.toString());
            Log.d("sada je   ", curDate.toString());

            if(AktivnaDo.before(curDate) || AktivnaOd.after(curDate)){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Anketa nije dostupna");
                alertDialog.setMessage("Dostupnost ankete:\n" + format.format(AktivnaOd) + " - \n" + format.format(AktivnaDo));
                alertDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
            else{
                long aId = myArrayAdapter.anketa.get(position).getIdAnketa();
                //Log.d("*****OnItemClick  ", "anketa: " + aId);
                //Intent i2 = new Intent(this, listaPitanja2.class);
                Intent i2 = new Intent(this, Pocetak_ispunjavanja_ankete.class);
                i2.putExtra("anketa", aId);
                i2.putExtra("anketaIme", myArrayAdapter.anketa.get(position).getNazivAnketa());
                i2.putExtra("anketaOpis", myArrayAdapter.anketa.get(position).getOpisAnketa());
                startActivity(i2);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                if(data.getBooleanExtra("IZLAZ", true) == true){
                    finish();
                }
                refreshTablica();
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }
}
