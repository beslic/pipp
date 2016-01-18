package com.example.mateo.sza_mobapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.*;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mateo on 15.1.2016..
 */
public class Refresh {
    Context context;

    public boolean refreshAnkete(Korisnik korisnik, Context context, String adresa) {
        //Log.d("*****Login  ", "POCETAK");
        this.context = context;
        Gson gson = new Gson();
        String odgovor = "";
        String jsonKorisnik = gson.toJson(korisnik);
        NetworkConnection PROVJERA = new NetworkConnection(context, adresa + ":8080/sza-webapp/android/");

        if (PROVJERA.isConnected() == false) {
            Toast.makeText(context, "Nema internetske veze", Toast.LENGTH_LONG).show();
        } else {
            try {
                odgovor = PROVJERA.execute(jsonKorisnik).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException s) {
                s.printStackTrace();
            }
            try {
                JSONObject jsonOdgovor = new JSONObject(odgovor);
                Log.d("jsonOdgovor", jsonOdgovor.get("status").toString());

                if (jsonOdgovor.get("status").toString().equals("success")) {
                    JSONObject jsonKorisnikOdgovor;
                    jsonKorisnikOdgovor = jsonOdgovor.getJSONObject("korisnik");
                    JSONArray poljeAnketa = jsonKorisnikOdgovor.getJSONArray("anketa");
                    dataRefresh(poljeAnketa);
                } else if(jsonOdgovor.get("status").toString().equals("failed") &&
                        (jsonOdgovor.get("errormessage").toString().equals("Lozinka je netočna") ||
                                jsonOdgovor.get("errormessage").toString().equals("Korisničko ime nije pronađeno u bazi"))) {
                    Log.d("*****Login", "FAIL");
                    return false;
                }
                else{
                    Toast.makeText(context, "Ažuriranje anketa neuspješno!", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public void dataRefresh(JSONArray data) {
        dataHandler dh = new dataHandler(context, null, null, 1);
        Anketa anketa;
        dh.brisanjeAnketaPitanjaOdgovora();
        Log.d("prije refresh-a", " ");
        dh.ispisBaze();
        for (int i = 0; i < data.length(); i++) {
            try {
                //Log.d("Broj", i + " data" + data.getJSONObject(i).toString());
                anketa = new Anketa();
                JSONObject anketaObjekt;
                anketaObjekt = data.getJSONObject(i);

                anketa.setOpisAnketa(anketaObjekt.getString("opisAnketa"));
                anketa.setBrojPitanja(Integer.parseInt(anketaObjekt.getString("brojPitanja")));
                anketa.setIdAnketa(Long.parseLong(anketaObjekt.getString("idAnketa")));
                anketa.setAktivnaDo(anketaObjekt.getString("aktivnaDo"));
                anketa.setVrijemeIzrada(anketaObjekt.getString("vrijemeIzrada"));
                anketa.setAktivnaOd(anketaObjekt.getString("aktivnaOd"));
                anketa.setNazivAnketa(anketaObjekt.getString("nazivAnketa"));
                anketa.setJePrivatna(anketaObjekt.getBoolean("jePrivatna"));

                //anketa = gson.fromJson(je, Anketa.class);
                dh.addAnketa(anketa, context);

                JSONArray poljePitanja = anketaObjekt.getJSONArray("pitanja");

                for (int pitanjaBrojac = 0; pitanjaBrojac < poljePitanja.length(); pitanjaBrojac++) {
                    Pitanje novoPitanje = new Pitanje();
                    JSONObject pitanjeObjekt = poljePitanja.getJSONObject(pitanjaBrojac);
                    novoPitanje.setAnketa_id(Long.parseLong(anketaObjekt.getString("idAnketa")));
                    novoPitanje.setPitanje_id(Long.parseLong(pitanjeObjekt.getString("idPitanje")));
                    novoPitanje.setPitanje(pitanjeObjekt.getString("textPitanje"));
                    novoPitanje.setRbrPitanje(Integer.parseInt(pitanjeObjekt.getString("rbrPitanje")));

                    JSONArray poljeOdgovora = pitanjeObjekt.getJSONArray("odgovor");
                    dh.addPitanje(novoPitanje, context);

                    for (int odgovorBrojac = 0; odgovorBrojac < poljeOdgovora.length(); odgovorBrojac++) {
                        Odgovor noviOdgovor = new Odgovor();
                        JSONObject odgovorObjekt = poljeOdgovora.getJSONObject(odgovorBrojac);
                        noviOdgovor.setPitanje_id(Long.parseLong(pitanjeObjekt.getString("idPitanje")));
                        noviOdgovor.setIdOdgovor(Long.parseLong(odgovorObjekt.getString("idOdgovor")));
                        noviOdgovor.setRbrOdgovor(Integer.parseInt(odgovorObjekt.getString("rbrOdgovor")));
                        noviOdgovor.setOdgovor((odgovorObjekt.getString("textOdgovor")));
                        dh.addOdgovor(noviOdgovor, context);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public boolean slanjeIspunjenih(String adresa){

        dataHandler dh = new dataHandler(context, null, null, 1);
        List<NOVO_ispunjavanjeAnkete> ispunjavanja = dh.findIspunjavajne();
        Gson gson = new Gson();
        String jsonIspunjavanja = gson.toJson(ispunjavanja);
        Log.d("ISPUNJENE ", jsonIspunjavanja);
        String odgovor = "";
        NetworkConnection PROVJERA = new NetworkConnection(context, adresa + ":8080/sza-webapp/****/");
        if (PROVJERA.isConnected() == false) {
            Toast.makeText(context, "Nema internetske veze", Toast.LENGTH_LONG).show();
        } else {
            try {
                odgovor = PROVJERA.execute(jsonIspunjavanja).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException s) {
                s.printStackTrace();
            }
            try {
                JSONObject jsonOdgovor = new JSONObject(odgovor);
                Log.d("jsonOdgovor", jsonOdgovor.get("status").toString());

                if (jsonOdgovor.get("status").toString().equals("success")) {
                    return true;
                } else {
                    Log.d("*****Slanje", "FAIL");
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
