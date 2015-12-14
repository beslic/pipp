package com.example.mateo.sza_mobapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Mateo on 28.10.2015..
 */


// NEMA JOŠ NIKAKVU FUNKCIJU!!!!!
public class JSONAdapter extends BaseAdapter{

    Context mContext;
    LayoutInflater mLayoutInflater;
    JSONArray mJSONArray;

    public JSONAdapter(Context context, LayoutInflater inflater){
        mContext = context;
        mLayoutInflater = inflater;
        mJSONArray = new JSONArray();
    }

    @Override
    public int getCount(){
        return mJSONArray.length();
    }
    @Override
    public Object getItem(int position){
        return mJSONArray.optJSONObject(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView != null){
            convertView = mLayoutInflater.inflate(R.layout.lista_anketa, null);
            holder = new ViewHolder();
            holder.imeAnkete = (TextView)convertView.findViewById(R.id.imeAnkete);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        JSONObject jsonObject = (JSONObject) getItem(position);
        String anketa="";
        if(jsonObject.has("Anketa")){
            anketa = jsonObject.optString("Anketa");
        }
        holder.imeAnkete.setText(anketa);
        return convertView;
    }

    public void updateData(JSONArray jsonArray){
        mJSONArray = jsonArray;
        notifyDataSetChanged();
    }

    private static class ViewHolder{
        public TextView imeAnkete;
    }


}
