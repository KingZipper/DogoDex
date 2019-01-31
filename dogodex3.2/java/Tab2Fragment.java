package com.example.williamrudwall.dogodex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Tab2Fragment extends android.support.v4.app.Fragment{

//Klass för tänkt flöde, hårdkodade bilder för att simulera nyhetsflöde

    private ListView wListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        wListView = (ListView) view.findViewById(R.id.listView);

        ArrayList<Card> list = new ArrayList<>();

        list.add(new Card("drawable://" + R.drawable.wilson1, "Wilson1"));
        list.add(new Card("drawable://" + R.drawable.wilson2, "Wilson2"));
        list.add(new Card("drawable://" + R.drawable.wilson3, "Wilson3"));
        list.add(new Card("drawable://" + R.drawable.wilson4, "Wilson4"));
        list.add(new Card("drawable://" + R.drawable.wilson5, "Wilson5"));
        list.add(new Card("drawable://" + R.drawable.wilson6, "Wilson6"));
        list.add(new Card("drawable://" + R.drawable.wilson7, "Wilson7"));


        CustomListAdapter adapter = new CustomListAdapter(getActivity(), R.layout.card_layout_main, list);
        wListView.setAdapter(adapter);




        return view;
    }
}
