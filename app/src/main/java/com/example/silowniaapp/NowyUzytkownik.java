package com.example.silowniaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;



public class NowyUzytkownik extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_nowy_uzytkownik_layout);

        Button stworzPlan = findViewById(R.id.stworzPlan);

        TextView przywitanie = findViewById(R.id.przywitanie);
        String witajLogin = "Witaj " + loadNick() + ", ";
        przywitanie.setText(witajLogin);


        stworzPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent stworzPlanIntent = new Intent(NowyUzytkownik.this, TworzeniePlanu.class);
                    startActivity(stworzPlanIntent);
            }
        });


    }


    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}
