package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 14.12.2015..
 */
public class NOVO_odabraniOdgovori {
    private long pitanjeId;
    private long odgovorId;
    private long idIspunjavanja;
    public NOVO_odabraniOdgovori(){}

    public NOVO_odabraniOdgovori(long pitanjeId, long odgovor) {
        this.pitanjeId = pitanjeId;
        this.odgovorId = odgovor;
    }

    public long getOdgovor() {
        return odgovorId;
    }

    public void setOdgovor(long odgovor) {
        this.odgovorId = odgovor;
    }

    public long getPitanjeId() {
        return pitanjeId;
    }

    public void setPitanjeId(long pitanjeId) {
        this.pitanjeId = pitanjeId;
    }

    public long getIdIspunjavanja() {
        return idIspunjavanja;
    }

    public void setIdIspunjavanja(long idIspunjavanja) {
        this.idIspunjavanja = idIspunjavanja;
    }
}
