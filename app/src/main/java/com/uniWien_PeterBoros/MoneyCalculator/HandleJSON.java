package com.uniWien_PeterBoros.MoneyCalculator;

/**
 * Created by Peter Boros on 24.3.2015.
 */

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;



// class for parsing an JSON api
// first, create variables

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


    public HandleJSON(String url)
    {
        this.urlString = url;
    }
    // getter for the currency values

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
    public String getDKK() { return DKK; }
    public String getHRK() { return HRK; }
    public String getMXN() { return MXN; }
    public String getRUB() { return RUB; }
    public String getNZD() { return NZD; }


    // get JSON object

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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // this is the only code I have from internet (stackoverflow), I didnt wanted to use ajax od jar libraries. This fetches the Json from website.

    public void fetchJSON(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
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
