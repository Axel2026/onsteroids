package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class TworzeniePlanu2 extends AppCompatActivity {

    String liczbaCwiczen;
    int[] idy = {R.id.przycisk1, R.id.przycisk2, R.id.przycisk3, R.id.przycisk4, R.id.przycisk5, R.id.przycisk6, R.id.przycisk7, R.id.przycisk8, R.id.przycisk9, R.id.przycisk10, R.id.przycisk11, R.id.przycisk12, R.id.przycisk13, R.id.przycisk14, R.id.przycisk15, R.id.przycisk16,
            R.id.przycisk17, R.id.przycisk18, R.id.przycisk19, R.id.przycisk20, R.id.przycisk21, R.id.przycisk22, R.id.przycisk23, R.id.przycisk24, R.id.przycisk25, R.id.przycisk26, R.id.przycisk27, R.id.przycisk28, R.id.przycisk29, R.id.przycisk30 };
    int licznik = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Wpisać tabliceCwiczen do sczytanejTablicy i wpisywać zawartość do przycisków
        //Zrobić pojawianie się edittext od razu, liczby sczytywane są po kliknięciu przycisku i wpisywane do tablicy np. ["wyciskanie", "2", "3", "4"]
        //https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
        //Później wpisać do jsona(nowego lub nie)
        //{"wyciskanie":["2", "3", "4"]}
        //Zrobić wygląd activity
        //Zrobić przycisk OK, chyba musi tylko przechodzić do planu gotowego
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_tworzenie_planu2);
        try {
            czytanieJsona();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String[] sczytanaTablica = new String[Integer.parseInt(liczbaCwiczen)];
        tworzeniePrzyciskow(Integer.parseInt(liczbaCwiczen));

        /*ScrollView sv = new ScrollView(this);
        LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setPadding(50, 10, 0, 0);
        sv.addView(ll2);
        for(int i = 0; i < Integer.parseInt(liczbaCwiczen); i++) {
            Button b1 = (Button) findViewById(idy[i]);
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //LinearLayout row = new LinearLayout(TworzeniePlanu2.this);
                    EditText obciazenie = new EditText(TworzeniePlanu2.this);
                    EditText serie = new EditText(TworzeniePlanu2.this);
                    EditText powtorzenia = new EditText(TworzeniePlanu2.this);
                    obciazenie.setText("Obciążenie");
                    serie.setText("Serie");
                    powtorzenia.setText("Powtórzenia");
                    ll2.addView(obciazenie);
                    ll2.addView(serie);
                    ll2.addView(powtorzenia);
                    licznik++;
                    System.out.println("Kliknąłeś " + licznik + " przycisków");
                }
            });
        }
        this.setContentView(sv);*/
    }

    public void tworzeniePrzyciskow(int ilosc){
        ScrollView sv = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                Button button = new Button(this);
                button.setText("Przycisk#" + i);
                button.setId(idy[i]);
                row.addView(button);
            }
            ll.addView(row);
            Button b1 = (Button) findViewById(idy[i]);
        }
        this.setContentView(sv);
    }

    public void czytanieJsona() throws JSONException {
        try {
            FileInputStream fis = TworzeniePlanu2.this.openFileInput(loadNick() + ".json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            liczbaCwiczen = jsonObject.getString("liczba ćwiczeń");
            JSONArray arr = jsonObject.getJSONArray("ćwiczenia");
            List<String> listaCwiczen = new ArrayList<String>();
            for (int i=0; i<arr.length(); i++) {
                listaCwiczen.add(arr.getString(i));
            }

            String[] tablicaCwiczen = new String[Integer.parseInt(liczbaCwiczen)];
            int j = 0;
            for (int i=0; i<listaCwiczen.size(); i++) {
                if(!listaCwiczen.get(i).equals("null")) {
                    tablicaCwiczen[j] = listaCwiczen.get(i);
                    j++;
                }
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