package com.example.onsteroids;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_logowania);

        EditText login = findViewById(R.id.yourLogin);
        login.setText(loadNick());


        Button button = findViewById(R.id.setLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nick = login.getText().toString();
                String fileName = nick + ".json";
                File file = new File(LoginActivity.this.getFilesDir(), fileName);
                if (nick.equals("") || nick.equals(" ") || nick.equals("Login") || nick.equals("Proszę podać login!")) {
                    String brakLoginu = "Proszę podać login!";
                    login.setText(brakLoginu);
                } else if(!file.exists())  {
                    Intent intentNowyUzytkownik = new Intent(LoginActivity.this, NowyUzytkownik.class);
                    saveNick(nick);
                    intentNowyUzytkownik.putExtra("NICKNAME", nick);
                    startActivity(intentNowyUzytkownik);
                } else {
                    Intent intent = new Intent(LoginActivity.this, OstatniTrening.class);
                    intent.putExtra("NICKNAME", nick);
                    saveNick(nick);
                    startActivity(intent);
                }
            }
        });
    }

    public void saveNick(String nick) {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NICK", nick);
        editor.apply();
    }

    public String loadNick() {
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        return sharedPreferences.getString("NICK", "User");
    }
}