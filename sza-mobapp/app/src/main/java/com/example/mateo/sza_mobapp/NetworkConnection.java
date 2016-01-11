package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.security.auth.callback.Callback;

/**
 * Created by Mateo on 15.12.2015..
 */
public class NetworkConnection {
    private  String urlString= "http://10.129.46.220:8080/sza-webapp/android/";//"http://localhost:8080/sza-webapp/android/";
    //String URL="http://gurujsonrpc.appspot.com/guru";
     boolean prihvaceno = false;
     Context context;
     OnJSONResponseCallback callback;
    JSONObject odg;

    public interface OnJSONResponseCallback {
        public void onJSONResponse(boolean success, JSONObject response);
    }

    public NetworkConnection(Context context){
        this.context = context;
        this.callback = callback;
    }

    public JSONObject provjeri(Korisnik korisnik, OnJSONResponseCallback callback1, Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        callback = callback1;
        //context = context;
        RequestParams params = new RequestParams();

        //try{
            //JSONObject korisnik = new JSONObject(stringKorisnik);


            //Http entity = new ByteArrayEntity(korisnik.toString().getBytes("UTF-8"));
            //entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            params.add("korisnickoIme", korisnik.getIme());
            params.add("lozinka", korisnik.getLozinka());


        httpClient.post(context, urlString,  params,  new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.d("*****JsonObject", jsonObject.toString());//<-Json objekt koji dobijes kao response
                //UpdateData(jsonObject);
                callback.onJSONResponse(true, jsonObject);
                odg=jsonObject;
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.d("*****Failure", "json");
                callback.onJSONResponse(false, error);
                odg = error;
            }

        });

        return odg;
    }



    /*public void UpdateData(JSONObject jsonObject){
        String json = jsonObject.toString();
        Log.d("*****UhvacenOdgovor", json);
        prihvaceno = true;
    }*/




    /*StringEntity se = null;
    try {
        se = new StringEntity(params.toString());
    } catch (UnsupportedEncodingException e) {
        // handle exceptions properly!
    }
    se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

    client.post(null, "www.example.com/objects", se, "application/json", responseHandler);*/







    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

}
