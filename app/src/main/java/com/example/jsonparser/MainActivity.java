package com.example.jsonparser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Spinner spinner1;
    private String url1 = "http://api.fixer.io/latest";
    private EditText mInputView;
    private TextView mOutputView;
    private HandleJSON json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputView = (EditText) findViewById(R.id.editText1);
        mOutputView = (TextView) findViewById(R.id.textView2);

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        Button buttonConvert = (Button) findViewById(R.id.buttonConvert);

        String finalUrl = url1;
        json = new HandleJSON(finalUrl);
        json.fetchJSON();



        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInputView.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_LONG).show();
                else {
                    String sEUR = mInputView.getText().toString();
                    double dEUR = Double.parseDouble(sEUR);


                    double result = 0;
                    if (String.valueOf(spinner1.getSelectedItem()).equals("USD")
                            ) {
                        String sUSD = json.getUSD();
                        result = Double.parseDouble(sUSD) * dEUR;

                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("CZK")
                            ) {
                        String sCZK = json.getCZK();
                        result = Double.parseDouble(sCZK) * dEUR;

                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("GBP")
                            ) {
                        String sGBP = json.getGBP();
                        result = Double.parseDouble(sGBP) * dEUR;

                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("PLN")
                            ) {
                        String sPLN = json.getPLN();
                        result = Double.parseDouble(sPLN) * dEUR;

                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("HUF")
                            ) {
                        String sHUF = json.getHUF();
                        result = Double.parseDouble(sHUF) * dEUR;
                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("AUD")
                            ) {
                        String sAUD = json.getAUD();
                        result = Double.parseDouble(sAUD) * dEUR;

                    } else if (String.valueOf(spinner1.getSelectedItem()).equals("BGN")
                            ) {
                        String sBGN = json.getBGN();
                        result = Double.parseDouble(sBGN) * dEUR;

                    }
                    else if (String.valueOf(spinner1.getSelectedItem()).equals("DKK")
                            ) {
                        String sDKK = json.getDKK();
                        result = Double.parseDouble(sDKK) * dEUR;

                    }

                    else if (String.valueOf(spinner1.getSelectedItem()).equals("HRK")
                            ) {
                        String sHRK = json.getHRK();
                        result = Double.parseDouble(sHRK) * dEUR;

                    }

                    else if (String.valueOf(spinner1.getSelectedItem()).equals("MXN")
                            ) {
                        String sMXN = json.getMXN();
                        result = Double.parseDouble(sMXN) * dEUR;

                    }

                    else if (String.valueOf(spinner1.getSelectedItem()).equals("RUB")
                            ) {
                        String sRUB = json.getRUB();
                        result = Double.parseDouble(sRUB) * dEUR;

                    }

                    else if (String.valueOf(spinner1.getSelectedItem()).equals("NZD")
                            ) {
                        String sNZD = json.getNZD();
                        result = Double.parseDouble(sNZD) * dEUR;

                    }


                    mOutputView.setText(Double.toString(result) + " " + spinner1.getSelectedItem().toString());
                }
            }
            });

        }



}






