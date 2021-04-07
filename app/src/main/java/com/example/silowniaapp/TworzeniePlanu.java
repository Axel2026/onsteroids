package com.example.silowniaapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TworzeniePlanu extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_tworzenie_planu_layout);

        RadioButton cwiczenieKlata1 = findViewById(R.id.cwiczenieKlata1);
        RadioButton cwiczenieKlata2 = findViewById(R.id.cwiczenieKlata2);
        RadioButton cwiczenieKlata3 = findViewById(R.id.cwiczenieKlata3);
        RadioButton cwiczenieKlata4 = findViewById(R.id.cwiczenieKlata4);
        RadioButton cwiczenieKlata5 = findViewById(R.id.cwiczenieKlata5);
        RadioButton cwiczenieKlata6 = findViewById(R.id.cwiczenieKlata6);

        RadioButton cwiczenieBiceps1 = findViewById(R.id.cwiczenieBiceps1);
        RadioButton cwiczenieBiceps2 = findViewById(R.id.cwiczenieBiceps2);
        RadioButton cwiczenieBiceps3 = findViewById(R.id.cwiczenieBiceps3);
        RadioButton cwiczenieBiceps4 = findViewById(R.id.cwiczenieBiceps4);
        RadioButton cwiczenieBiceps5 = findViewById(R.id.cwiczenieBiceps5);
        RadioButton cwiczenieBiceps6 = findViewById(R.id.cwiczenieBiceps6);

        RadioButton cwiczenieTriceps1 = findViewById(R.id.cwiczenieTriceps1);
        RadioButton cwiczenieTriceps2 = findViewById(R.id.cwiczenieTriceps2);
        RadioButton cwiczenieTriceps3 = findViewById(R.id.cwiczenieTriceps3);
        RadioButton cwiczenieTriceps4 = findViewById(R.id.cwiczenieTriceps4);
        RadioButton cwiczenieTriceps5 = findViewById(R.id.cwiczenieTriceps5);
        RadioButton cwiczenieTriceps6 = findViewById(R.id.cwiczenieTriceps6);

        RadioButton cwiczenieBarki1 = findViewById(R.id.cwiczenieBarki1);
        RadioButton cwiczenieBarki2 = findViewById(R.id.cwiczenieBarki2);
        RadioButton cwiczenieBarki3 = findViewById(R.id.cwiczenieBarki3);
        RadioButton cwiczenieBarki4 = findViewById(R.id.cwiczenieBarki4);
        RadioButton cwiczenieBarki5 = findViewById(R.id.cwiczenieBarki5);
        RadioButton cwiczenieBarki6 = findViewById(R.id.cwiczenieBarki6);

        RadioButton cwiczenieNogi1 = findViewById(R.id.cwiczenieNogi1);
        RadioButton cwiczenieNogi2 = findViewById(R.id.cwiczenieNogi2);
        RadioButton cwiczenieNogi3 = findViewById(R.id.cwiczenieNogi3);
        RadioButton cwiczenieNogi4 = findViewById(R.id.cwiczenieNogi4);
        RadioButton cwiczenieNogi5 = findViewById(R.id.cwiczenieNogi5);
        RadioButton cwiczenieNogi6 = findViewById(R.id.cwiczenieNogi6);



            //LINKI DO SPINNERA
            //https://www.youtube.com/watch?v=urQp7KsQhW8
            //https://androiddlaprogramistow.wordpress.com/2013/11/07/spinner-rozwijalna-lista-do-wyboru-jednej-opcji/
            //https://www.youtube.com/watch?v=urQp7KsQhW8
