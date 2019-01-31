package com.example.williamrudwall.dogodex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    private String TEXT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Deklarering av knappar och textfält
        Button doneBtn = (Button) findViewById(R.id.doneBtn);
        final EditText dogNameEditText = (EditText) findViewById(R.id.dogNameEditText);
        final EditText trainerNameEditText = (EditText) findViewById(R.id.trainerNameEditText);
        final Spinner dogRaceSpinner = (Spinner) findViewById(R.id.dogRaceSpinner);
        final RadioGroup genderGroup = (RadioGroup) findViewById(R.id.dogGenderRadioGroup);

        // Listan för alternativen i dropdownlistan
        ArrayList<String> dogRaceListSpinner = new ArrayList<String>();
        dogRaceListSpinner.add("Border Collie");
        dogRaceListSpinner.add("Spanish Water Dog");
        dogRaceListSpinner.add("Shetland Sheepdog");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dogRaceListSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dogRaceSpinner.setAdapter(adapter);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        // Test för att se om värdet sparades i den här vyn när man startade om appen
        TextView testView = (TextView) findViewById(R.id.testTrainer);
        testView.setText(sharedPreferences.getString("trainerName", TEXT));

        // DONE BUTTON
       doneBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editor.putString("trainerName", trainerNameEditText.getText().toString());
               editor.putString("dogName", dogNameEditText.getText().toString());
               int genderId = genderGroup.getCheckedRadioButtonId();
               RadioButton selectedGender = (RadioButton) findViewById(genderId);
               if(selectedGender != null) {
                   String dogGender = selectedGender.getText().toString();
                   editor.putString("dogGender", dogGender);
               }
               editor.putString("dogRace", dogRaceSpinner.getSelectedItem().toString());
               editor.putBoolean("registered", true);
               editor.apply();
               Intent startIntent = new Intent(getApplicationContext(), ConfirmationRegistrationScreen.class);
               startActivity(startIntent);
           }
       });

        // En toast som bekräftar att man har registrerat
        Context context = getApplicationContext();
        CharSequence text = "REGISTRED!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}