package com.example.onsteroids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yashovardhan99.timeit.Stopwatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cwiczenie extends AppCompatActivity {

    static List<String> czasSeria = new ArrayList<String>();
    TextView ops;
    TextView nazwaCwiczenia;
    TextView czas;
    Button startStop;
    Button zakoncz;
    int liczbaSerii = 1;
    int stanStopera = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_cwiczenie);
        wpisywaniePowtorzen();
        Stopwatch stopwatch = new Stopwatch();
        startStop = findViewById(R.id.startStop);
        czas = findViewById(R.id.czasCwiczenia);
        zakoncz = findViewById(R.id.zakoncz);
        zakoncz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nacisnietoZakoncz();
            }
        });

        startStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nacisnietoStopStart(stopwatch);
            }
        });
    }

    public String loadObciazenie() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("o", "0");
    }

    public String loadSerie() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("s", "0");
    }

    public String loadPowtorzenia() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("p", "0");
    }

    public String loadNazwa() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("n", "0");
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }

    public void wpisywaniePowtorzen() {
        ops = (TextView) findViewById(R.id.ops);
        nazwaCwiczenia = (TextView) findViewById(R.id.nazwaCwiczenia);
        String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie do wykonania: " + loadSerie();
        ops.setText(ustawione);
        nazwaCwiczenia.setText(loadNazwa());
    }

    public void nacisnietoStopStart(Stopwatch stopwatch) {
        if (stanStopera == 0) {
            System.out.println("Stan: " + 0);
            ops = (TextView) findViewById(R.id.ops);
            String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie: " + liczbaSerii;
            ops.setText(ustawione);
            stopwatch.start();
            stanStopera++;
        } else if (stanStopera == 1) {
            System.out.println("Stan: " + 1);
            if (liczbaSerii == Integer.parseInt(loadSerie())) {
                czasSeria.add(String.valueOf(liczbaSerii));
                czasSeria.add(czas.getText().toString());
                try {
                    TworzenieJsona();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                liczbaSerii = 1;
                finish();
            }
            stopwatch.pause();
            stanStopera++;
        } else if (stanStopera == 2) {
            System.out.println("Stan: " + 2);
            if (liczbaSerii == (Integer.parseInt(loadSerie()) - 1)) {
                ops = (TextView) findViewById(R.id.ops);
                String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie: " + loadSerie();
                ops.setText(ustawione);
                stopwatch.resume();
                startStop.setText("Stop i Zapisz");
                liczbaSerii++;
                stanStopera--;
            }
            if (liczbaSerii < Integer.parseInt(loadSerie())) {
                ops = (TextView) findViewById(R.id.ops);
                liczbaSerii++;
                String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie: " + liczbaSerii;
                ops.setText(ustawione);
                stopwatch.resume();
                stanStopera--;
            }
        }
        stopwatch.setTextView(czas);
    }

    public void nacisnietoZakoncz() {
        if (liczbaSerii <= 0) {
            System.out.println("Wystąpił błąd!");
            liczbaSerii = 1;
            finish();
            try {
                TworzenieJsona();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (liczbaSerii < Integer.parseInt(loadSerie())) {
            czasSeria.add(String.valueOf(liczbaSerii));
            czasSeria.add(czas.getText().toString());
            try {
                TworzenieJsona();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            liczbaSerii = 1;
            finish();
        } else {
            czasSeria.add(String.valueOf(liczbaSerii - 1));
            czasSeria.add(czas.getText().toString());
            try {
                TworzenieJsona();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            liczbaSerii = 1;
            finish();
        }
    }

    public void TworzenieJsona() throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nazwa", loadNick());
        JSONArray jsArray = new JSONArray();
        for (int i = 0; i < czasSeria.size(); i++) {
            jsArray.put(czasSeria.get(i));
        }
        jsonObject.put("serie i czas", jsArray);
        String userString = jsonObject.toString();
        String fileName = loadNick() + "_ostatniTrening" + ".json";
        File file = new File(Cwiczenie.this.getFilesDir(), fileName);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(userString);
        bufferedWriter.close();
    }
}