package com.example.mateo.anketa_proba1;

/**
 * Created by Mateo on 28.10.2015..
 */
public class Pitanje {
    private int anketa_id;
    private String pitanje;
    private int pitanje_id;

    public Pitanje(){}

    public Pitanje(int id, String pit, int pitanje_id){
        this.anketa_id = id;
        this.pitanje = pit;
        this.pitanje_id=pitanje_id;
    }
    public int getPitanje_id(){
        return pitanje_id;
    }
    public String getPitanje(){
        return pitanje;
    }
    public int getAnketa_id(){
        return anketa_id;
    }
    public void setPitanje_id(int id){
        this.pitanje_id=id;
    }
    public void setPitanje(String p){
        this.pitanje = p;
    }
    public void setAnketa_id(int id){
        this.anketa_id = id;
    }
}