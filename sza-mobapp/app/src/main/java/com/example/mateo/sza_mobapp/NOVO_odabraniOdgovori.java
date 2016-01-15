package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 14.12.2015..
 */
public class NOVO_odabraniOdgovori {
    private int pitanjeId;
    private int odgovor;

    public NOVO_odabraniOdgovori(){}

    public NOVO_odabraniOdgovori(int pitanjeId, int odgovor) {
        this.pitanjeId = pitanjeId;
        this.odgovor = odgovor;
    }

    public int getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(int odgovor) {
        this.odgovor = odgovor;
    }

    public int getPitanjeId() {
        return pitanjeId;
    }

    public void setPitanjeId(int pitanjeId) {
        this.pitanjeId = pitanjeId;
    }
}
