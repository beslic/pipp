package com.example.mateo.sza_mobapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mateo on 27.1.2016..
 */
public class NetworkAlertDialog {



    public static AlertDialog.Builder create(Context context, SharedPreferences loginInfo, final SharedPreferences.Editor loginInfoEditor){

        final EditText input = new EditText(context);
        final Spinner spinner = new Spinner(context);
        ArrayList<SpinnerItem> lista = new ArrayList<>();
        lista.add(new SpinnerItem("1000 ms", 1000));
        lista.add(new SpinnerItem("5000 ms", 5000));
        lista.add(new SpinnerItem("7000 ms", 7000));
        lista.add(new SpinnerItem("10 000 ms", 10000));
        lista.add(new SpinnerItem("20 000 ms", 20000));
        lista.add(new SpinnerItem("40 000 ms", 40000));
        lista.add(new SpinnerItem("60 000 ms", 60000));
        final CustomSpinnerAdapter myAdapter = new CustomSpinnerAdapter(context, R.layout.spinner_layout, lista);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        for(int i = 0;i<lista.size();i++){
            if(lista.get(i).getVrijednost() == loginInfo.getInt("CONN_TIMEOUT", 5000)){
                spinner.setSelection(i);
                break;
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem i = (SpinnerItem) parent.getSelectedItem();
                Log.d("selected", i.getNaziv());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        LinearLayout verticalLayout = new LinearLayout(context);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Adresa servera");
        alertDialog.setMessage("Upišite adresu servera: ");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        input.setText(loginInfo.getString("ADRESA_SERVERA", "192.168.1.102"));

        final TextView textView = new TextView(context);
        textView.setText("Timeout: ");
        textView.setPadding(10, 10, 10, 0);
        textView.setTextSize(1, 20);
        verticalLayout.addView(input);
        verticalLayout.addView(textView);
        verticalLayout.addView(spinner);

        alertDialog.setView(verticalLayout);
        alertDialog.setPositiveButton("Potvrdi",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loginInfoEditor.putString("ADRESA_SERVERA", input.getText().toString());
                        SpinnerItem i = (SpinnerItem) spinner.getSelectedItem();
                        loginInfoEditor.putInt("CONN_TIMEOUT", i.getVrijednost());
                        Log.d("Network Changed", input.getText().toString() + "  " + i.getVrijednost() + " ms");
                        loginInfoEditor.commit();
                    }
                });

        alertDialog.setNegativeButton("Odustani",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        //cancel = true;
                    }
                });


        return alertDialog;
    }



}
