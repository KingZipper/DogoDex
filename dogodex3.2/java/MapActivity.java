package com.example.williamrudwall.dogodex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button mapButton = (Button) findViewById(R.id.mapButton);

        //Knapp för att starta Google kartan
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, GoogleMapsActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.newsIcon:
                        Intent intent = new Intent(MapActivity.this, NewsActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profileIcon:
                        Intent intent2 = new Intent(MapActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.mapIcon:

                        break;
                    case R.id.friendsIcon:
                        Intent intent4 = new Intent(MapActivity.this, FriendsActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.settingsIcon:
                        Intent intent5 = new Intent(MapActivity.this, InfoActivity.class);
                        startActivity(intent5);
                        finish();
                        break;

                }



                return false;
            }
        });



    }


}
