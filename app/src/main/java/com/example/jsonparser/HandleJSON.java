package com.example.jsonparser;

/**
 * Created by Petko on 24.3.2015.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;

public class HandleJSON {
    private String USD = "USD";
    private String CZK = "CZK";
    private String GBP = "GBP";
    private String PLN = "PLN";
    private String HUF = "HUF";
    private String AUD = "AUD";
    private String BGN = "BGN";
    private String DKK = "DKK";
    private String HRK = "HRK";
    private String MXN = "MXN";
    private String RUB = "RUB";
    private String NZD = "NZD";





    private String urlString = null;

    public volatile boolean parsingComplete = true;
    public HandleJSON(String url){
        this.urlString = url;
    }
    public String getUSD(){
        return USD;
    }
    public String getCZK(){
        return CZK;
    }
    public String getGBP(){
        return GBP;
    }
    public String getPLN(){
        return PLN;
    }
    public String getHUF(){
        return HUF;
    }
    public String getAUD(){
        return AUD;
    }
    public String getBGN(){
        return BGN;
    }
    public String getDKK()  { return DKK;}
    public String getHRK() {return HRK; }
    public String getMXN() { return MXN; }
    public String getRUB() {return RUB;}
    public String getNZD() {return NZD;}

    @SuppressLint("NewApi")
    public void readAndParseJSON(String in) {
        try {
            JSONObject reader = new JSONObject(in);



            JSONObject rates  = reader.getJSONObject("rates");
            USD = rates.getString("USD");
            CZK = rates.getString("CZK");
            GBP = rates.getString("GBP");
            PLN = rates.getString("PLN");
            HUF = rates.getString("HUF");
            AUD = rates.getString("AUD");
            BGN = rates.getString("BGN");
            DKK = rates.getString("DKK");
            HRK = rates.getString("HRK");
            MXN = rates.getString("MXN");
            RUB = rates.getString("RUB");
            NZD = rates.getString("NZD");

            parsingComplete = false;



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    String data = convertStreamToString(stream);

                    readAndParseJSON(data);
                    stream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
