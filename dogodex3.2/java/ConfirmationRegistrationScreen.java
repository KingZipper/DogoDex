package com.example.williamrudwall.dogodex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

// Klass för att bekräfta sin registrering

public class ConfirmationRegistrationScreen extends AppCompatActivity {

    private String trainerName;
    private String dogName;
    private String dogRace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_registration_screen);
        loadData();
        // Fortsätt knappen
        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(startIntent);
            }
        });
    }

    // Laddar datan med shared preferences och visar en text av ens registrering
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationActivity.SHARED_PREFS, MODE_PRIVATE);
        TextView tw = (TextView) findViewById(R.id.displayTextView);
        trainerName = sharedPreferences.getString("trainerName", trainerName);
        dogName = sharedPreferences.getString("dogName", dogName);
        dogRace = sharedPreferences.getString("dogRace", dogRace);
        tw.setText("Hello and Welcome " +
            trainerName + "\n" + "We are happy that you and " +
            dogName + " have joined the community. Since your dog is a " +
            dogRace + " we have some recommendations for you, " +
                "that you perhaps already knew about." +
            "\n" + "Feel free to explore!");

         }

}
