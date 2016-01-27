package com.example.mateo.sza_mobapp;

/**
 * Created by Mateo on 27.1.2016..
 */
public class SpinnerItem {
        String naziv;
        int vrijednost;

        public SpinnerItem(String naziv, int vrijednost) {
            this.naziv = naziv;
            this.vrijednost = vrijednost;
        }

        public String getNaziv() {
            return naziv;
        }

        public void setNaziv(String naziv) {
            this.naziv = naziv;
        }

        public int getVrijednost() {
            return vrijednost;
        }

        public void setVrijednost(int vrijednost) {
            this.vrijednost = vrijednost;
        }
}
