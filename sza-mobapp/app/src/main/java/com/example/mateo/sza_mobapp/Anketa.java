package com.example.mateo.sza_mobapp;

import java.util.List;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Anketa {
    private String imeAnkete;
    private int anketaId;
    private String vlasnik;
    private List<Pitanje> pitanja;

    public Anketa(){}

    public Anketa(String ime, int anketaId, String vlasnik){
        this.imeAnkete = ime;
        this.anketaId = anketaId;
        this.vlasnik = vlasnik;
    }
    public void setVlasnik(String vlasnikId){
        this.vlasnik = vlasnikId;
    }
    public String getVlasnik(){
        return this.vlasnik;
    }
    public String getIme(){
        return imeAnkete;
    }
    public int getId(){
        return anketaId;
    }
    public void setImeAnkete(String ime){
        this.imeAnkete = ime;
    }
    public void setId(int id){
        this.anketaId = id;
    }
}
