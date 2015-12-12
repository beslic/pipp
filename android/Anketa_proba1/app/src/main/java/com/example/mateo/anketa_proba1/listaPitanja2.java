package com.example.mateo.anketa_proba1;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class listaPitanja2 extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /** The {@link ViewPager} that will host the section contents.*/
    private ViewPager mViewPager;
    List<Pitanje> lista1;
    dataHandler dH = new dataHandler(this, null, null, 1);
    int anketaId;
    int brojIspunjavanja;

    double longitude = 0;
    double latitude = 0;
    boolean poznataLokacija = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pitanja2);
        Bundle extras = getIntent().getExtras();
        Random r = new Random();
        anketaId =0;
        int i;
        if (extras != null) {
            anketaId = extras.getInt("anketa1");
            longitude = extras.getDouble("lon");
            latitude = extras.getDouble("lat");
            poznataLokacija = extras.getBoolean("poznato");
            Log.d("*****listaPitanja2  ", "extras != null, anketa: " + anketaId);
            extras.clear();
        }
        lista1 = dH.findPitanje(anketaId);
        if(lista1==null){
            Toast.makeText(this, "nema pitanja", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Log.d("*****listaPitanja2", "POCETAK");
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            do{
                brojIspunjavanja = r.nextInt();
            }while(dH.addIspunajvanjeAnkete(anketaId, "admin", brojIspunjavanja, getDateTime(), longitude, latitude, poznataLokacija));
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            lista1.add(new Pitanje());
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
            mSectionsPagerAdapter.setCount(lista1.size()); //lista1.size(); ili 1?
            mSectionsPagerAdapter.notifyDataSetChanged();
            //mSectionsPagerAdapter.setCount(lista1.size());
            Log.d("*****listaPitanja2", " onCreate SectionsPagerAdapter " + mSectionsPagerAdapter.getCount());
            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mSectionsPagerAdapter.setCount(1);
            mSectionsPagerAdapter.notifyDataSetChanged();
        }

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void kraj(View view){
        dH.ispisOdgovora(brojIspunjavanja);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_pitanja2, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Nema nazad!", Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.*/

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private int page_count = lista1.size();
        private int max_count = lista1.size();
        Context context;
        Fragment fragment;

        public SectionsPagerAdapter(FragmentManager fm, Context c) {
            super(fm);
            this.context=c;
        }
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            ArrayList<String> odgovori = dH.findOdgovor(lista1.get(position).getPitanje_id());
            fragment = PlaceholderFragment.newInstance(position + 1, lista1.get(position), odgovori, context, mSectionsPagerAdapter, mViewPager, max_count, brojIspunjavanja);
            return fragment;
        }
        public void setCount(int count){
            this.page_count = count;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return page_count;
        }
        public int getMax_count(){
            return max_count;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "Pitanje " + position;
        }
    }



    /** A placeholder fragment containing a simple view.*/
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener{
        public static Context context;
        private static final String LIST = "lista";
        private static final String PITANJE = "pitanje";
        private static final String PITANJE_ID = "pitanjeId";
        private static final String PITANJE_ANKETA = "pitAnk";
        /**The fragment argument representing the section number for this fragment.*/
        private static final String ARG_SECTION_NUMBER = "section_number";
        /** Returns a new instance of this fragment for the given sectio number.*/
        private static Pitanje pitanje;
        private static SectionsPagerAdapter Ad;
        private boolean odgovoreno = false;
        private static ViewPager VP;
        private static int maxPages;
        private static int brIspunjavanja;

        public static PlaceholderFragment newInstance(int sectionNumber, Pitanje pit, ArrayList<String> odgovori1, Context c, SectionsPagerAdapter A, ViewPager V, int max, int brIsp) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            context=c;
            Ad=A;
            VP = V;
            pitanje = pit;
            maxPages=max;
            brIspunjavanja=brIsp;
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(PITANJE, pit.getPitanje());
            args.putInt(PITANJE_ID, pit.getPitanje_id());
            args.putInt(PITANJE_ANKETA, pit.getAnketa_id());
            args.putStringArrayList(LIST, odgovori1);
            fragment.setArguments(args);
            Log.d("*****PlFragment", " newInstance "+sectionNumber);
            return fragment;
        }
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.d("*****onCreateView", " "+(getArguments().getStringArrayList(LIST)).get(0));
            int i = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView;
            if(i<maxPages) {
                String s = getArguments().getString(PITANJE);
                int pitanjeId = getArguments().getInt(PITANJE_ID);
                int anketaId = getArguments().getInt(PITANJE_ANKETA);
                Log.d("*****onCreateView", pitanjeId + " " + anketaId);
                pitanje.setAnketa_id(anketaId);
                pitanje.setPitanje_id(pitanjeId);
                ArrayList<String> odgovori = getArguments().getStringArrayList(LIST);
                rootView = inflater.inflate(R.layout.fragment_lista_pitanja2, container, false);
                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                ListView listView = (ListView) rootView.findViewById(R.id.listaOdgovora);
                listView.setOnItemClickListener(this);
                ArrayAdapter myAdapter;
                myAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_activated_1, odgovori);
                //myAdapter = new ArrayAdapter(context, R.layout.odgovori, R.id.odgovor, odgovori);
                listView.setAdapter(myAdapter);
                textView.setText(s);
                //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                //textView.setText((getArguments().getStringArrayList(LIST)).get((getArguments().getInt(ARG_SECTION_NUMBER)) -1));
                Log.d("*****onCreateView", " done " + i);
            }
            else{
                rootView = inflater.inflate(R.layout.kraj_ispunjavanja_ankete, container, false);
            }
            return rootView;
        }

        @Override
        //public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String s = ((TextView)view).getText().toString();
            view.getFocusables(position);
            view.setSelected(true);
            Log.d("*****OdabirOdgovora  ", "odgovor: " + s + " " + pitanje.getPitanje_id() + " " + pitanje.getAnketa_id());
            dataHandler d = new dataHandler(context, null, null, 1);
            if(odgovoreno == false) {
                //d.povecajOdg(s, pitanje.getPitanje_id(), pitanje.getAnketa_id());
                d.addOdabraniOdgovori(brIspunjavanja, pitanje.getPitanje_id(), s);
            }
            int i = Ad.getCount();
            if(i<=Ad.getMax_count() && odgovoreno == false) {
                Ad.setCount(i + 1);
                Ad.notifyDataSetChanged();
                VP.setCurrentItem(i);
            }
            odgovoreno = true;
        }
    }
}
