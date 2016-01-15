package com.example.mateo.sza_mobapp;

import java.util.ArrayList;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Pitanje {

    private long anketa_id;
    private String pitanje;
    private long pitanje_id;
    private ArrayList<Odgovor> odgovor;
    private int rbrPitanje;

    dataHandler dH;

    public Pitanje(){}

    public Pitanje(long id, String pit, long pitanje_id, ArrayList<Odgovor> odg){
        this.anketa_id = id;
        this.pitanje = pit;
        this.pitanje_id=pitanje_id;
        this.odgovor = odg;
    }

    public int getRbrPitanje() {
        return rbrPitanje;
    }

    public void setRbrPitanje(int rbrPitanje) {
        this.rbrPitanje = rbrPitanje;
    }

    public long getPitanje_id(){
        return pitanje_id;
    }
    public String getPitanje(){
        return pitanje;
    }
    public long getAnketa_id(){
        return anketa_id;
    }
    public void setPitanje_id(long id){
        this.pitanje_id=id;
    }
    public void setPitanje(String p){
        this.pitanje = p;
    }
    public void setAnketa_id(long id){
        this.anketa_id = id;
    }

    public ArrayList<Odgovor> getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(ArrayList<Odgovor> odgovor) {
        this.odgovor = odgovor;
    }
}
