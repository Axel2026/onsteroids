package com.example.silowniaapp;

import android.os.Bundle;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.appcompat.app.AppCompatActivity;

public class OstatniTrening extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onStart() {
        super.onStart();
        setContentView(R.layout.ekran_ostatni_trening);
    }
}
