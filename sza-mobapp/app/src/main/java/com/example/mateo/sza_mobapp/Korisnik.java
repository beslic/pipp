package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 15.12.2015..
 */
public class Korisnik {
    //private int id;
    private String ime;
    //private String ime;
    //private String prezime;
    private String lozinka;
    /*private String email;
    private int razinaPrava;
    private boolean aktivan;
    private List<Anketa> anketa;*/

    public Korisnik(String ime, String lozinka) {
        this.ime = ime;
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRazinaPrava() {
        return razinaPrava;
    }

    public void setRazinaPrava(int razinaPrava) {
        this.razinaPrava = razinaPrava;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public List<Anketa> getAnketa() {
        return anketa;
    }

    public void setAnketa(List<Anketa> anketa) {
        this.anketa = anketa;
    }*/
}
