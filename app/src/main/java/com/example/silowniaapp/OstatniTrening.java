package com.example.silowniaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class OstatniTrening extends AppCompatActivity {


//
//
//
    //DO ZROBIENIA SERIE (ŻEBY POKAZYWAŁO TYLE ILE SIĘ ZROBIŁO, A NIE TYLE ILE SIE MIAŁO ZROBIĆ)
//
//
//
//


    private AppBarConfiguration mAppBarConfiguration;

    Button ok,loginNawigacja,tworzeniePlanuNawigacja,planNawigacja,ostatniTreningNapis;
    String liczbaCwiczen;
    String[] tablicaCwiczen;
    String[][] seriePowtorzenia;
    String[][] serieCzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.ekran_ostatni_trening);
        super.onCreate(savedInstanceState);
        try {
            czytanieJsona();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tworzeniePrzyciskow(Integer.parseInt(liczbaCwiczen));


/*        for(int i = 0; i < tablicaCwiczen.length; i++){
            System.out.println("tC: " + tablicaCwiczen[i]);
        }

        for(int i = 0; i < seriePowtorzenia.length; i++){
            for(int j = 0; j < 3; j++){
                System.out.println("sP: " + seriePowtorzenia[i][j]);
            }
        }

        for(int i = 0; i < serieCzas.length; i++){
            for(int j = 0; j < 2; j++){
                System.out.println("sC: " + serieCzas[i][j]);
            }
        }*/

    }

    public void tworzeniePrzyciskow(int ilosc){
        ScrollView sv2 = new ScrollView(this);

        ostatniTreningNapis = new Button(this);
        ostatniTreningNapis.setWidth(297);
        ostatniTreningNapis.setHeight(42);
        ostatniTreningNapis.setBackgroundResource(R.mipmap.ostatnitrening);
        ostatniTreningNapis.setEnabled(false);

        loginNawigacja = new Button(this);
        loginNawigacja.setText("PRZEJDŹ DO LOGOWANIA");
        loginNawigacja.setTextSize(20);
        loginNawigacja.setTextColor(getResources().getColor(R.color.white));
        //loginNawigacja.setLayoutParams (new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params4.setMargins(10,10,10,10);
        loginNawigacja.setLayoutParams(params4);
        loginNawigacja.setWidth(200);
        loginNawigacja.setBackgroundResource(R.mipmap.przycisktlo);
        loginNawigacja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OstatniTrening.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        tworzeniePlanuNawigacja = new Button(this);
        tworzeniePlanuNawigacja.setText("przejdź do Tworzenia Planu");
        tworzeniePlanuNawigacja.setTextSize(20);
        tworzeniePlanuNawigacja.setTextColor(getResources().getColor(R.color.white));
        //tworzeniePlanuNawigacja.setLayoutParams (new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params3.setMargins(10,10,10,10);
        tworzeniePlanuNawigacja.setLayoutParams(params3);
        tworzeniePlanuNawigacja.setBackgroundResource(R.mipmap.przycisktlo);
        tworzeniePlanuNawigacja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OstatniTrening.this, TworzeniePlanu.class);
                startActivity(intent);
            }
        });


        planNawigacja = new Button(this);
        planNawigacja.setText("przejdź do Planu");
        planNawigacja.setTextSize(20);
        planNawigacja.setTextColor(getResources().getColor(R.color.white));
        //planNawigacja.setLayoutParams (new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params2.setMargins(10,10,10,10);
        planNawigacja.setLayoutParams(params2);
        planNawigacja.setBackgroundResource(R.mipmap.przycisktlo);
        planNawigacja.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OstatniTrening.this, PlanTreningu.class);
                startActivity(intent);
            }
        });
