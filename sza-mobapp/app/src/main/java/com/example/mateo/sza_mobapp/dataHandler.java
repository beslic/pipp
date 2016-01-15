package com.example.mateo.sza_mobapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateo on 28.10.2015..
 */
public class dataHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "anketa.db";
    private static final String TABLE_ANKETA = "tableAnketa";
    private static final String TABLE_PITANJA = "tablePitanja";
    private static final String TABLE_ODGOVORI = "tableOdgovori";
    private static final String TABLE_KORISNIK = "tableKorisnik";
    private static final String TABLE_ISPUNJAVANJE_ANKETE = "tableIspunjavanjeAnkete";
    private static final String TABLE_ODABRANI_ODGOVORI = "tableOdabraniOdgovori";
    private static final String TABLE_DOSTUPNE_ANKETE = "tableDostupneAnkete";


    public static final String COLUMN_ANKETA_IME = "anketa_ime";
    public static final String COLUMN_ANKETA_ID = "anketa_id";
    public static final String COLUMN_ANKETA_VLASNIK = "vlasnikAnkete";
    public static final String COLUMN_VRIJEME_IZRADE = "vrijemeIzrade";
    public static final String COLUMN_AKTIVNA_OD = "aktivnaOd";
    public static final String COLUMN_AKTIVNA_DO = "aktivnaDo";
    public static final String COLUMN_ANKETA_OPIS = "anketaOpis";


    public static final String COLUMN_PITANJE = "pitanje";
    public static final String COLUMN_PITANJE_ID = "pitanje_id";
    public static final String COLUMN_PITANJE_A = "pitanje_o";

    public static final String COLUMN_ODGOVOR = "odgovor";
    //public static final String COLUMN_BR_ODGOVORA = "br_odgovora";
    public static final String COLUMN_ODGOVOR_PIT_ID = "odgovor_p";
    public static final String COLUMN_ODGOVOR_ID = "odgovorId";
    public static final String COLUMN_RBR_ODGOVOR = "rbrOdgovor";

    public static final String COLUMN_IME = "ime";
    public static final String COLUMN_PREZIME = "prezime";
    public static final String COLUMN_KORISNICKO_IME = "korisnickoIme";
    public static final String COLUMN_LOZINKA = "lozinka";
    public static final String COLUMN_RAZINA_PRAVA = "razinaPrava";

    public static final String COLUMN_ID_ISPUNJAVANJA = "idIspunjvanja";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    public dataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE1 =  "CREATE TABLE IF NOT EXISTS " + TABLE_ANKETA + " ("
                + COLUMN_ANKETA_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ANKETA_IME + " TEXT, "
                + COLUMN_ANKETA_VLASNIK+" TEXT, "
                + COLUMN_ANKETA_OPIS + " TEXT, "
                + COLUMN_AKTIVNA_OD+" DATETIME, "
                + COLUMN_VRIJEME_IZRADE + " DATETIME, "
                + COLUMN_AKTIVNA_DO + " DATETIME)";

        String CREATE_TABLE2 =  "CREATE TABLE IF NOT EXISTS " + TABLE_PITANJA + " ("
                + COLUMN_PITANJE + " TEXT , "
                + COLUMN_PITANJE_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PITANJE_A + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_PITANJE_A + ") REFERENCES " + TABLE_ANKETA + "(" + COLUMN_ANKETA_ID + ") )";

        String CREATE_TABLE3 =  "CREATE TABLE IF NOT EXISTS " + TABLE_ODGOVORI + " ("
                + COLUMN_ODGOVOR_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_ODGOVOR_PIT_ID + " INTEGER, "
                + COLUMN_ODGOVOR + " TEXT, "
                + COLUMN_RBR_ODGOVOR + " INTEGER, "
                /*+ COLUMN_BR_ODGOVORA + " INTEGER, "*/
                + " FOREIGN KEY(" + COLUMN_ODGOVOR_PIT_ID + ") REFERENCES " + TABLE_PITANJA + "("+ COLUMN_PITANJE_ID+") )";


        String CREATE_TABLE5 = "CREATE TABLE IF NOT EXISTS " + TABLE_ISPUNJAVANJE_ANKETE + " ("
                +COLUMN_KORISNICKO_IME+" TEXT, "
                +COLUMN_ANKETA_ID+ " INTEGER, "
                +COLUMN_ID_ISPUNJAVANJA+" INTEGER PRIMARY KEY, "
                +COLUMN_VRIJEME_IZRADE + " DATETIME, "
                +COLUMN_LONGITUDE + " DOUBLE, "
                +COLUMN_LATITUDE + " DOUBLE) ";

        String CREATE_TABLE6 = "CREATE TABLE IF NOT EXISTS " + TABLE_ODABRANI_ODGOVORI + " ("
                +COLUMN_ID_ISPUNJAVANJA+ " TEXT, "
                +COLUMN_PITANJE_ID+" INTEGER, "
                +COLUMN_ODGOVOR_ID + " INTEGER, "
                +"PRIMARY KEY ( "+COLUMN_ID_ISPUNJAVANJA+", "+COLUMN_PITANJE_ID+")) ";

        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE5);
        db.execSQL(CREATE_TABLE6);
        //Log.d("********  Create table:", "    Success");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODGOVORI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PITANJA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANKETA);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_KORISNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODABRANI_ODGOVORI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ISPUNJAVANJE_ANKETE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOSTUPNE_ANKETE);
        onCreate(db);
    }

    public void addAnketa(Anketa anketa, Context c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANKETA_ID, anketa.getIdAnketa());
        values.put(COLUMN_ANKETA_IME, anketa.getNazivAnketa());
        //values.put(COLUMN_ANKETA_VLASNIK, anketa.getVlasnik());
        values.put(COLUMN_ANKETA_OPIS, anketa.getOpisAnketa());
        values.put(COLUMN_AKTIVNA_OD, anketa.getAktivnaOd());
        values.put(COLUMN_VRIJEME_IZRADE, anketa.getVrijemeIzrada());
        values.put(COLUMN_AKTIVNA_DO, anketa.getAktivnaDo());
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ANKETA, null, values)==-1){
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
        }
        //Log.d("*****addAnketa  ", values.getAsString(COLUMN_ANKETA_IME));
        db.close();
    }

    public void addPitanje(Pitanje p, Context c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PITANJE_A, p.getAnketa_id());
        values.put(COLUMN_PITANJE, p.getPitanje());
        values.put(COLUMN_PITANJE_ID, p.getPitanje_id());
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_PITANJA, null, values)==-1){
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
        }
        //Log.d("*****addPitanje  ", values.getAsString(COLUMN_PITANJE) + "  " + values.getAsString(COLUMN_PITANJE_ID));
        db.close();
    }

    public void addOdgovor(Odgovor o, Context c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ODGOVOR_PIT_ID, o.getPitanje_id());
        values.put(COLUMN_ODGOVOR, o.getOdgovor());
        values.put(COLUMN_ODGOVOR_ID, o.getIdOdgovor());
        values.put(COLUMN_RBR_ODGOVOR, o.getRbrOdgovor());
        //values.put(COLUMN_BR_ODGOVORA, o.getBrojOdgovora());
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ODGOVORI, null, values)==-1){
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
        }
        //Log.d("*****addOdgovor  ", values.getAsString(COLUMN_ODGOVOR));
        db.close();
    }

    public boolean addIspunajvanjeAnkete(long anketaId, String korisnik, long brojIspunjavanja, String timestamp, double longitude, double latitude, boolean poznataLokacija){
        boolean rez = false;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANKETA_ID, anketaId);
        values.put(COLUMN_KORISNICKO_IME, korisnik);
        values.put(COLUMN_ID_ISPUNJAVANJA, brojIspunjavanja);
        values.put(COLUMN_VRIJEME_IZRADE, timestamp);
        if(poznataLokacija){
            values.put(COLUMN_LONGITUDE, longitude);
            values.put(COLUMN_LATITUDE, latitude);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ISPUNJAVANJE_ANKETE, null, values)==-1){
            Log.d("*****addIspunjavanje  ", "error");
            rez=true;
        }
        db.close();
        return rez;
    }

    public boolean addOdabraniOdgovori(long brojIspunjavanja, long pitanje, long odgovor){
        boolean rez = true;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_ISPUNJAVANJA, brojIspunjavanja);
        values.put(COLUMN_PITANJE_ID, pitanje);
        values.put(COLUMN_ODGOVOR_ID, odgovor);
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ODABRANI_ODGOVORI, null, values)==-1){
            Log.d("*****addOdabrani  ", "error");
            rez = false;
        }
        db.close();
        return rez;
    }

    public List<Anketa> findAnketa(){
        //Log.d("*****findAnketa ", "pocetak");
        String query = "SELECT * FROM " + TABLE_ANKETA;
        List<Anketa> listaAnketa;
        listaAnketa = new ArrayList<Anketa>();
        Anketa anketa;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            //Log.d("*****findAnketa ", "moved to first");
            cursor.moveToFirst();
            anketa = new Anketa();
            anketa.setIdAnketa(cursor.getLong(0));
            anketa.setNazivAnketa(cursor.getString(1));
            //anketa.setVlasnik(cursor.getString(2));
            anketa.setOpisAnketa(cursor.getString(3));
            anketa.setAktivnaOd(cursor.getString(4));
            anketa.setVrijemeIzrada(cursor.getString(5));
            anketa.setAktivnaDo(cursor.getString(6));
            listaAnketa.add(anketa);
            //Log.d("*****findAnketa ", "dodano na listu: " + anketa.getNazivAnketa()+ " " +anketa.getIdAnketa());
            while(cursor.moveToNext() && i<10){
                anketa = new Anketa();
                anketa.setIdAnketa(cursor.getLong(0));
                anketa.setNazivAnketa(cursor.getString(1));
                //anketa.setVlasnik(cursor.getString(2));
                anketa.setOpisAnketa(cursor.getString(3));
                anketa.setAktivnaOd(cursor.getString(4));
                anketa.setVrijemeIzrada(cursor.getString(5));
                anketa.setAktivnaDo(cursor.getString(6));
                listaAnketa.add(anketa);
                //Log.d("*****findAnketa ", "dodano na listu: " + anketa.getNazivAnketa()+ " " +anketa.getIdAnketa());
                i++;
            }
            cursor.close();
        }
        else{
            anketa=null;
        }
        db.close();
        //Log.d("*****findAnketa ", "gotovo");
        return listaAnketa;
    }

    public ArrayList<Pitanje> findPitanje(long anketaId){
        //Log.d("*****findPitanje  ", "iz " + anketaId);
        ArrayList<Pitanje> listaPitanja;
        listaPitanja = new ArrayList<Pitanje>();
        String query = "SELECT * FROM " + TABLE_PITANJA + " JOIN "+ TABLE_ANKETA +
                " ON " + COLUMN_ANKETA_ID + " = " + COLUMN_PITANJE_A +
                " WHERE " + COLUMN_ANKETA_ID + " = " + anketaId;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        Pitanje pitanje = new Pitanje();
        if(cursor.moveToFirst()){
            //Log.d("*****findPitanje ", "moved to first");
            cursor.moveToFirst();
            pitanje.setAnketa_id(cursor.getLong(2));
            pitanje.setPitanje_id(cursor.getLong(1));
            pitanje.setPitanje(cursor.getString(0));
            pitanje.setOdgovor(this.findOdgovor(Integer.parseInt(cursor.getString(1))));
            listaPitanja.add(pitanje);
            //Log.d("*****findPitanje ", "dodano na listu: " + cursor.getString(0)+ " " +cursor.getString(1));
            while(cursor.moveToNext()){
                pitanje = new Pitanje();
                pitanje.setAnketa_id(cursor.getLong(2));
                pitanje.setPitanje_id(cursor.getLong(1));
                pitanje.setPitanje(cursor.getString(0));
                pitanje.setOdgovor(this.findOdgovor(cursor.getLong(1)));
                listaPitanja.add(pitanje);
                //Log.d("*****findPitanje ", "dodano na listu: " + cursor.getString(0)+ " " +cursor.getString(1));
                i++;
            }
            cursor.close();
        }
        else{
            listaPitanja=null;
        }
        db.close();
        //Log.d("*****findPitanje ", "gotovo");
        return listaPitanja;
    }

    public ArrayList<Odgovor> findOdgovor(long pitanjeId){
        ArrayList<Odgovor> list;
        list = new ArrayList<Odgovor>();
        String query = "SELECT * FROM " + TABLE_PITANJA + " JOIN "+ TABLE_ODGOVORI +
                " ON " + COLUMN_PITANJE_ID + " = " + COLUMN_ODGOVOR_PIT_ID +
                " WHERE " + COLUMN_PITANJE_ID + " = " + pitanjeId;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        Odgovor odgovor = new Odgovor();
        if(cursor.moveToFirst()){
            //Log.d("*****findOdgovor ", "moved to first");
            cursor.moveToFirst();
            odgovor.setIdOdgovor(cursor.getLong(3));
            odgovor.setPitanje_id(cursor.getLong(1));
            odgovor.setOdgovor(cursor.getString(5));
            odgovor.setRbrOdgovor(cursor.getInt(6));
            list.add(odgovor);
            //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
            while(cursor.moveToNext()){
                odgovor = new Odgovor();
                odgovor.setIdOdgovor(cursor.getLong(3));
                odgovor.setPitanje_id(cursor.getLong(1));
                odgovor.setOdgovor(cursor.getString(5));
                odgovor.setRbrOdgovor(cursor.getInt(6));
                list.add(odgovor);
                //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
                i++;
            }
            cursor.close();
        }
        else{
            //list.add(0, "nema odgovora");
        }
        db.close();
        return list;
    }

    public List<NOVO_ispunjavanjeAnkete> findIspunjavajne(){
        List<NOVO_ispunjavanjeAnkete> list;
        list = new ArrayList<NOVO_ispunjavanjeAnkete>();
        String query = "SELECT * FROM " + TABLE_ISPUNJAVANJE_ANKETE;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        NOVO_ispunjavanjeAnkete ispunjavanje = new NOVO_ispunjavanjeAnkete();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            ispunjavanje.setKorisnickoIme(cursor.getString(0));
            ispunjavanje.setAnketaId(cursor.getLong(1));
            ispunjavanje.setIdIspunjavanja(cursor.getLong(2));
            ispunjavanje.setDateTime(cursor.getString(3));
            ispunjavanje.setLongitude(cursor.getDouble(4));
            ispunjavanje.setLatitude(cursor.getDouble(5));
            ispunjavanje.setOdabraniOdgovori(this.findOdabrani(cursor.getLong(2)));
            list.add(ispunjavanje);
            //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
            while(cursor.moveToNext()){
                ispunjavanje = new NOVO_ispunjavanjeAnkete();
                ispunjavanje.setKorisnickoIme(cursor.getString(0));
                ispunjavanje.setAnketaId(cursor.getLong(1));
                ispunjavanje.setIdIspunjavanja(cursor.getLong(2));
                ispunjavanje.setDateTime(cursor.getString(3));
                ispunjavanje.setLongitude(cursor.getDouble(4));
                ispunjavanje.setLatitude(cursor.getDouble(5));
                ispunjavanje.setOdabraniOdgovori(this.findOdabrani(cursor.getLong(2)));
                list.add(ispunjavanje);
                //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
                i++;
            }
            cursor.close();
        }
        else{
            //list.add(0, "nema odgovora");
        }
        db.close();
        return list;
    }

    public List<NOVO_odabraniOdgovori> findOdabrani(long idIspiunjavanja){
        List<NOVO_odabraniOdgovori> list;
        list = new ArrayList<NOVO_odabraniOdgovori>();
        String query = "SELECT * FROM " + TABLE_ODABRANI_ODGOVORI
                +" WHERE "+ COLUMN_ID_ISPUNJAVANJA+ " = "+idIspiunjavanja;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        NOVO_odabraniOdgovori odgovori = new NOVO_odabraniOdgovori();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            odgovori.setIdIspunjavanja(cursor.getLong(0));
            odgovori.setOdgovor(cursor.getLong(2));
            odgovori.setPitanjeId(cursor.getLong(1));
            list.add(odgovori);
            //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
            while(cursor.moveToNext()){
                odgovori = new NOVO_odabraniOdgovori();
                odgovori.setIdIspunjavanja(cursor.getLong(0));
                odgovori.setOdgovor(cursor.getLong(2));
                odgovori.setPitanjeId(cursor.getLong(1));
                list.add(odgovori);
                list.add(odgovori);
                //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
                i++;
            }
            cursor.close();
        }
        else{
            //list.add(0, "nema odgovora");
        }
        db.close();
        return list;
    }

    public void brisanjeAnketaPitanjaOdgovora(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ODGOVORI, null, null);
        db.delete(TABLE_PITANJA, null, null);
        db.delete(TABLE_ANKETA, null, null);
        db.close();
    }

    public void brisanjeIspunjavanja(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ISPUNJAVANJE_ANKETE, null, null);
        db.delete(TABLE_ODABRANI_ODGOVORI, null, null);
        db.close();
    }

    public void ispisBaze(){
        String query="SELECT * FROM "+TABLE_ANKETA+" LEFT JOIN "+TABLE_PITANJA+
                " ON "+COLUMN_ANKETA_ID+" = "+COLUMN_PITANJE_A+
                " LEFT JOIN "+TABLE_ODGOVORI+
                " ON "+COLUMN_PITANJE_ID+" = "+ COLUMN_ODGOVOR_PIT_ID +
                " ORDER BY "+ COLUMN_ANKETA_ID+", "+COLUMN_PITANJE_ID+", "+COLUMN_ODGOVOR+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        Log.d("*****ISPIS", "POCETAK 1*****");
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            Log.d("*****ISPIS ", cursor.getString(1) + " " + cursor.getString(0)
                    + " " + cursor.getString(2) + " " + cursor.getString(3)
                    + " " + cursor.getString(4) + " " + cursor.getString(6)
                    + " " + cursor.getString(7)+" "+cursor.getString(12));
            while(cursor.moveToNext()){
                Log.d("*****ISPIS ", cursor.getString(1)+" "+cursor.getString(0)
                        +" "+cursor.getString(2)+" "+cursor.getString(3) + " " + cursor.getString(4)
                        +" "+cursor.getString(6)+" "+cursor.getString(7)
                        +" "+cursor.getString(12));
            }
            cursor.close();
        }
        else{
            Log.d("*****ISPIS", "prazna baza");
        }
        db.close();
    }

    public void ispisOdgovora(Long brojIspunjavanja){
        String query="SELECT * FROM "+TABLE_ISPUNJAVANJE_ANKETE+" LEFT JOIN "+ TABLE_ODABRANI_ODGOVORI +
                " ON "+ TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+" = "+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_ID_ISPUNJAVANJA+
                " LEFT JOIN " + TABLE_PITANJA + " ON "+TABLE_PITANJA+"."+COLUMN_PITANJE_ID+" = "+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_PITANJE_ID+
                " LEFT JOIN " + TABLE_ODGOVORI + " ON " + TABLE_ODABRANI_ODGOVORI+"."+COLUMN_ODGOVOR_ID +" = "+ TABLE_ODGOVORI+"."+COLUMN_ODGOVOR_ID+
                //" WHERE "+TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+" = "+brojIspunjavanja+
                " ORDER BY "+TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+","+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_PITANJE_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        Log.d("*****ISPIS", "POCETAK 2*****");
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            Log.d("*****ISPIS ", "korisnik:"+ cursor.getString(0)
                    +" "+cursor.getString(2)+" "+cursor.getString(3)+"    @"+cursor.getString(4)
                    +" "+cursor.getString(5)+"    "+cursor.getString(9)+" "+cursor.getString(14));
            while(cursor.moveToNext()){
                Log.d("*****ISPIS ", "korisnik:"+cursor.getString(0)
                        +" "+cursor.getString(2)+" "+cursor.getString(3)
                        +"    @"+cursor.getString(4)+" "+cursor.getString(5)
                        +"    "+cursor.getString(9)+" "+cursor.getString(14));
            }
            cursor.close();
            Log.d("*****ISPIS ", "KRAJ");
        }
        else{
            Log.d("*****ISPIS", "prazna baza");
        }
        db.close();
    }

}
