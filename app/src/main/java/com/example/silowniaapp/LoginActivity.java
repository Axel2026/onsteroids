package com.example.silowniaapp;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ekran_logowania_layout);

        EditText login = findViewById(R.id.yourLogin);
        login.setText(loadNick());


        Button button = findViewById(R.id.setLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nick = login.getText().toString();
                if(nick.equals("michal")) {
                    Intent intentNowyUzytkownik = new Intent(LoginActivity.this, NowyUzytkownik.class);
                    saveNick(nick);
                    intentNowyUzytkownik.putExtra("NICKNAME", nick);
                    startActivity(intentNowyUzytkownik);
                }else if(nick.equals("")){
                    String brakLoginu = "Proszę podać login!";
                    login.setText(brakLoginu);
                }else{
                    Intent intent = new Intent(LoginActivity.this, OstatnieTreningi.class);
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