package com.example.mateo.sza_mobapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Mateo on 13.1.2016..
 */
public class Settings extends AppCompatActivity {
    private Switch switch1;
    private Switch switch2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch1.setChecked(false);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "switch1 changed", Toast.LENGTH_LONG).show();
            }
        });
        switch2.setChecked(false);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "switch2 changed", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    public void brisanjeBaze(View view){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Brisanje anketa, pitanja i odgovora");
        alertDialog.setMessage("Jeste li sigurni?");

        alertDialog.setPositiveButton("Da",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataHandler dh = new dataHandler(getApplicationContext(), null, null, 1);
                        dh.brisanjeAnketaPitanjaOdgovora();
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
    public void brisanjeIspunjavanja(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Brisanje ispunjavanja");
        alertDialog.setMessage("Jeste li sigurni?");

        alertDialog.setPositiveButton("Da",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataHandler dh = new dataHandler(getApplicationContext(), null, null, 1);
                        dh.brisanjeIspunjavanja();
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

    @Override
    public void onBackPressed(){
        finish();
    }

}
