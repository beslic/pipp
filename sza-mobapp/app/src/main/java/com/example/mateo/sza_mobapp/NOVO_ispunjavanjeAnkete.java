package com.example.mateo.sza_mobapp;

import android.content.Context;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Mateo on 14.12.2015..
 */
public class NOVO_ispunjavanjeAnkete {

    Context context;
    private long anketaId;
    private double latitude;
    private double longitude;
    private boolean poznataLokacija;
    private long idIspunjavanja;

    private String korisnickoIme;
    private String dateTime;
    //private List<Pitanje> listaPitanja;
    List<NOVO_odabraniOdgovori> odabraniOdgovori = new ArrayList<NOVO_odabraniOdgovori>();


    public NOVO_ispunjavanjeAnkete(long anketaId, String korisnickoIme, double latitude, double longitude, Context context, String date, boolean poznataLokacija) {
        this.anketaId = anketaId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.poznataLokacija = poznataLokacija;
        this.context = context;
        this.dateTime = date;
        this.korisnickoIme = korisnickoIme;
        //dH = new dataHandler(this.context, null, null, 1);
        //listaPitanja = dH.findPitanje(anketaId);
    }

    public NOVO_ispunjavanjeAnkete(){

    }

    public void dodajUBazu(){
        Random r = new Random();
        dataHandler dH = new dataHandler(context, null, null, 1);
        do{
            idIspunjavanja = r.nextLong();
        }while(dH.addIspunajvanjeAnkete(anketaId, korisnickoIme, idIspunjavanja, dateTime, longitude, latitude, poznataLokacija));

        for(int i=0; i<odabraniOdgovori.size();i++){
            dH.addOdabraniOdgovori(idIspunjavanja, odabraniOdgovori.get(i).getPitanjeId(), odabraniOdgovori.get(i).getOdgovor() );
        }
    }

    public long getAnketaId() {
        return anketaId;
    }

    public void setAnketaId(long anketaId) {
        this.anketaId = anketaId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<NOVO_odabraniOdgovori> getOdabraniOdgovori() {
        return odabraniOdgovori;
    }

    public void setOdabraniOdgovori(List<NOVO_odabraniOdgovori> odabraniOdgovori) {
        this.odabraniOdgovori = odabraniOdgovori;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public boolean isPoznataLokacija() {
        return poznataLokacija;
    }

    public void setPoznataLokacija(boolean poznataLokacija) {
        this.poznataLokacija = poznataLokacija;
    }

    public long getIdIspunjavanja() {
        return idIspunjavanja;
    }

    public void setIdIspunjavanja(long idIspunjavanja) {
        this.idIspunjavanja = idIspunjavanja;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }


}
