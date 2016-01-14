package com.example.mateo.sza_mobapp;

import java.util.Date;
import java.util.List;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Anketa {
    private String nazivAnketa;
    private int idAnketa;
    //private String vlasnik;
    private Date vrijemeIzrada;
    private String opisAnketa;
    private boolean jePrivatna;
    private Date aktivnaOd;
    private Date aktivnaDo;
    private int brojPitanja;
    private List<Pitanje> pitanja;

    public Anketa(){}

    public Anketa(String ime, int idAnketa, String vlasnik){
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

    public int getIdAnketa() {
        return idAnketa;
    }

    public void setIdAnketa(int idAnketa) {
        this.idAnketa = idAnketa;
    }

    public List<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(List<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }

    public Date getVrijemeIzrada() {
        return vrijemeIzrada;
    }

    public void setVrijemeIzrada(Date vrijemeIzrada) {
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

    public Date getAktivnaOd() {
        return aktivnaOd;
    }

    public void setAktivnaOd(Date aktivnaOd) {
        this.aktivnaOd = aktivnaOd;
    }

    public Date getAktivnaDo() {
        return aktivnaDo;
    }

    public void setAktivnaDo(Date aktivnaDo) {
        this.aktivnaDo = aktivnaDo;
    }

    public int getBrojPitanja() {
        return brojPitanja;
    }

    public void setBrojPitanja(int brojPitanja) {
        this.brojPitanja = brojPitanja;
    }
}