//        Spinner wybierzParieCiala = (Spinner) findViewById(R.id.wybierzParieCiala);
//
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TworzeniePlanu.this,
//                R.layout.spinner_item, getResources().getStringArray(R.array.parieCiala));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        wybierzParieCiala.setAdapter(myAdapter);

        final Spinner spinner = (Spinner)findViewById(R.id.wybierzParieCiala);
        String[] elementy = {"Wszystko","Klatka piersiowa", "Biceps", "Triceps", "Barki", "Nogi"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_item, elementy);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {

                //Toast.makeText(TworzeniePlanu.this, "Wybrano opcję" + (id), Toast.LENGTH_SHORT).show();

                switch((int)position)
                {
                    case 0:
                        cwiczenieKlata1.setVisibility(View.VISIBLE);
                        cwiczenieKlata2.setVisibility(View.VISIBLE);
                        cwiczenieKlata3.setVisibility(View.VISIBLE);
                        cwiczenieKlata4.setVisibility(View.VISIBLE);
                        cwiczenieKlata5.setVisibility(View.VISIBLE);
                        cwiczenieKlata6.setVisibility(View.VISIBLE);

                        cwiczenieBiceps1.setVisibility(View.VISIBLE);
                        cwiczenieBiceps2.setVisibility(View.VISIBLE);
                        cwiczenieBiceps3.setVisibility(View.VISIBLE);
                        cwiczenieBiceps4.setVisibility(View.VISIBLE);
                        cwiczenieBiceps5.setVisibility(View.VISIBLE);
                        cwiczenieBiceps6.setVisibility(View.VISIBLE);

                        cwiczenieTriceps1.setVisibility(View.VISIBLE);
                        cwiczenieTriceps2.setVisibility(View.VISIBLE);
                        cwiczenieTriceps3.setVisibility(View.VISIBLE);
                        cwiczenieTriceps4.setVisibility(View.VISIBLE);
                        cwiczenieTriceps5.setVisibility(View.VISIBLE);
                        cwiczenieTriceps6.setVisibility(View.VISIBLE);

                        cwiczenieBarki1.setVisibility(View.VISIBLE);
                        cwiczenieBarki2.setVisibility(View.VISIBLE);
                        cwiczenieBarki3.setVisibility(View.VISIBLE);
                        cwiczenieBarki4.setVisibility(View.VISIBLE);
                        cwiczenieBarki5.setVisibility(View.VISIBLE);
                        cwiczenieBarki6.setVisibility(View.VISIBLE);

                        cwiczenieNogi1.setVisibility(View.VISIBLE);
                        cwiczenieNogi2.setVisibility(View.VISIBLE);
                        cwiczenieNogi3.setVisibility(View.VISIBLE);
                        cwiczenieNogi4.setVisibility(View.VISIBLE);
                        cwiczenieNogi5.setVisibility(View.VISIBLE);
                        cwiczenieNogi6.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        cwiczenieKlata1.setVisibility(View.VISIBLE);
                        cwiczenieKlata2.setVisibility(View.VISIBLE);
                        cwiczenieKlata3.setVisibility(View.VISIBLE);
                        cwiczenieKlata4.setVisibility(View.VISIBLE);
                        cwiczenieKlata5.setVisibility(View.VISIBLE);
                        cwiczenieKlata6.setVisibility(View.VISIBLE);

                        cwiczenieBiceps1.setVisibility(View.GONE);
                        cwiczenieBiceps2.setVisibility(View.GONE);
                        cwiczenieBiceps3.setVisibility(View.GONE);
                        cwiczenieBiceps4.setVisibility(View.GONE);
                        cwiczenieBiceps5.setVisibility(View.GONE);
                        cwiczenieBiceps6.setVisibility(View.GONE);

                        cwiczenieTriceps1.setVisibility(View.GONE);
                        cwiczenieTriceps2.setVisibility(View.GONE);
                        cwiczenieTriceps3.setVisibility(View.GONE);
                        cwiczenieTriceps4.setVisibility(View.GONE);
                        cwiczenieTriceps5.setVisibility(View.GONE);
                        cwiczenieTriceps6.setVisibility(View.GONE);

                        cwiczenieBarki1.setVisibility(View.GONE);
                        cwiczenieBarki2.setVisibility(View.GONE);
                        cwiczenieBarki3.setVisibility(View.GONE);
                        cwiczenieBarki4.setVisibility(View.GONE);
                        cwiczenieBarki5.setVisibility(View.GONE);
                        cwiczenieBarki6.setVisibility(View.GONE);

                        cwiczenieNogi1.setVisibility(View.GONE);
                        cwiczenieNogi2.setVisibility(View.GONE);
                        cwiczenieNogi3.setVisibility(View.GONE);
                        cwiczenieNogi4.setVisibility(View.GONE);
                        cwiczenieNogi5.setVisibility(View.GONE);
                        cwiczenieNogi6.setVisibility(View.GONE);
                        break;
                    case 2:

                        cwiczenieKlata1.setVisibility(View.GONE);
                        cwiczenieKlata2.setVisibility(View.GONE);
                        cwiczenieKlata3.setVisibility(View.GONE);
                        cwiczenieKlata4.setVisibility(View.GONE);
                        cwiczenieKlata5.setVisibility(View.GONE);
                        cwiczenieKlata6.setVisibility(View.GONE);

                        cwiczenieBiceps1.setVisibility(View.VISIBLE);
                        cwiczenieBiceps2.setVisibility(View.VISIBLE);
                        cwiczenieBiceps3.setVisibility(View.VISIBLE);
                        cwiczenieBiceps4.setVisibility(View.VISIBLE);
                        cwiczenieBiceps5.setVisibility(View.VISIBLE);
                        cwiczenieBiceps6.setVisibility(View.VISIBLE);

                        cwiczenieTriceps1.setVisibility(View.GONE);
                        cwiczenieTriceps2.setVisibility(View.GONE);
                        cwiczenieTriceps3.setVisibility(View.GONE);
                        cwiczenieTriceps4.setVisibility(View.GONE);
                        cwiczenieTriceps5.setVisibility(View.GONE);
                        cwiczenieTriceps6.setVisibility(View.GONE);

                        cwiczenieBarki1.setVisibility(View.GONE);
                        cwiczenieBarki2.setVisibility(View.GONE);
                        cwiczenieBarki3.setVisibility(View.GONE);
                        cwiczenieBarki4.setVisibility(View.GONE);
                        cwiczenieBarki5.setVisibility(View.GONE);
                        cwiczenieBarki6.setVisibility(View.GONE);

                        cwiczenieNogi1.setVisibility(View.GONE);
                        cwiczenieNogi2.setVisibility(View.GONE);
                        cwiczenieNogi3.setVisibility(View.GONE);
                        cwiczenieNogi4.setVisibility(View.GONE);
                        cwiczenieNogi5.setVisibility(View.GONE);
                        cwiczenieNogi6.setVisibility(View.GONE);

                        break;
                    case 3:
                        cwiczenieKlata1.setVisibility(View.GONE);
                        cwiczenieKlata2.setVisibility(View.GONE);
                        cwiczenieKlata3.setVisibility(View.GONE);
                        cwiczenieKlata4.setVisibility(View.GONE);
                        cwiczenieKlata5.setVisibility(View.GONE);
                        cwiczenieKlata6.setVisibility(View.GONE);

                        cwiczenieBiceps1.setVisibility(View.GONE);
                        cwiczenieBiceps2.setVisibility(View.GONE);
                        cwiczenieBiceps3.setVisibility(View.GONE);
                        cwiczenieBiceps4.setVisibility(View.GONE);
                        cwiczenieBiceps5.setVisibility(View.GONE);
                        cwiczenieBiceps6.setVisibility(View.GONE);

                        cwiczenieTriceps1.setVisibility(View.VISIBLE);
                        cwiczenieTriceps2.setVisibility(View.VISIBLE);
                        cwiczenieTriceps3.setVisibility(View.VISIBLE);
                        cwiczenieTriceps4.setVisibility(View.VISIBLE);
                        cwiczenieTriceps5.setVisibility(View.VISIBLE);
                        cwiczenieTriceps6.setVisibility(View.VISIBLE);

                        cwiczenieBarki1.setVisibility(View.GONE);
                        cwiczenieBarki2.setVisibility(View.GONE);
                        cwiczenieBarki3.setVisibility(View.GONE);
                        cwiczenieBarki4.setVisibility(View.GONE);
                        cwiczenieBarki5.setVisibility(View.GONE);
                        cwiczenieBarki6.setVisibility(View.GONE);

                        cwiczenieNogi1.setVisibility(View.GONE);
                        cwiczenieNogi2.setVisibility(View.GONE);
                        cwiczenieNogi3.setVisibility(View.GONE);
                        cwiczenieNogi4.setVisibility(View.GONE);
                        cwiczenieNogi5.setVisibility(View.GONE);
                        cwiczenieNogi6.setVisibility(View.GONE);

                        break;
                    case 4:
                        cwiczenieKlata1.setVisibility(View.GONE);
                        cwiczenieKlata2.setVisibility(View.GONE);
                        cwiczenieKlata3.setVisibility(View.GONE);
                        cwiczenieKlata4.setVisibility(View.GONE);
                        cwiczenieKlata5.setVisibility(View.GONE);
                        cwiczenieKlata6.setVisibility(View.GONE);

                        cwiczenieBiceps1.setVisibility(View.GONE);
                        cwiczenieBiceps2.setVisibility(View.GONE);
                        cwiczenieBiceps3.setVisibility(View.GONE);
                        cwiczenieBiceps4.setVisibility(View.GONE);
                        cwiczenieBiceps5.setVisibility(View.GONE);
                        cwiczenieBiceps6.setVisibility(View.GONE);

                        cwiczenieTriceps1.setVisibility(View.GONE);
                        cwiczenieTriceps2.setVisibility(View.GONE);
                        cwiczenieTriceps3.setVisibility(View.GONE);
                        cwiczenieTriceps4.setVisibility(View.GONE);
                        cwiczenieTriceps5.setVisibility(View.GONE);
                        cwiczenieTriceps6.setVisibility(View.GONE);

                        cwiczenieBarki1.setVisibility(View.VISIBLE);
                        cwiczenieBarki2.setVisibility(View.VISIBLE);
                        cwiczenieBarki3.setVisibility(View.VISIBLE);
                        cwiczenieBarki4.setVisibility(View.VISIBLE);
                        cwiczenieBarki5.setVisibility(View.VISIBLE);
                        cwiczenieBarki6.setVisibility(View.VISIBLE);

                        cwiczenieNogi1.setVisibility(View.GONE);
                        cwiczenieNogi2.setVisibility(View.GONE);
                        cwiczenieNogi3.setVisibility(View.GONE);
                        cwiczenieNogi4.setVisibility(View.GONE);
                        cwiczenieNogi5.setVisibility(View.GONE);
                        cwiczenieNogi6.setVisibility(View.GONE);

                        break;
                    case 5:
                        cwiczenieKlata1.setVisibility(View.GONE);
                        cwiczenieKlata2.setVisibility(View.GONE);
                        cwiczenieKlata3.setVisibility(View.GONE);
                        cwiczenieKlata4.setVisibility(View.GONE);
                        cwiczenieKlata5.setVisibility(View.GONE);
                        cwiczenieKlata6.setVisibility(View.GONE);

                        cwiczenieBiceps1.setVisibility(View.GONE);
                        cwiczenieBiceps2.setVisibility(View.GONE);
                        cwiczenieBiceps3.setVisibility(View.GONE);
                        cwiczenieBiceps4.setVisibility(View.GONE);
                        cwiczenieBiceps5.setVisibility(View.GONE);
                        cwiczenieBiceps6.setVisibility(View.GONE);

                        cwiczenieTriceps1.setVisibility(View.GONE);
                        cwiczenieTriceps2.setVisibility(View.GONE);
                        cwiczenieTriceps3.setVisibility(View.GONE);
                        cwiczenieTriceps4.setVisibility(View.GONE);
                        cwiczenieTriceps5.setVisibility(View.GONE);
                        cwiczenieTriceps6.setVisibility(View.GONE);

                        cwiczenieBarki1.setVisibility(View.GONE);
                        cwiczenieBarki2.setVisibility(View.GONE);
                        cwiczenieBarki3.setVisibility(View.GONE);
                        cwiczenieBarki4.setVisibility(View.GONE);
                        cwiczenieBarki5.setVisibility(View.GONE);
                        cwiczenieBarki6.setVisibility(View.GONE);

                        cwiczenieNogi1.setVisibility(View.VISIBLE);
                        cwiczenieNogi2.setVisibility(View.VISIBLE);
                        cwiczenieNogi3.setVisibility(View.VISIBLE);
                        cwiczenieNogi4.setVisibility(View.VISIBLE);
                        cwiczenieNogi5.setVisibility(View.VISIBLE);
                        cwiczenieNogi6.setVisibility(View.VISIBLE);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
