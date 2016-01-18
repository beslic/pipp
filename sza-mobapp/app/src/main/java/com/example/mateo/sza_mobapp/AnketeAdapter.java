package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mateo on 4.11.2015..
 */
public class AnketeAdapter extends ArrayAdapter<Anketa> {
    Context context;
    int layoutResourceId;
    List<Anketa> anketa = null;

    public AnketeAdapter(Context context, int layoutResourceId, List<Anketa> anketa){
        super(context, layoutResourceId, anketa);
        this.context=context;
        this.layoutResourceId=layoutResourceId;
        this.anketa=anketa;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        AnketaHolder holder = null;
        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new AnketaHolder();
            holder.ime=(TextView)row.findViewById(R.id.anketaIme);
            //holder.id=(TextView)row.findViewById(R.id.idAnketa);
            row.setTag(holder);
        }
        else{
            holder = (AnketaHolder)row.getTag();
        }
        Anketa anketa1 = anketa.get(position);
        holder.ime.setText(anketa1.getNazivAnketa());
        //holder.id.setText(Long.toString(anketa1.getIdAnketa()));
        return row;
    }

    @Override
    public void add(Anketa anketa2){
        this.anketa.add(anketa2);
    }

    static class AnketaHolder{
        TextView ime;
        //TextView id;
    }
}
