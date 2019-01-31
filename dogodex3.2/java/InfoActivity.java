package com.example.williamrudwall.dogodex;


import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class InfoActivity extends YouTubeBaseActivity {


    private YouTubePlayerView wYTPV;
    private Button pupButton, basicButton, insuranceButton, foodButton;
    private YouTubePlayer.OnInitializedListener wOIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        foodButton = (Button) findViewById(R.id.foodButton);
        insuranceButton = (Button) findViewById(R.id.insuranceButton);
        basicButton = (Button) findViewById(R.id.basicButton);
        pupButton = (Button) findViewById(R.id.puppyButton);
        wYTPV = (YouTubePlayerView)findViewById(R.id.youtubePlayer);


        // Knapp för att öppna en webblänk
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webView = new WebView(InfoActivity.this);
                setContentView(webView);
                webView.loadUrl("https://www.zooplus.se/shop/hund/hundmat_torrfoder/");
            }
        });

        // Knapp för att öppna en webblänk
        insuranceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebView webView = new WebView(InfoActivity.this);
                setContentView(webView);
                webView.loadUrl("https://www.agria.se/hund/hundforsakring/");
            }
        });

        //Spelar upp en youtube video i appen
        // Tycker denna funktionen är smidigare att använda än att öppna upp youtube appen för att spela en video.
        wOIL = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    youTubePlayer.loadVideo("Ro0KH6hAnHA");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        //Knapp för att spela upp en youtube video i appen
        pupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wYTPV.initialize(YoutubeConfig.getApiKey(),wOIL);
            }
        });

        // Knapp för att starta youtube appen och spela upp videon där
        // Använde en intent också ifall det skulle vara ett krav.
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + "jFMA5ggFsXU"));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.newsIcon:
                        Intent intent = new Intent(InfoActivity.this, NewsActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profileIcon:
                        Intent intent2 = new Intent(InfoActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.mapIcon:
                        Intent intent3 = new Intent(InfoActivity.this, MapActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.friendsIcon:
                        Intent intent4 = new Intent(InfoActivity.this, FriendsActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.settingsIcon:
                        break;

                }
                return false;
            }
        });

    }
}
