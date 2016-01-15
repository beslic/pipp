package com.example.mateo.sza_mobapp;

import android.content.Context;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Mateo on 14.12.2015..
 */
public class NOVO_ispunjavanjeAnkete {
    dataHandler dH;
    Context context;
    private int anketaId;
    private double latitude;
    private double longitude;
    private boolean poznataLokacija;
    private int idIspunjavanja;

    private String korisnickoIme;
    private String dateTime;
    private List<Pitanje> listaPitanja;
    ArrayList<NOVO_odabraniOdgovori> odabraniOdgovori = new ArrayList<NOVO_odabraniOdgovori>();
    Random r = new Random();

    public NOVO_ispunjavanjeAnkete(int anketaId, String korisnickoIme, double latitude, double longitude, Context context, String date, boolean poznataLokacija) {
        this.anketaId = anketaId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.poznataLokacija = poznataLokacija;
        this.context = context;
        this.dateTime = date;
        this.korisnickoIme = korisnickoIme;
        dH = new dataHandler(this.context, null, null, 1);
        listaPitanja = dH.findPitanje(anketaId);
    }

    public void dodajUBazu(){
        do{
            idIspunjavanja = r.nextInt();
        }while(dH.addIspunajvanjeAnkete(anketaId, korisnickoIme, idIspunjavanja, dateTime, longitude, latitude, poznataLokacija));

        for(int i=0; i<odabraniOdgovori.size();i++){
            dH.addOdabraniOdgovori(idIspunjavanja, odabraniOdgovori.get(i).getPitanjeId(), odabraniOdgovori.get(i).getOdgovor() );
        }
    }

    public int getAnketaId() {
        return anketaId;
    }

    public void setAnketaId(int anketaId) {
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

    public ArrayList<NOVO_odabraniOdgovori> getOdabraniOdgovori() {
        return odabraniOdgovori;
    }

    public void setOdabraniOdgovori(ArrayList<NOVO_odabraniOdgovori> odabraniOdgovori) {
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
}
