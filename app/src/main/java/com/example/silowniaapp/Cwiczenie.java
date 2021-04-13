package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.yashovardhan99.timeit.Stopwatch;

public class Cwiczenie extends AppCompatActivity {

    TextView ops;
    TextView nazwaCwiczenia;
    TextView czas;
//    Button startStop = findViewById(R.id.startStop);
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_cwiczenie);
        wpisywaniePowtorzen();
        /*Stopwatch stopwatch = new Stopwatch();
        stopwatch.setTextView(czas);
        startStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopwatch.start();
            }
        });*/
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

    public void wpisywaniePowtorzen(){
        ops = (TextView) findViewById(R.id.ops);
        nazwaCwiczenia = (TextView) findViewById(R.id.nazwaCwiczenia);
        String ustawione = "Obciążenie: " + loadObciazenie() + "\nPowtórzenia: " + loadPowtorzenia() + "\nSerie: " + loadSerie();
        ops.setText(ustawione);
        nazwaCwiczenia.setText(loadNazwa());
    }
}