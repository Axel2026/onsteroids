package com.example.silowniaapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;



public class NowyUzytkownik extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.ekran_nowy_uzytkownik);

        TextView przywitanie = findViewById(R.id.przywitanie);
        String witajLogin = "Witaj " + loadNick() + ", ";
        przywitanie.setText(witajLogin);
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}
