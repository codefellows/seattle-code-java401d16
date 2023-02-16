package com.zork.zorkmaster.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.zork.zorkmaster.R;
import com.zork.zorkmaster.adapter.SuperPetRecyclerViewAdapter;
import com.zork.zorkmaster.database.ZorkMasterDatabase;
import com.zork.zorkmaster.model.SuperPet;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // TODO Step 1-1: (In main activity) Add a RecyclerView in the WSYIWYG editor
    ZorkMasterDatabase zorkMasterDatabase;
    public static final String DATABASE_NAME = "zork_master";
    List<SuperPet> superPetList;
    SuperPetRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Builder pattern
        zorkMasterDatabase = Room.databaseBuilder(
                getApplicationContext(),
                ZorkMasterDatabase.class,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()// If Room gets confused, it tosses your database; don't use this in production!
                .allowMainThreadQueries()
                .build();

        superPetList = zorkMasterDatabase.superPetDao().findAll();

        setupBttns();
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        superPetList.clear();
        superPetList.addAll(zorkMasterDatabase.superPetDao().findAll());
        adapter.notifyDataSetChanged();
    }

    // TODO Step 1-2: (In main activity) Grab the RecyclerView
    public void setUpRecyclerView(){

        // TODO Step 2-2: (In main activity) Make some data items

        RecyclerView superPetRecyclerView = findViewById(R.id.MainActivityRecyclerViewSuperPet);
        // TODO Step 1-3: (In main activity) Set the layout manager of the RecyclerView to a LinearLayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        superPetRecyclerView.setLayoutManager(layoutManager);

        // TODO Step 1-5: (In main activity) Create and attach the RecyclerView.Adapter
        // TODO Step 2-3: (In main activity and RecyclerViewAdapter) Hand in some data items
        // TODO Step 3-2: (In main activity and RecyclerViewAdapter) Hand in the Activity context
        adapter = new SuperPetRecyclerViewAdapter(superPetList, this);
        superPetRecyclerView.setAdapter(adapter);
    }

    public void setupBttns(){
        findViewById(R.id.MainActivityBttnAddASuperPet).setOnClickListener(v -> {
            Intent goToAddASuperPetIntent = new Intent(this, AddASuperPetActivity.class);
            startActivity(goToAddASuperPetIntent);
                });
    }
}