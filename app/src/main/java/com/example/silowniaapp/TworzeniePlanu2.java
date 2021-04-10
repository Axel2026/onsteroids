package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

import org.json.*;

public class TworzeniePlanu2 extends AppCompatActivity {

    String liczbaCwiczen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_tworzenie_planu2);
        tworzeniePrzyciskow(5);
        try {
            czytanieJsona();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void tworzeniePrzyciskow(int ilosc){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                Button button = new Button(this);
                int id = i;
                button.setText("Przycisk#" + id);
                button.setId(id);
                row.addView(button);
            }
            layout.addView(row);
        }
        setContentView(layout);
    }

    public void czytanieJsona() throws JSONException {
        try {
            FileInputStream fis = TworzeniePlanu2.this.openFileInput("michal69.json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            liczbaCwiczen = jsonObject.getString("liczba ćwiczeń");
            System.out.println("Liczba ćwiczeń: " + liczbaCwiczen);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}