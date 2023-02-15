package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;
import com.zork.zorkmaster.model.SuperPet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // TODO Step 1-1: (In main activity) Add a RecyclerView in the WSYIWYG editor



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpRecyclerView();
    }

    // TODO Step 1-2: (In main activity) Grab the RecyclerView
    public void setUpRecyclerView(){

        // TODO Step 2-2: (In main activity) Make some data items
        List<SuperPet> superPetList = new ArrayList<>();
        SuperPet pet = new SuperPet("a", "1");
        SuperPet pet1 = new SuperPet("b", "2");
        SuperPet pet2 = new SuperPet("c", "3");
        SuperPet pet3 = new SuperPet("d", "4");
        SuperPet pet4 = new SuperPet("e", "5");
        SuperPet pet5 = new SuperPet("f", "6");
        SuperPet pet6 = new SuperPet("g", "7");
        superPetList.add(pet);
        superPetList.add(pet1);
        superPetList.add(pet2);
        superPetList.add(pet3);
        superPetList.add(pet4);
        superPetList.add(pet5);
        superPetList.add(pet6);

        RecyclerView superPetRecyclerView = findViewById(R.id.MainActivityRecyclerViewSuperPet);
        // TODO Step 1-3: (In main activity) Set the layout manager of the RecyclerView to a LinearLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        superPetRecyclerView.setLayoutManager(layoutManager);

        // TODO Step 1-5: (In main activity) Create and attach the RecyclerView.Adapter
        // TODO Step 2-3: (In main activity and RecyclerViewAdapter) Hand in some data items
        // TODO Step 3-2: (In main activity and RecyclerViewAdapter) Hand in the Activity context
        SuperPetRecyclerViewAdapter adapter = new SuperPetRecyclerViewAdapter(superPetList, this);
        superPetRecyclerView.setAdapter(adapter);
    }
}