package com.example.mateo.sza_mobapp;

import java.util.Date;
import java.util.List;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Anketa {
    private String opisAnketa;
    private List<Pitanje> pitanja;
    private int brojPitanja;
    private long idAnketa;
    private String aktivnaDo;
    private String vrijemeIzrada;
    private String aktivnaOd;
    private String nazivAnketa;
    private boolean jePrivatna;
    //private String vlasnik;

    public Anketa(){}

    public Anketa(String ime, long idAnketa, String vlasnik){
        this.nazivAnketa = ime;
        this.idAnketa = idAnketa;
        //this.vlasnik = vlasnik;
    }

    //public void setVlasnik(String vlasnikId){
    //    this.vlasnik = vlasnikId;
    //}
    /*public String getVlasnik(){
        return this.vlasnik;
    }*/
    public void setNazivAnketa(String ime){
        this.nazivAnketa = ime;
    }

    public String getNazivAnketa() {
        return nazivAnketa;
    }

    public long getIdAnketa() {
        return idAnketa;
    }

    public void setIdAnketa(long idAnketa) {
        this.idAnketa = idAnketa;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public String getVrijemeIzrada() {
        return vrijemeIzrada;
    }

    public void setVrijemeIzrada(String vrijemeIzrada) {
        this.vrijemeIzrada = vrijemeIzrada;
    }

    public String getOpisAnketa() {
        return opisAnketa;
    }

    public void setOpisAnketa(String opisAnketa) {
        this.opisAnketa = opisAnketa;
    }

    public boolean isJePrivatna() {
        return jePrivatna;
    }

    public void setJePrivatna(boolean jePrivatna) {
        this.jePrivatna = jePrivatna;
    }

    public String getAktivnaOd() {
        return aktivnaOd;
    }

    public void setAktivnaOd(String aktivnaOd) {
        this.aktivnaOd = aktivnaOd;
    }

    public String getAktivnaDo() {
        return aktivnaDo;
    }

    public void setAktivnaDo(String aktivnaDo) {
        this.aktivnaDo = aktivnaDo;
    }

    public int getBrojPitanja() {
        return brojPitanja;
    }

    public void setBrojPitanja(int brojPitanja) {
        this.brojPitanja = brojPitanja;
    }
}
