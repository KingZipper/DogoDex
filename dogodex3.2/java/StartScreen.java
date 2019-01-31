package com.example.williamrudwall.dogodex;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

//Klass för startskärmen

public class StartScreen extends AppCompatActivity {

    private final int ACCESS_NETWORK_STATE_CODE = 1;
    private boolean registered;
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        //Kollar efter användarens tillåtelse och Printar en toast ifall det finns/inte finns internet
        if(checkPermission(Manifest.permission.ACCESS_NETWORK_STATE)){
            if (checkForNetwork()){
                Toast.makeText(this, "YOU HAVE INTERNET", Toast.LENGTH_SHORT).show();
            } else if (!checkForNetwork()){
                Toast.makeText(this, "NO INTERNET", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, ACCESS_NETWORK_STATE_CODE);
        }


        //Startskärmen som visar bild och en progressbar så att det simulerar att appen laddas.
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
                mProgressBar.setVisibility(View.VISIBLE);
                SharedPreferences sharedPreferences = getSharedPreferences(RegistrationActivity.SHARED_PREFS, MODE_PRIVATE);
                registered = sharedPreferences.getBoolean("registered", registered);
                if(registered) {
                Intent homeIntent = new Intent(StartScreen.this, NewsActivity.class);
                startActivity(homeIntent);
                finish();
            } else
                if(!registered){
                Intent registrationIntent = new Intent(StartScreen.this, RegistrationActivity.class);
                startActivity(registrationIntent);
                finish();
            }

            }
        }, SPLASH_TIME_OUT);
    }

    //Kollar om det finns internet
    private boolean checkForNetwork(){
        boolean have_wifi = false;
        boolean have_mobileData = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info:networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {

                if (info.isConnected()) {
                    have_wifi = true;
                }

            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected()) {
                    have_mobileData = true;
                }

            }
        }
        return have_wifi ||have_mobileData;

    }

    // Metod för att kolla tillåtelse
    private boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}
