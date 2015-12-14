package com.example.mateo.anketa_proba1;

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

    public static final String COLUMN_PITANJE = "pitanje";
    public static final String COLUMN_PITANJE_ID = "pitanje_id";
    public static final String COLUMN_PITANJE_A = "pitanje_o";

    public static final String COLUMN_ODGOVOR = "odgovor";
    //public static final String COLUMN_BR_ODGOVORA = "br_odgovora";
    public static final String COLUMN_ODGOVOR_PIT_ID = "odgovor_p";

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
                + COLUMN_ANKETA_IME + " TEXT"
                + COLUMN_ANKETA_VLASNIK+" TEXT)";

        String CREATE_TABLE2 =  "CREATE TABLE IF NOT EXISTS " + TABLE_PITANJA + " ("
                + COLUMN_PITANJE + " TEXT , "
                + COLUMN_PITANJE_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PITANJE_A + " INTEGER, "
                + "FOREIGN KEY(" + COLUMN_PITANJE_A + ") REFERENCES " + TABLE_ANKETA + "(" + COLUMN_ANKETA_ID + ") )";

        String CREATE_TABLE3 =  "CREATE TABLE IF NOT EXISTS " + TABLE_ODGOVORI + " ("
                + COLUMN_ODGOVOR_PIT_ID + " INTEGER, "
                + COLUMN_ODGOVOR + " TEXT, "
                /*+ COLUMN_BR_ODGOVORA + " INTEGER, "*/
                + " FOREIGN KEY(" + COLUMN_ODGOVOR_PIT_ID + ") REFERENCES " + TABLE_PITANJA + "("+ COLUMN_PITANJE_ID+") )";

        String CREATE_TABLE4 = "CREATE TABLE IF NOT EXISTS " + TABLE_KORISNIK + " ("
                +COLUMN_KORISNICKO_IME+" TEXT PRIMARY KEY, "
                +COLUMN_LOZINKA+ " TEXT, "
                +COLUMN_IME+" TEXT, "
                +COLUMN_PREZIME + " TEXT, "
                +COLUMN_RAZINA_PRAVA+" INTEGER) ";

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
                +COLUMN_ODGOVOR + " TEXT, "
                +"PRIMARY KEY ( "+COLUMN_ID_ISPUNJAVANJA+", "+COLUMN_PITANJE_ID+")) ";

        String CREATE_TABLE7 = "CREATE TABLE IF NOT EXISTS " + TABLE_DOSTUPNE_ANKETE + " ("
                +COLUMN_ANKETA_ID+ " INTEGER, "
                +COLUMN_KORISNICKO_IME+" TEXT, "
                +"PRIMARY KEY ("+COLUMN_ANKETA_ID+", "+COLUMN_KORISNICKO_IME+"))";

        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
        db.execSQL(CREATE_TABLE4);
        db.execSQL(CREATE_TABLE5);
        db.execSQL(CREATE_TABLE6);
        db.execSQL(CREATE_TABLE7);
        Log.d("********  Create table:", "    Success");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODGOVORI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PITANJA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANKETA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KORISNIK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODABRANI_ODGOVORI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ISPUNJAVANJE_ANKETE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOSTUPNE_ANKETE);
        onCreate(db);
    }

    public void inicijalizacija(){
        ContentValues values = new ContentValues();
        values.put(COLUMN_KORISNICKO_IME, "admin");
        values.put(COLUMN_PREZIME, "adminPrezime");
        values.put(COLUMN_IME, "adminIme");
        values.put(COLUMN_LOZINKA, "admin");
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_KORISNIK, null, values)==-1){
            Log.d("*****inicijalizacija  ", "obavljeno");
        }
        db.close();
    }

    public void addAnketa(Anketa anketa, Context c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ANKETA_ID, anketa.getId());
        values.put(COLUMN_ANKETA_IME, anketa.getIme());
        //values.put(COLUMN_ANKETA_VLASNIK, anketa.getVlasnik());
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ANKETA, null, values)==-1){
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
        }
        Log.d("*****addAnketa  ", values.getAsString(COLUMN_ANKETA_IME));
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
        Log.d("*****addPitanje  ", values.getAsString(COLUMN_PITANJE) + "  " + values.getAsString(COLUMN_PITANJE_ID));
        db.close();
    }

    public void addOdgovor(Odgovor o, Context c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ODGOVOR_PIT_ID, o.getPitanje_id());
        values.put(COLUMN_ODGOVOR, o.getOdgovor());
        //values.put(COLUMN_BR_ODGOVORA, o.getBrojOdgovora());
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ODGOVORI, null, values)==-1){
            Toast.makeText(c, "Error", Toast.LENGTH_LONG).show();
        }
        Log.d("*****addOdgovor  ", values.getAsString(COLUMN_ODGOVOR));
        db.close();
    }

    public boolean addIspunajvanjeAnkete(int anketaId, String korisnik, int brojIspunjavanja, String timestamp, double longitude, double latitude, boolean poznataLokacija){
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

    public boolean addOdabraniOdgovori(int brojIspunjavanja, int pitanje, String odgovor){
        boolean rez = true;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_ISPUNJAVANJA, brojIspunjavanja);
        values.put(COLUMN_PITANJE_ID, pitanje);
        values.put(COLUMN_ODGOVOR, odgovor);
        SQLiteDatabase db = this.getWritableDatabase();
        if(db.insert(TABLE_ODABRANI_ODGOVORI, null, values)==-1){
            Log.d("*****addOdabrani  ", "error");
            rez = false;
        }
        db.close();
        return rez;
    }

    public List<Anketa> findAnketa(){
        Log.d("*****findAnketa ", "pocetak");
        String query = "SELECT * FROM " + TABLE_ANKETA;
        List<Anketa> listaAnketa;
        listaAnketa = new ArrayList<Anketa>();
        Anketa anketa;
        int i=1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            Log.d("*****findAnketa ", "moved to first");
            cursor.moveToFirst();
            anketa = new Anketa();
            anketa.setId(Integer.parseInt(cursor.getString(0)));
            anketa.setImeAnkete(cursor.getString(1));
            //anketa.setVlasnik(cursor.getString(2));
            listaAnketa.add(anketa);
            Log.d("*****findAnketa ", "dodano na listu: " + anketa.getIme()+ " " +anketa.getId());
            while(cursor.moveToNext() && i<10){
                anketa = new Anketa();
                anketa.setId(Integer.parseInt(cursor.getString(0)));
                anketa.setImeAnkete(cursor.getString(1));
                //anketa.setVlasnik(cursor.getString(2));
                listaAnketa.add(anketa);
                Log.d("*****findAnketa ", "dodano na listu: " + anketa.getIme()+ " " +anketa.getId());
                i++;
            }
            cursor.close();
        }
        else{
            anketa=null;
        }
        db.close();
        Log.d("*****findAnketa ", "gotovo");
        return listaAnketa;
    }

    public ArrayList<Pitanje> findPitanje(int anketaId){
        Log.d("*****findPitanje  ", "iz " + anketaId);
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
            Log.d("*****findPitanje ", "moved to first");
            cursor.moveToFirst();
            pitanje.setAnketa_id(Integer.parseInt(cursor.getString(2)));
            pitanje.setPitanje_id(Integer.parseInt(cursor.getString(1)));
            pitanje.setPitanje(cursor.getString(0));
            pitanje.setOdgovor(this.findOdgovor(Integer.parseInt(cursor.getString(1))));
            listaPitanja.add(pitanje);
            Log.d("*****findPitanje ", "dodano na listu: " + cursor.getString(0)+ " " +cursor.getString(1));
            while(cursor.moveToNext()){
                pitanje = new Pitanje();
                pitanje.setAnketa_id(Integer.parseInt(cursor.getString(2)));
                pitanje.setPitanje_id(Integer.parseInt(cursor.getString(1)));
                pitanje.setPitanje(cursor.getString(0));
                pitanje.setOdgovor(this.findOdgovor(Integer.parseInt(cursor.getString(1))));
                listaPitanja.add(pitanje);
                Log.d("*****findPitanje ", "dodano na listu: " + cursor.getString(0)+ " " +cursor.getString(1));
                i++;
            }
            cursor.close();
        }
        else{
            listaPitanja=null;
        }
        db.close();
        Log.d("*****findPitanje ", "gotovo");
        return listaPitanja;
    }

    public ArrayList<Odgovor> findOdgovor(int pitanjeId){
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
            odgovor.setPitanje_id(Integer.parseInt(cursor.getString(1)));
            odgovor.setOdgovor(cursor.getString(4));
            list.add(odgovor);
            //Log.d("*****findOdgovor ", "dodano na listu: " + cursor.getString(4));
            while(cursor.moveToNext()){
                odgovor = new Odgovor();
                odgovor.setPitanje_id(Integer.parseInt(cursor.getString(1)));
                odgovor.setOdgovor(cursor.getString(4));
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

    /*public void povecajOdg(String odgovor, int pitanje, int anketa){
        int broj=0;
        Log.d("*****povecajOdg", odgovor + " " + pitanje + " "+anketa);
        String query = "SELECT "+COLUMN_BR_ODGOVORA+" FROM "+TABLE_ANKETA+" LEFT JOIN "+TABLE_PITANJA+
                " ON "+COLUMN_ANKETA_ID+" = "+COLUMN_PITANJE_A+
                " LEFT JOIN "+TABLE_ODGOVORI+
                " ON "+ COLUMN_ODGOVOR_PIT_ID +" = "+COLUMN_PITANJE_ID+
                " WHERE " + COLUMN_ANKETA_ID + " = "+ anketa +
                " AND "+ COLUMN_PITANJE_ID +" = "+ pitanje +
                " AND " + COLUMN_ODGOVOR + " = \""+ odgovor + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            broj = Integer.parseInt(cursor.getString(0));
            ContentValues values = new ContentValues();
            values.put(COLUMN_BR_ODGOVORA, broj + 1);
            db.update(TABLE_ODGOVORI, values, COLUMN_ODGOVOR_PIT_ID + " = " + pitanje + " AND " + COLUMN_ODGOVOR + " = \"" + odgovor + "\" ", null);
        }
        else{
            Log.d("*****povecajOdg", "greska");
        }
        cursor.close();
    }*/

    public boolean provjeriKorisnika(String korisnickoIme, String lozinka){
        boolean ima = false;
        String query="SELECT * FROM "+TABLE_KORISNIK+" WHERE "+
                COLUMN_KORISNICKO_IME+" = \""+korisnickoIme+ "\" AND "+
                COLUMN_LOZINKA+" = \""+lozinka+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            ima=true;
        }
        return ima;
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
            Log.d("*****ISPIS ", cursor.getString(1) + " " + cursor.getString(0) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(6) /*+ " " + cursor.getString(7)*/);
            while(cursor.moveToNext()){
                Log.d("*****ISPIS ", cursor.getString(1)+" "+cursor.getString(0)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(6)/*+" "+cursor.getString(7)*/);
            }
            cursor.close();
        }
        else{
            Log.d("*****ISPIS", "prazna baza");
        }
        db.close();
    }

    public void ispisOdgovora(int brojIspunjavanja){
        String query="SELECT "+TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+", " +COLUMN_PITANJE+", "+ COLUMN_ODGOVOR+
                ", "+COLUMN_LONGITUDE+", "+COLUMN_LATITUDE+", "+COLUMN_VRIJEME_IZRADE+
                " FROM "+TABLE_ISPUNJAVANJE_ANKETE+" LEFT JOIN "+ TABLE_ODABRANI_ODGOVORI +
                " ON "+ TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+" = "+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_ID_ISPUNJAVANJA+
                " LEFT JOIN " + TABLE_PITANJA + " ON "+TABLE_PITANJA+"."+COLUMN_PITANJE_ID+" = "+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_PITANJE_ID+
                //" WHERE "+TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+" = "+brojIspunjavanja+
                " ORDER BY "+TABLE_ISPUNJAVANJE_ANKETE+"."+COLUMN_ID_ISPUNJAVANJA+","+ TABLE_ODABRANI_ODGOVORI +"."+COLUMN_PITANJE_ID+" ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        Log.d("*****ISPIS", "POCETAK 2*****");
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            Log.d("*****ISPIS ", "id:"+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"    @"+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
            while(cursor.moveToNext()){
                Log.d("*****ISPIS ", "id:"+cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+"    @"+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5));
            }
            cursor.close();
            Log.d("*****ISPIS ", "KRAJ");
        }
        else{
            Log.d("*****ISPIS", "prazna baza");
        }
        db.close();
    }

    public void ispisKorisnika(){
        String query="SELECT * FROM "+TABLE_KORISNIK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            Log.d("*****ISPIS ", cursor.getString(0)+" "+cursor.getString(1));
            while(cursor.moveToNext()){
                Log.d("*****ISPIS ", cursor.getString(0)+" "+cursor.getString(1));
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
