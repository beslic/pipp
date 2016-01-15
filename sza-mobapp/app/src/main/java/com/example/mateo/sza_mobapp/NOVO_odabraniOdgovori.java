package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 14.12.2015..
 */
public class NOVO_odabraniOdgovori {
    private long pitanjeId;
    private long odgovor;

    public NOVO_odabraniOdgovori(){}

    public NOVO_odabraniOdgovori(long pitanjeId, long odgovor) {
        this.pitanjeId = pitanjeId;
        this.odgovor = odgovor;
    }

    public long getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(long odgovor) {
        this.odgovor = odgovor;
    }

    public long getPitanjeId() {
        return pitanjeId;
    }

    public void setPitanjeId(long pitanjeId) {
        this.pitanjeId = pitanjeId;
    }
}
