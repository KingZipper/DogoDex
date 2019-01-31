package com.example.williamrudwall.dogodex;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class NewsActivity extends AppCompatActivity {


    private SectionsPagerAdapter wSectionsPagerAdapter;
    private ViewPager wViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        wSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        wViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(wViewPager);

        // Sätter upp en tablayout med tabs i som man kan skifta mellan
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(wViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_videocam);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pets);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.newsIcon:
                        break;
                    case R.id.profileIcon:
                        Intent intent2 = new Intent(NewsActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.mapIcon:
                        Intent intent3 = new Intent(NewsActivity.this, MapActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case R.id.friendsIcon:
                        Intent intent4 = new Intent(NewsActivity.this, FriendsActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.settingsIcon:
                        Intent intent5 = new Intent(NewsActivity.this, InfoActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                }
                return false;
            }
        });

    }

    // Lägger till de tre olika fragmentsen i "tabben"
    private void setupViewPager(ViewPager viewPager) {
    SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment());
        adapter.addFragment(new Tab2Fragment());
        viewPager.setAdapter(adapter);
    }






}

