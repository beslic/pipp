package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Odgovor {
    private long pitanje_id;
    private String odgovor;
    private int brojOdgovora;
    private int rbrOdgovor;
    private long idOdgovor;

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

    public long getIdOdgovor() {
        return idOdgovor;
    }

    public void setIdOdgovor(long idOdgovor) {
        this.idOdgovor = idOdgovor;
    }

    public long getPitanje_id(){
        return pitanje_id;
    }
    public String getOdgovor(){
        return odgovor;
    }
    public int getBrojOdgovora(){
        return brojOdgovora;
    }
    public void setPitanje_id(long id){
        this.pitanje_id=id;
    }
    public void setOdgovor(String p){
        this.odgovor = p;
    }
    public void setBrojOdgovora(int id){
        this.brojOdgovora = id;
    }
}
