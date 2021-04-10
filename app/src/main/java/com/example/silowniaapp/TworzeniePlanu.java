package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TworzeniePlanu extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    String[] exerciseArr = new String[24];
    Context c = TworzeniePlanu.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_tworzenie_planu);


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

        final Spinner spinner = (Spinner)findViewById(R.id.wybierzParieCiala);
        String[] elementy = {"Wszystko","Klatka piersiowa", "Biceps", "Triceps", "Barki", "Nogi"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_item, elementy);
        Button stworzPlan = findViewById(R.id.przyciskOk);

        stworzPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cwiczenieKlata1.isChecked()) {
                    exerciseArr[0] = "Wyciskanie sztangi na ławeczce";
                }
                if (cwiczenieKlata2.isChecked()) {
                    exerciseArr[1] = "Wyciskanie sztangi na ławeczce ukośnej";
                }
                if (cwiczenieKlata3.isChecked()) {
                    exerciseArr[2] = "Wyciskanie hantli leżąc";
                }
                if (cwiczenieKlata4.isChecked()) {
                    exerciseArr[3] = "Rozpiętki na bramie";
                }
                if (cwiczenieKlata5.isChecked()) {
                    exerciseArr[4] = "Rozpiętki na ławeczce";
                }
                if (cwiczenieKlata6.isChecked()) {
                    exerciseArr[5] = "Wyciskanie na maszynie Smitha";
                }
                if (cwiczenieBiceps1.isChecked()) {
                    exerciseArr[6] = "Uginanie ramion - hantle";
                }
                if (cwiczenieBiceps2.isChecked()) {
                    exerciseArr[7] = "Uginanie ramion - sztanga krzywa";
                }
                if (cwiczenieBiceps3.isChecked()) {
                    exerciseArr[8] = "Uginanie ramion - sztanga prosta";
                }
                if (cwiczenieBiceps4.isChecked()) {
                    exerciseArr[9] = " \"Modlitewnik\" ";
                }
                if (cwiczenieBiceps5.isChecked()) {
                    exerciseArr[10] = "Uginanie ramion - brama sznurki";
                }
                if (cwiczenieBiceps6.isChecked()) {
                    exerciseArr[11] = "Podciąganie podchwytem";
                }
                if (cwiczenieTriceps1.isChecked()) {
                    exerciseArr[12] = "Wyciskanie francuskie";
                }
                if (cwiczenieTriceps2.isChecked()) {
                    exerciseArr[13] = "Prostowanie ramion - sznurki";
                }
                if (cwiczenieTriceps3.isChecked()) {
                    exerciseArr[14] = "Prostowanie ramion - metalowy uchwyt";
                }
                if (cwiczenieTriceps4.isChecked()) {
                    exerciseArr[15] = "Wyciskanie francuskie hantlami";
                }
                if (cwiczenieTriceps5.isChecked()) {
                    exerciseArr[16] = "Dipy";
                }
                if (cwiczenieTriceps6.isChecked()) {
                    exerciseArr[17] = "Prostowanie ramion w opadzie tułowia";
                }
                if (cwiczenieBarki1.isChecked()) {
                    exerciseArr[18] = "Wyciskanie żołnierskie";
                }
                if (cwiczenieBarki2.isChecked()) {
                    exerciseArr[19] = "Unoszenie hantli bokiem";
                }
                if (cwiczenieBarki3.isChecked()) {
                    exerciseArr[20] = "Unoszenie hantli przodem";
                }
                if (cwiczenieBarki4.isChecked()) {
                    exerciseArr[21] = "Maszyna 'Butterfly'";
                }
                if (cwiczenieBarki5.isChecked()) {
                    exerciseArr[22] = "Odwrotne rozpiętki na bramie";
                }
                if (cwiczenieBarki6.isChecked()) {
                    exerciseArr[23] = "Wyciskanie hantli w górę";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[24] = "Przysiady ze sztangą";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[25] = "Wspięcia na palce na maszynie";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[26] = "Prostowanie nóg na maszynie izotonicznej";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[27] = "Przysiady na maszynie półwolnej";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[28] = "Uginanie nóg na maszynie leżąc";
                }
                if (cwiczenieNogi1.isChecked()) {
                    exerciseArr[29] = "Uginanie nóg na maszynie siedząc";
                }

                try {
                    TworzenieJsona();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                //saveArrayData(exerciseArr, "listacwiczen", c);
                Intent intentNowyUzytkownik = new Intent(TworzeniePlanu.this, TworzeniePlanu2.class);
                startActivity(intentNowyUzytkownik);
            }
        });

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int id, long position) {


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

    public void TworzenieJsona() throws IOException, JSONException {

            int licznik = 0;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nazwa", loadNick());
            JSONArray jsArray = new JSONArray();
            for (int i = 0; i < exerciseArr.length; i++) {
                licznik += exerciseArr[i]==null ? 0 : 1;
                jsArray.put(exerciseArr[i]);
            }
            jsonObject.put("ćwiczenia", jsArray);
            jsonObject.put("liczba ćwiczeń", licznik);
            String userString = jsonObject.toString();
            String fileName = loadNick() + ".json";
            File file = new File(c.getFilesDir(), fileName);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(userString);
            bufferedWriter.close();
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }

    public boolean saveArrayData(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName +" _size ", array.length);
        for(int i=0 ; i<array.length ; i++){
            editor.putString(arrayName + "_ " + i, array[i]);
        }
        return editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
