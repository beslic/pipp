package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Odgovor {
    private int pitanje_id;
    private String odgovor;
    private int brojOdgovora;

    public Odgovor(){}

    public Odgovor(int pitanje_id, String o, int br){
        this.pitanje_id = pitanje_id;
        this.odgovor = o;
        this.brojOdgovora=br;
    }
    public int getPitanje_id(){
        return pitanje_id;
    }
    public String getOdgovor(){
        return odgovor;
    }
    public int getBrojOdgovora(){
        return brojOdgovora;
    }
    public void setPitanje_id(int id){
        this.pitanje_id=id;
    }
    public void setOdgovor(String p){
        this.odgovor = p;
    }
    public void setBrojOdgovora(int id){
        this.brojOdgovora = id;
    }
}
