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

import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.apache.http.entity.StringEntity;

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
import java.net.URL;

import javax.security.auth.callback.Callback;

/**
 * Created by Mateo on 15.12.2015..
 */
public class NetworkConnection extends AsyncTask<String, Void, String> {
    private  String urlString= "http://10.129.46.220:8080/sza-webapp/android/";
    private URL url;
    //"http://localhost:8080/sza-webapp/android/";
    //String URL="http://gurujsonrpc.appspot.com/guru";
     boolean prihvaceno = false;
     Context context;
     OnJSONResponseCallback callback;
    JSONObject odg;

    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        //try {
            return asd(urls[0]);
        //} catch (IOException e) {
          //  return "Unable to retrieve web page. URL may be invalid.";
        //}
    }
    // onPostExecute displays the results of the AsyncTask.



    @Override
    protected void onPostExecute(String result) {
        Log.d("ODGOVOR",result);

    }

    public interface OnJSONResponseCallback {
        public void onJSONResponse(boolean success, JSONObject response);
    }

    public NetworkConnection(Context context){
        this.context = context;
        this.callback = callback;
    }

    public JSONObject provjeri(String korisnik, OnJSONResponseCallback callback1, Context context){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        callback = callback1;
        //context = context;
        RequestParams params = new RequestParams();
        params.put("json", korisnik);


        JsonHttpResponseHandler jsonHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                Log.d("*****JsonObject", jsonObject.toString());//<-Json objekt koji dobijes kao response
                //UpdateData(jsonObject);
                callback.onJSONResponse(true, jsonObject);
                odg = jsonObject;
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.d("*****Failure", "json");
                callback.onJSONResponse(false, error);
                odg = error;
            }

        };
        httpClient.post(context, urlString, params, jsonHandler);
        InputStream is = null;

        return odg;
    }


    protected String asd(String podaci){
        String output = "";
        try {
            Log.d("pocetak asd","");
            URL url = new URL("http://10.129.46.220:8080/sza-webapp/android/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            //String input = "{\"ime\":\"korisnik123\",\"lozinka\":\"lozinka123\"}";

            OutputStream os = conn.getOutputStream();
            os.write(podaci.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                Log.d("failed", "asd");
                /*throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());*/
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                Log.d("asdf",output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return output;
    }



    /*public void UpdateData(JSONObject jsonObject){
        String json = jsonObject.toString();
        Log.d("*****UhvacenOdgovor", json);
        prihvaceno = true;
    }*/


    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

}
