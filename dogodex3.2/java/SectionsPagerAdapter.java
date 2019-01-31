package com.example.williamrudwall.dogodex;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

// Klass för att kunna välja fragment

public class SectionsPagerAdapter extends FragmentPagerAdapter {

private final List<Fragment> wFragmentList = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //Kollar var man befinner sig i listan
    @Override
    public Fragment getItem(int position) {
        return wFragmentList.get(position);
    }

    // Kollar hur stor listan är
    @Override
    public int getCount() {
        return wFragmentList.size();
    }

    //Lägger till fragment i listan
    public void addFragment(Fragment fragment){
       wFragmentList.add(fragment);
    }

}
