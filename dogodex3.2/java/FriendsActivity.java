package com.example.williamrudwall.dogodex;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.williamrudwall.dogodex.NotificationClass.CHANNEL_1_ID;

public class FriendsActivity extends AppCompatActivity {

    private Button notifyButton, friendButton, startVibrate, stopVibrate;
    private EditText messageText;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);



        notificationManager = NotificationManagerCompat.from(this);
        messageText = (EditText) findViewById(R.id.editTextMessage);
        notifyButton = (Button) findViewById(R.id.notifyButton);
        friendButton = (Button) findViewById(R.id.friendButton);
        startVibrate = (Button) findViewById(R.id.vibrateButton);
        stopVibrate = (Button) findViewById(R.id.stopVibrateButton);

        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        final long[] pattern = {2000, 1000};

        //Startar vibrationen
        startVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        vibrator.vibrate(pattern, 0);
            }
        });

        // Stannar vibrationen
        stopVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
            }
        });


        //Knapp för att skicka notis
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sendOnChannel1(v);
            }
        });

        //Knapp för att öppna en dialog
        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openDialog();
            }
        });



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.newsIcon:
                        Intent intent = new Intent(FriendsActivity.this, NewsActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profileIcon:
                        Intent intent2 = new Intent(FriendsActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.mapIcon:
                        Intent intent3 = new Intent(FriendsActivity.this, MapActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.friendsIcon:
                        break;
                    case R.id.settingsIcon:
                        Intent intent5 = new Intent(FriendsActivity.this, InfoActivity.class);
                        startActivity(intent5);
                        finish();
                        break;

                }



                return false;
            }
        });

    }

        //Skapar en notis
    public void sendOnChannel1(View v){
    String title = "DOGODEX";
    String message = messageText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
            .setSmallIcon(R.drawable.ic_pets)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .build();
        notificationManager.notify(1, notification);

        Toast.makeText(this, "Yay you made an dogofication", Toast.LENGTH_SHORT).show();
    }

    //Skapar och visar dialogen
    public void openDialog(){
        DialogClass dialogClass = new DialogClass();
        dialogClass.show(getSupportFragmentManager(), "Dialog");
    }


}