//        ImageView kreska = findViewById(R.id.kreska);
//        kreska.setImageResource(R.mipmap.kreska);
//        sv2.addView(kreska);
        //Tutaj zmieniaj wygląd ekranu, sv to nasz layout, więc jego edytuj tu. Linijkę niżej jest zmiana jego tła.
        sv2.setBackgroundColor(getResources().getColor(R.color.tlo));
        LinearLayout ll3 = new LinearLayout(this);
        ll3.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.gravity = RelativeLayout.ALIGN_PARENT_LEFT;
        ll3.setLayoutParams(layoutParams);

        sv2.addView(ll3);

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                TextView tv = new TextView(this);
                String t = tablicaCwiczen[i] + "\nSerie: " +
                        seriePowtorzenia[i][2] /*serieCzas[i][0]*/ + "\nPowtórzenia: " + seriePowtorzenia[i][1] + "\nObciążenie: " + seriePowtorzenia[i][0] + "\nCzas: " + serieCzas[i][1];
                tv.setText(t);
                tv.setBackgroundResource(R.drawable.tlo_wybierz);
                tv.setWidth(1300);
                tv.setTextColor(getResources().getColor(R.color.white));
                //tv.setGravity(Gravity.CENTER_VERTICAL);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                //tv.setLayoutParams (new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                tv.setPadding(30,30,30,30);
                tv.setLayoutParams(params);
                row.addView(tv);
            }

            //ll3.addView(ostatniTreningNapis);
            ll3.addView(row);
        }
            ok = new Button(this);
            ok.setText("");
            ok.setEnabled(false);
            ok.setLayoutParams (new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.MATCH_PARENT));
            ok.setBackgroundColor(getResources().getColor(R.color.tlo));
            ok.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(OstatniTrening.this, PlanTreningu.class);
                    startActivity(intent);
                }
            });


            ll3.setGravity(Gravity.CENTER);
            ll3.addView(ok);
            ll3.addView(loginNawigacja);
            ll3.addView(tworzeniePlanuNawigacja);
            ll3.addView(planNawigacja);
            this.setContentView(sv2);
    }

    public void czytanieJsona() throws JSONException {
        try {

            //Otwieranie i sczytywanie pliku login_cwiczenia.json
            FileInputStream fis2 = OstatniTrening.this.openFileInput(loadNick() + "_cwiczenia" + ".json");
            int size2 = fis2.available();
            byte[] buffer2 = new byte[size2];
            fis2.read(buffer2);
            fis2.close();
            String json2 = new String(buffer2, "UTF-8");
            JSONObject jsonObject2 = new JSONObject(json2);
            liczbaCwiczen = jsonObject2.getString("liczba ćwiczeń");
            JSONArray arr2 = jsonObject2.getJSONArray("tablica ćwiczeń");
            tablicaCwiczen = new String[Integer.parseInt(liczbaCwiczen)];
            for(int i = 0; i < Integer.parseInt(liczbaCwiczen); i++){
                tablicaCwiczen[i] = arr2.getString(i);
            }
            seriePowtorzenia = new String[Integer.parseInt(liczbaCwiczen)][3];
            for(int o = 0; o < Integer.parseInt(liczbaCwiczen); o++){
                JSONArray arr3 = jsonObject2.getJSONArray(tablicaCwiczen[o]);
                seriePowtorzenia[o][0] = arr3.getString(0);
                seriePowtorzenia[o][1] = arr3.getString(1);
                seriePowtorzenia[o][2] = arr3.getString(2);
            }

            //Otwieranie i sczytywanie pliku login_ostatniTrening.json
            FileInputStream fis = OstatniTrening.this.openFileInput(loadNick() + "_ostatniTrening" + ".json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            serieCzas = new String[Integer.parseInt(liczbaCwiczen)][2];
            for (int i = 0; i < tablicaCwiczen.length; i++) {
                JSONArray arr = jsonObject.getJSONArray("serie i czas");
                serieCzas[i][0] = arr.getString((i*2));
                serieCzas[i][1] = arr.getString((i*2)+1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}
