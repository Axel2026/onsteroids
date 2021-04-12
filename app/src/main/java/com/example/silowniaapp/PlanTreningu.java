package com.example.silowniaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlanTreningu extends AppCompatActivity {

    int[] idy = {R.id.przycisk1, R.id.przycisk2, R.id.przycisk3, R.id.przycisk4, R.id.przycisk5, R.id.przycisk6, R.id.przycisk7, R.id.przycisk8, R.id.przycisk9, R.id.przycisk10, R.id.przycisk11, R.id.przycisk12, R.id.przycisk13, R.id.przycisk14, R.id.przycisk15, R.id.przycisk16,
            R.id.przycisk17, R.id.przycisk18, R.id.przycisk19, R.id.przycisk20, R.id.przycisk21, R.id.przycisk22, R.id.przycisk23, R.id.przycisk24, R.id.przycisk25, R.id.przycisk26, R.id.przycisk27, R.id.przycisk28, R.id.przycisk29, R.id.przycisk30 };
    int[] idytv = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10, R.id.tv11, R.id.tv12, R.id.tv13, R.id.tv14, R.id.tv15, R.id.tv16,
            R.id.tv17, R.id.tv18, R.id.tv19, R.id.tv20, R.id.tv21, R.id.tv22, R.id.tv23, R.id.tv24, R.id.tv25, R.id.tv26, R.id.tv27, R.id.tv28, R.id.tv29, R.id.tv30 };
    String liczbaCwiczen;
    String[] tablicaCwiczen;
    String[][] seriePowtorzenia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_plan_treningu);

        try {
            czytanieJsona();
            tworzeniePrzyciskow(Integer.parseInt(liczbaCwiczen));
/*            for(int i =0; i < tablicaCwiczen.length; i++){
                System.out.println(tablicaCwiczen[i]);
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void tworzeniePrzyciskow(int ilosc){
        ScrollView sv2 = new ScrollView(this);
        //Tutaj zmieniaj wygląd ekranu, sv to nasz layout, więc jego edytuj tu. Linijkę niżej jest zmiana jego tła.
        sv2.setBackgroundColor(getResources().getColor(R.color.tlo));
        LinearLayout ll3 = new LinearLayout(this);
        //LinearLayout ll2 = new LinearLayout(this);
        ll3.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = RelativeLayout.ALIGN_PARENT_LEFT;
        ll3.setLayoutParams(layoutParams);
/*        ll2.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = RelativeLayout.ALIGN_PARENT_RIGHT;*/
        //ll2.setLayoutParams(layoutParams2);
        sv2.addView(ll3);
        //ll3.addView(ll2);

/*        for(int j = 0; j < Integer.parseInt(liczbaCwiczen); j++){
            TextView info = new TextView(PlanTreningu.this);
            info.setId(idytv[j]);
            String t = "O: " + seriePowtorzenia[j][0] + " S: " + seriePowtorzenia[j][1] + " P: " + seriePowtorzenia[j][2];
            info.setText(t);
            ll3.addView(info);
        }*/

        for(int i = 0; i < ilosc; i++) {
            LinearLayout row = new LinearLayout(this);
            for (int j = 0; j < 1; j++) {
                Button button = new Button(this);
                String t = "O: " + seriePowtorzenia[j][0] + " S: " + seriePowtorzenia[j][1] + " P: " + seriePowtorzenia[j][2];
                button.setText(tablicaCwiczen[i] + "  " +t);
                button.setId(idy[i]);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                button.setLayoutParams (new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT));
                row.addView(button);
            }
            ll3.addView(row);
        }

/*        Button ok = new Button(this);
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
        ll.addView(ok);*/
        this.setContentView(sv2);
    }

    public void czytanieJsona() throws JSONException {
        try {
            //Otwieranie i sczytywanie pliku login.json
            FileInputStream fis = PlanTreningu.this.openFileInput(loadNick() + ".json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            liczbaCwiczen = jsonObject.getString("liczba ćwiczeń");
            JSONArray arr = jsonObject.getJSONArray("ćwiczenia");
            List<String> listaCwiczen = new ArrayList<String>();
            for (int i = 0; i < arr.length(); i++) {
                listaCwiczen.add(arr.getString(i));
            }

            tablicaCwiczen = new String[Integer.parseInt(liczbaCwiczen)];
            int j = 0;
            for (int i = 0; i < listaCwiczen.size(); i++) {
                if (!listaCwiczen.get(i).equals("null")) {
                    tablicaCwiczen[j] = listaCwiczen.get(i);
                    j++;
                }
            }

            //Otwieranie i sczytywanie pliku login_cwiczenia.json
            FileInputStream fis2 = PlanTreningu.this.openFileInput(loadNick() + "_cwiczenia" + ".json");
            int size2 = fis2.available();
            byte[] buffer2 = new byte[size2];
            fis2.read(buffer2);
            fis2.close();
            String json2 = new String(buffer2, "UTF-8");
            JSONObject jsonObject2 = new JSONObject(json2);
            seriePowtorzenia = new String[Integer.parseInt(liczbaCwiczen)][3];
            for(int o = 0; o < Integer.parseInt(liczbaCwiczen); o++){
                JSONArray arr2 = jsonObject2.getJSONArray(tablicaCwiczen[o]);
                seriePowtorzenia[o][0] = arr2.getString(0);
                seriePowtorzenia[o][1] = arr2.getString(1);
                seriePowtorzenia[o][2] = arr2.getString(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}