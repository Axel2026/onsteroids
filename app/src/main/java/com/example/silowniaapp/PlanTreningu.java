package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PlanTreningu extends AppCompatActivity {

    int[] idy = {R.id.przycisk1, R.id.przycisk2, R.id.przycisk3, R.id.przycisk4, R.id.przycisk5, R.id.przycisk6, R.id.przycisk7, R.id.przycisk8, R.id.przycisk9, R.id.przycisk10, R.id.przycisk11, R.id.przycisk12, R.id.przycisk13, R.id.przycisk14, R.id.przycisk15, R.id.przycisk16,
            R.id.przycisk17, R.id.przycisk18, R.id.przycisk19, R.id.przycisk20, R.id.przycisk21, R.id.przycisk22, R.id.przycisk23, R.id.przycisk24, R.id.przycisk25, R.id.przycisk26, R.id.przycisk27, R.id.przycisk28, R.id.przycisk29, R.id.przycisk30 };
    String liczbaCwiczen;
    Button zakonczTrening;
    String[] tablicaCwiczen;
    String[][] seriePowtorzenia;
    final int[] kolejnyPrzycisk = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_plan_treningu);

        try {
            czytanieJsona();
            tworzeniePrzyciskow(Integer.parseInt(liczbaCwiczen));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Integer.parseInt(liczbaCwiczen); i++) {
            kolejnyPrzycisk[0] = i;
            Button b1 = findViewById(idy[i]);
            b1.setOnClickListener(new View.OnClickListener() {
                int a = kolejnyPrzycisk[0];
                public void onClick(View v) {
                    b1.setBackgroundResource(R.drawable.tlo_zrobione);
                    Intent intentCwiczenie = new Intent(PlanTreningu.this, Cwiczenie.class);
                    saveCw("o", seriePowtorzenia[a][0]);
                    saveCw("s", seriePowtorzenia[a][1]);
                    saveCw("p", seriePowtorzenia[a][2]);
                    saveCw("n", tablicaCwiczen[a]);
                    b1.setEnabled(false);
                    startActivity(intentCwiczenie);
                }
            });
        }
    }

    public void tworzeniePrzyciskow(int ilosc){
        ScrollView sv2 = new ScrollView(this);
        //Tutaj zmieniaj wygląd ekranu, sv to nasz layout, więc jego edytuj tu. Linijkę niżej jest zmiana jego tła.
        sv2.setBackgroundColor(getResources().getColor(R.color.tlo));
        LinearLayout ll3 = new LinearLayout(this);
        ll3.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = RelativeLayout.ALIGN_PARENT_LEFT;
        ll3.setLayoutParams(layoutParams);
        sv2.addView(ll3);

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                Button button = new Button(this);
                String t = "O: " + seriePowtorzenia[i][0] + " S: " + seriePowtorzenia[i][1] + " P: " + seriePowtorzenia[i][2];
                button.setBackgroundResource(R.drawable.tlo_nie_zrobione);
                button.setText(tablicaCwiczen[i] + "  " +t);
                button.setId(idy[i]);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                button.setLayoutParams (new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                row.addView(button);
            }
            ll3.addView(row);
        }
        zakonczTrening = new Button(this);
        zakonczTrening.setText("ZAKONCZ TRENING");
        zakonczTrening.setBackgroundColor(getResources().getColor(R.color.okZielony));
        zakonczTrening.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(PlanTreningu.this, OstatniTrening.class);
                startActivity(intent);
            }
        });
        ll3.addView(zakonczTrening);

        this.setContentView(sv2);
    }

    public void czytanieJsona() throws JSONException {
        try {
            //Otwieranie i sczytywanie pliku login.json
            FileInputStream fis = PlanTreningu.this.openFileInput(loadNick() + ".json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            liczbaCwiczen = jsonObject.getString("liczba ćwiczeń");
            JSONArray arr = jsonObject.getJSONArray("ćwiczenia");
            List<String> listaCwiczen = new ArrayList<String>();
            for (int i = 0; i < arr.length(); i++) {
                listaCwiczen.add(arr.getString(i));
            }

            tablicaCwiczen = new String[Integer.parseInt(liczbaCwiczen)];
            int j = 0;
            for (int i = 0; i < listaCwiczen.size(); i++) {
                if (!listaCwiczen.get(i).equals("null")) {
                    tablicaCwiczen[j] = listaCwiczen.get(i);
                    j++;
                }
            }

            //Otwieranie i sczytywanie pliku login_cwiczenia.json
            FileInputStream fis2 = PlanTreningu.this.openFileInput(loadNick() + "_cwiczenia" + ".json");
            int size2 = fis2.available();
            byte[] buffer2 = new byte[size2];
            fis2.read(buffer2);
            fis2.close();
            String json2 = new String(buffer2, "UTF-8");
            JSONObject jsonObject2 = new JSONObject(json2);
            seriePowtorzenia = new String[Integer.parseInt(liczbaCwiczen)][3];
            for(int o = 0; o < Integer.parseInt(liczbaCwiczen); o++){
                JSONArray arr2 = jsonObject2.getJSONArray(tablicaCwiczen[o]);
                seriePowtorzenia[o][0] = arr2.getString(0);
                seriePowtorzenia[o][1] = arr2.getString(1);
                seriePowtorzenia[o][2] = arr2.getString(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }

    public void saveCw(String klucz, String cw) {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(klucz, cw);
        editor.apply();
    }
}