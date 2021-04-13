package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class Cwiczenie extends AppCompatActivity {

    TextView ops;
    TextView nazwaCwiczenia;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_cwiczenie);
        wpisywaniePowtorzen();
    }

    public String loadObciazenie() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("o"+ i, "0");
    }
    public String loadSerie() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("s"+ i, "0");
    }
    public String loadPowtorzenia() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("p"+ i, "0");
    }
    public String loadNazwa() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("n"+ i, "0");
    }
    public String loadLiczbaCwiczen() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("l", "0");
    }

    public void wpisywaniePowtorzen(){
        ops = (TextView) findViewById(R.id.ops);
        nazwaCwiczenia = (TextView) findViewById(R.id.nazwaCwiczenia);
        String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie: " + loadSerie();
        ops.setText(ustawione);
        nazwaCwiczenia.setText(loadNazwa());
    }
}