package com.example.williamrudwall.dogodex;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private String trainerName;
    private String dogName;
    private String dogRace;
    private Button copyButton, pasteButton;
    private EditText copyText;
    private TextView pasteText, nameTextView, dogNameTextView, dogRaceTextView;
    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        nameTextView = (TextView) findViewById(R.id.profileTrainerName);
        dogNameTextView = (TextView) findViewById(R.id.profileDogName);
        dogRaceTextView = (TextView) findViewById(R.id.profileDogRace);
        pasteText = (TextView) findViewById(R.id.textPaste);
        copyText = (EditText) findViewById(R.id.textCopy);
        copyButton = (Button) findViewById(R.id.copyButton);
        pasteButton = (Button) findViewById(R.id.pasteButton);
        clipboardManager = (ClipboardManager)  getSystemService(CLIPBOARD_SERVICE);

        //Kollar om man har kopierat något
        if(!clipboardManager.hasPrimaryClip()){
            pasteButton.setEnabled(false);
        }

        // Kopieringsknapp
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = copyText.getText().toString();

                if(!text.equals("")){
                    ClipData clipData = ClipData.newPlainText("text", text);
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(ProfileActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    pasteButton.setEnabled(true);
                }

            }
        });

        // Klistra in knapp
        pasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);

                pasteText.setText("Favourite snack: " + item.getText().toString());
                Toast.makeText(ProfileActivity.this, "Pasted", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.newsIcon:
                        Intent intent = new Intent(ProfileActivity.this, NewsActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profileIcon:
                        break;
                    case R.id.mapIcon:
                        Intent intent3 = new Intent(ProfileActivity.this, MapActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.friendsIcon:
                        Intent intent4 = new Intent(ProfileActivity.this, FriendsActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.settingsIcon:
                        Intent intent5 = new Intent(ProfileActivity.this, InfoActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    //Laddar in datan från registreringen till profilen
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(RegistrationActivity.SHARED_PREFS, MODE_PRIVATE);

        trainerName = sharedPreferences.getString("trainerName", trainerName);
        dogName = sharedPreferences.getString("dogName", dogName);
        dogRace = sharedPreferences.getString("dogRace", dogRace);

        nameTextView.setText("Trainer: " + trainerName);
        dogNameTextView.setText("Dog: " + dogName);
        dogRaceTextView.setText("Race: " + dogRace);
    }
}

