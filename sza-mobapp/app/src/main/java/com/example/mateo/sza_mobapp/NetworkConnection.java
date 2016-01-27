package com.example.mateo.sza_mobapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

/*import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;*/

import org.json.JSONObject;

//import org.apache.http.entity.StringEntity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.security.auth.callback.Callback;

/**
 * Created by Mateo on 15.12.2015..
 */
public class NetworkConnection extends AsyncTask<String, Void, String> {
    //private  String urlString= "http://10.129.46.220:8080/sza-webapp/android/";
    //"http://localhost:8080/sza-webapp/android/";
    //String URL="http://gurujsonrpc.appspot.com/guru";
     Context context;
    String adresa;
    int connTimeout;

    @Override
    protected String doInBackground(String... urls) {
            //Log.d("checkpoint ","2");
            return asd(urls[0]);
    }
    // onPostExecute displays the results of the AsyncTask.

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected void onPostExecute(String result) {
        //Log.d("ODGOVOR"," nesto");
        //return result;

    }


    public NetworkConnection(Context context, String adresa, int timeout){
        this.context = context;
        this.adresa = adresa;
        this.connTimeout = timeout;
    }


    protected String asd(String podaci){
        String output = "";
        String odgovor = "";
        try {
            Log.d("pocetak povezivanja", "http://" + adresa);
            URL url = new URL("http://" + adresa);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(connTimeout);
            conn.setReadTimeout(connTimeout);
            //String input = "{\"ime\":\"korisnik123\",\"lozinka\":\"lozinka123\"}";

            OutputStream os = conn.getOutputStream();
            os.write(podaci.getBytes());
            os.flush();



            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                int i = conn.getResponseCode();
                String str = Integer.toString(i);
                Log.d("failed", str);
                conn.disconnect();
                return "{\"status\":\"failed\",\"errormessage\":\"" + str + "\"}";
                /*throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());*/
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //Log.d("asdf", output);
                odgovor = odgovor.concat(output);
            }
            conn.disconnect();
        }catch (SocketTimeoutException s){
            s.printStackTrace();
            odgovor = "{\"status\":\"failed\",\"errormessage\":\"connection timeout\"}";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
            odgovor = "{\"status\":\"failed\",\"errormessage\":\"connection timeout\"}";
        }
        //Log.d("asd return odgovor", " "+odgovor);
        return odgovor;
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

}
