package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mateo on 27.1.2016..
 */
public class CustomSpinnerAdapter extends ArrayAdapter{
    Context context;
    ArrayList<SpinnerItem> lista;
    int layoutResourceId;


    public CustomSpinnerAdapter(Context context, int layoutResourceId, ArrayList<SpinnerItem> lista){
        super(context, layoutResourceId, lista);
        this.context = context;
        this.lista = lista;
        this.layoutResourceId = layoutResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setText(lista.get(position).getNaziv());
        label.setTextSize(1, 18);
        label.setPadding(20, 0,0,0);
        return label;
    }




    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(lista.get(position).getNaziv());
        label.setTextSize(1, 18);
        label.setPadding(20,10,0,0);
        return label;
    }
}