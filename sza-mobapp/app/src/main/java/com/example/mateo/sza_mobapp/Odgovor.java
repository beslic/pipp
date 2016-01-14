package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Odgovor {
    private int pitanje_id;
    private String odgovor;
    private int brojOdgovora;
    private int rbrOdgovor;
    private int idOdgovor;

    public Odgovor(){}

    public Odgovor(int pitanje_id, String o, int br){
        this.pitanje_id = pitanje_id;
        this.odgovor = o;
        this.brojOdgovora=br;
    }

    public int getRbrOdgovor() {
        return rbrOdgovor;
    }

    public void setRbrOdgovor(int rbrOdgovor) {
        this.rbrOdgovor = rbrOdgovor;
    }

    public int getIdOdgovor() {
        return idOdgovor;
    }

    public void setIdOdgovor(int idOdgovor) {
        this.idOdgovor = idOdgovor;
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
