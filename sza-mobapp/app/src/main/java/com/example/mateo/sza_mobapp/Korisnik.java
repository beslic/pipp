package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 15.12.2015..
 */
public class Korisnik {
    private String ime;
    private String lozinka;

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
}
