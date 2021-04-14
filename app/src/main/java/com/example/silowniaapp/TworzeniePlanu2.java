package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class TworzeniePlanu2 extends AppCompatActivity {

    int[] idy = {R.id.przycisk1, R.id.przycisk2, R.id.przycisk3, R.id.przycisk4, R.id.przycisk5, R.id.przycisk6, R.id.przycisk7, R.id.przycisk8, R.id.przycisk9, R.id.przycisk10, R.id.przycisk11, R.id.przycisk12, R.id.przycisk13, R.id.przycisk14, R.id.przycisk15, R.id.przycisk16,
            R.id.przycisk17, R.id.przycisk18, R.id.przycisk19, R.id.przycisk20, R.id.przycisk21, R.id.przycisk22, R.id.przycisk23, R.id.przycisk24, R.id.przycisk25, R.id.przycisk26, R.id.przycisk27, R.id.przycisk28, R.id.przycisk29, R.id.przycisk30 };
    int[] idyET = {R.id.tekst1, R.id.tekst2, R.id.tekst3};
    List<String> cwiczeniaObciazenieSeriePowtorzenia = new ArrayList<String>();
    final int[] kolejnyPrzycisk = {0};
    EditText obciazenie;
    TextView obciazenieText;
    EditText serie;
    TextView serieText;
    EditText powtorzenia;
    TextView powtorzeniaText;
    String liczbaCwiczen;
    String[] tablicaCwiczen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Zrobić wygląd activity
        //Zrobić przycisk OK/////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_tworzenie_planu2);
        try {
            czytanieJsona();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        tworzeniePrzyciskow(Integer.parseInt(liczbaCwiczen));
        for(int i = 0; i < Integer.parseInt(liczbaCwiczen); i++) {
            kolejnyPrzycisk[0] = i;
            Button b1 = findViewById(idy[i]);
            b1.setOnClickListener(new View.OnClickListener() {
                int a = kolejnyPrzycisk[0];
                public void onClick(View v) {
                    b1.setEnabled(false);
                    cwiczeniaObciazenieSeriePowtorzenia.add(tablicaCwiczen[a]);
                    cwiczeniaObciazenieSeriePowtorzenia.add(obciazenie.getText().toString());
                    cwiczeniaObciazenieSeriePowtorzenia.add(serie.getText().toString());
                    cwiczeniaObciazenieSeriePowtorzenia.add(powtorzenia.getText().toString());
                }
            });
        }
    }

    public void tworzeniePrzyciskow(int ilosc){
        ScrollView sv = new ScrollView(this);
        //Tutaj zmieniaj wygląd ekranu, sv to nasz layout, więc jego edytuj tu. Linijkę niżej jest zmiana jego tła.
        sv.setBackgroundColor(getResources().getColor(R.color.tlo));
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        sv.addView(ll);

        obciazenie = new EditText(TworzeniePlanu2.this);
        obciazenieText = new TextView(TworzeniePlanu2.this);
        obciazenie.setId(idyET[0]);
        obciazenie.setTextColor(getResources().getColor(R.color.white));
        obciazenieText.setTextColor(getResources().getColor(R.color.tekstTworzeniePlanu));
        serie = new EditText(TworzeniePlanu2.this);
        serieText = new TextView(TworzeniePlanu2.this);
        serie.setId(idyET[1]);
        serie.setTextColor(getResources().getColor(R.color.white));
        serieText.setTextColor(getResources().getColor(R.color.tekstTworzeniePlanu));
        powtorzenia = new EditText(TworzeniePlanu2.this);
        powtorzeniaText = new TextView(TworzeniePlanu2.this);
        powtorzenia.setId(idyET[2]);
        powtorzenia.setTextColor(getResources().getColor(R.color.white));
        powtorzeniaText.setTextColor(getResources().getColor(R.color.tekstTworzeniePlanu));
        obciazenieText.setText("Obciążenie[kg]");
        obciazenie.setText("1");
        obciazenie.setInputType(InputType.TYPE_CLASS_NUMBER);
        serieText.setText("Liczba serii");
        serie.setText("2");
        serie.setInputType(InputType.TYPE_CLASS_NUMBER);
        powtorzeniaText.setText("Liczba powtórzeń");
        powtorzenia.setText("3");
        powtorzenia.setInputType(InputType.TYPE_CLASS_NUMBER);
        ll.addView(obciazenieText);
        ll.addView(obciazenie);
        ll.addView(serieText);
        ll.addView(serie);
        ll.addView(powtorzeniaText);
        ll.addView(powtorzenia);

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                Button button = new Button(this);
                button.setText(tablicaCwiczen[i]);
                button.setId(idy[i]);
                row.addView(button);
            }
            ll.addView(row);
        }

        Button ok = new Button(this);
        ok.setText("OK");
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Integer.parseInt(liczbaCwiczen) < 1){
                    Toast.makeText(TworzeniePlanu2.this, "Nie dodano żadnego ćwiczenia", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    TworzenieJsona();
                    Intent intent = new Intent(TworzeniePlanu2.this, PlanTreningu.class);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ll.addView(ok);
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
            if(Integer.parseInt(liczbaCwiczen) < 1){
                Toast.makeText(this, "Nie dodano żadnego ćwiczenia", Toast.LENGTH_SHORT).show();
                return;
            }
            JSONArray arr = jsonObject.getJSONArray("ćwiczenia");
            List<String> listaCwiczen = new ArrayList<String>();
            for (int i=0; i<arr.length(); i++) {
                listaCwiczen.add(arr.getString(i));
            }

            tablicaCwiczen = new String[Integer.parseInt(liczbaCwiczen)];
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

    public void TworzenieJsona() throws IOException, JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nazwa", loadNick());
        for (int i = 1; i < cwiczeniaObciazenieSeriePowtorzenia.size(); i+=4) {
            JSONArray jsArray = new JSONArray();
            jsArray.put(cwiczeniaObciazenieSeriePowtorzenia.get(i));
            jsArray.put(cwiczeniaObciazenieSeriePowtorzenia.get(i+1));
            jsArray.put(cwiczeniaObciazenieSeriePowtorzenia.get(i+2));
            jsonObject.put(cwiczeniaObciazenieSeriePowtorzenia.get(i-1), jsArray);
        }
        jsonObject.put("liczba ćwiczeń", liczbaCwiczen);
        JSONArray jsArray2 = new JSONArray();
        for (int i = 0; i < tablicaCwiczen.length; i++) {
            jsArray2.put(tablicaCwiczen[i]);
        }
        jsonObject.put("tablica ćwiczeń", jsArray2);
        String userString = jsonObject.toString();
        String fileName = loadNick() + "_cwiczenia" + ".json";
        File file = new File(TworzeniePlanu2.this.getFilesDir(), fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }


    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}